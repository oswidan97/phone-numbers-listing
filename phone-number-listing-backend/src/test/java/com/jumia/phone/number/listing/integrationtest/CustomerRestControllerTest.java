package com.jumia.phone.number.listing.integrationtest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.jumia.phone.number.listing.DTO.CountryDTO;
import com.jumia.phone.number.listing.DTO.customer.CustomerDTO;
import com.jumia.phone.number.listing.entity.Country;
import com.jumia.phone.number.listing.entity.Customer;
import com.jumia.phone.number.listing.repository.CountryRepository;
import com.jumia.phone.number.listing.repository.CustomerRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
public class CustomerRestControllerTest {

  @Autowired
  private MockMvc mvc;

  @Autowired
  private CustomerRepository customerRepository;

  @Autowired
  private CountryRepository countryRepository;

  @After
  public void resetDb() {
    customerRepository.deleteAll();
    countryRepository.deleteAll();
  }

  @Test
  public void givenCustomerPhoneNumber_whenGetCustomerPhoneNumber_thenStatus200() throws Exception {
    createTestCustomer("(237)21478569");
    createTestCustomer("(512)21478569");

    mvc.perform(get("/customer/phoneNumber").contentType(MediaType.APPLICATION_JSON))
       .andDo(print())
       .andExpect(status().isOk())
       .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
       .andExpect(jsonPath("$.content[0].phone", is("(237)21478569")))
       .andExpect(jsonPath("$.content[1].phone", is("(512)21478569")));
  }

  @Test
  public void givenCustomerPhoneNumber_whenGetCustomerPhoneNumberWith2PagesEachSize1_thenStatus200And2PagesEachSize1() throws Exception {
    createTestCustomer("(237)21478569");
    createTestCustomer("(512)21478569");

    mvc.perform(get("/customer/phoneNumber?page=0&size=1").contentType(MediaType.APPLICATION_JSON))
       .andDo(print())
       .andExpect(status().isOk())
       .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
       .andExpect(jsonPath("$.content", hasSize(equalTo(1))))
       .andExpect(jsonPath("$.totalPages", equalTo(2)))
       .andExpect(jsonPath("$.totalElements", equalTo(2)))
       .andExpect(jsonPath("$.numberOfElements", equalTo(1)));
  }

  @Test
  public void givenCustomerPhoneNumber_whenGetCustomerPhoneNumberWithGivenCountryAndState_thenStatus200And1ElementReturnedWithRequiredValues() throws Exception {
    createTestCustomer("Egypt", true);
    createTestCustomer("Egypt", false);
    createTestCustomer("Germany", true);
    createTestCustomer("Germany", false);

    mvc.perform(get("/customer/phoneNumber?countryName=Egypt&valid=true").contentType(MediaType.APPLICATION_JSON))
       .andDo(print())
       .andExpect(status().isOk())
       .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
       .andExpect(jsonPath("$.content", hasSize(equalTo(1))));
  }

  @Test
  public void givenCustomer_whenPostCustomerWithoutCountryRegex_thenStatus400() throws Exception {
    CustomerDTO customerDTO = new CustomerDTO();
    String name = "John Doe";
    customerDTO.setName(name);
    customerDTO.setPhone("0123");
    CountryDTO countryDTO = new CountryDTO();
    String countryName = "Egypt";
    countryDTO.setName(countryName);
    customerDTO.setCountry(countryDTO);
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
    ObjectWriter objectWriter = objectMapper.writer().withDefaultPrettyPrinter();
    String requestJson = objectWriter.writeValueAsString(customerDTO);

    mvc.perform(post("/customer").contentType(MediaType.APPLICATION_JSON).content(requestJson)).andDo(print()).andExpect(status().isBadRequest());
  }

  @Test
  public void givenCustomer_whenPostCustomer_thenStatus200AndCustomerFoundInDB() throws Exception {
    CustomerDTO customerDTO = new CustomerDTO();
    String name = "John Doe";
    customerDTO.setName(name);
    customerDTO.setPhone("0123");
    CountryDTO countryDTO = new CountryDTO();
    String countryName = "Egypt";
    countryDTO.setName(countryName);
    countryDTO.setRegex("\\(237\\)?[2368]\\d{7,8}");
    customerDTO.setCountry(countryDTO);
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
    ObjectWriter objectWriter = objectMapper.writer().withDefaultPrettyPrinter();
    String requestJson = objectWriter.writeValueAsString(customerDTO);

    mvc.perform(post("/customer").contentType(MediaType.APPLICATION_JSON).content(requestJson)).andDo(print()).andExpect(status().isOk());

    Optional<Customer> optionalCustomer = customerRepository.findByName(name);
    assertTrue(optionalCustomer.isPresent());
    assertThat(optionalCustomer.get().getName()).isEqualTo(name);
    assertThat(optionalCustomer.get().getCountry().getName()).isEqualTo(countryName);
  }

  private void createTestCustomer(String countryName, boolean phoneValidState) {
    Customer customer = new Customer();
    customer.setName("Bob");
    customer.setPhone("0123");
    customer.setValidPhone(phoneValidState);
    Country country = new Country();
    country.setName(countryName);
    customer.setCountry(country);
    customerRepository.saveAndFlush(customer);
  }

  private void createTestCustomer(String phoneNumber) {
    Customer customer = new Customer();
    customer.setName("Bob");
    customer.setPhone(phoneNumber);
    customer.setValidPhone(true);
    Country country = new Country();
    country.setRegex("\\(237\\)?[2368]\\d{7,8}");
    customer.setCountry(country);
    customerRepository.saveAndFlush(customer);
  }
}
