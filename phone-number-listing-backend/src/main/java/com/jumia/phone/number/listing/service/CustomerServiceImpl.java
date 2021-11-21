package com.jumia.phone.number.listing.service;

import com.jumia.phone.number.listing.DTO.SpecificationWithJoinDTO;
import com.jumia.phone.number.listing.DTO.customer.CustomerDTO;
import com.jumia.phone.number.listing.DTO.customer.CustomerPhoneNumberDTO;
import com.jumia.phone.number.listing.entity.Customer;
import com.jumia.phone.number.listing.mapping.Mapper;
import com.jumia.phone.number.listing.repository.CustomerRepository;
import com.jumia.phone.number.listing.specification.SpecificationGenerator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class CustomerServiceImpl implements CustomerService {
  private final CustomerRepository customerRepository;
  private final SpecificationGenerator<Customer> specificationGenerator;
  private final Mapper mapper;
  private final String COUNTRY_NAME = "name", VALID_PHONE = "validPhone", COUNTRY = "country";

  public CustomerServiceImpl(CustomerRepository customerRepository, SpecificationGenerator<Customer> specificationGenerator, Mapper mapper) {
    this.customerRepository = customerRepository;
    this.specificationGenerator = specificationGenerator;
    this.mapper = mapper;
  }

  @Override
  public Page<CustomerPhoneNumberDTO> getPhoneNumbers(String countryName, Boolean validPhone, Pageable pageable) {
    Map<String, Object> attributeValuesByAttributeNames = new HashMap<>();
    attributeValuesByAttributeNames.put(VALID_PHONE, validPhone);
    Specification<Customer> customerSpecification =
            Specification.where(validPhone == null ? null : specificationGenerator.getEqualitySpecification(attributeValuesByAttributeNames))
                         .and(countryName == null ? null : specificationGenerator.getEqualitySpecificationWithJoinedAttribute(
                                 new SpecificationWithJoinDTO<>(COUNTRY, COUNTRY_NAME, countryName)));

    Page<Customer> customerPage = customerRepository.findAll(customerSpecification, pageable);
    return customerPage.map((customer -> mapper.map(customer, CustomerPhoneNumberDTO.class)));
  }

  @Override
  public void addCustomer(CustomerDTO customerDTO) {
    Customer customer = mapper.map(customerDTO, Customer.class);
    customer.validatePhoneNumber();
    customerRepository.save(customer);
  }
}
