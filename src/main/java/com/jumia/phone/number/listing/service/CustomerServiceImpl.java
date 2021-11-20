package com.jumia.phone.number.listing.service;

import com.jumia.phone.number.listing.DTO.CustomerPhoneNumberDTO;
import com.jumia.phone.number.listing.entity.Customer;
import com.jumia.phone.number.listing.repository.CustomerRepository;
import com.jumia.phone.number.listing.specification.SpecificationGenerator;
import org.springframework.data.domain.Pageable;

import java.util.HashMap;
import java.util.Map;

public class CustomerServiceImpl implements CustomerService {
  private final CustomerRepository customerRepository;
  private final SpecificationGenerator<Customer> specificationGenerator;
  private final String COUNTRY_NAME = "countryName", VALID_PHONE = "validPhone";

  public CustomerServiceImpl(CustomerRepository customerRepository, SpecificationGenerator<Customer> specificationGenerator) {
    this.customerRepository = customerRepository;
    this.specificationGenerator = specificationGenerator;
  }

  @Override
  public CustomerPhoneNumberDTO getPhoneNumbers(String countryName, boolean validPhone, Pageable pageable) {
    Map<String, String> attributeValuesByAttributeNames = new HashMap<>();
    attributeValuesByAttributeNames.put(COUNTRY_NAME, countryName);
    attributeValuesByAttributeNames.put(VALID_PHONE, String.valueOf(validPhone));
    customerRepository.findAll(specificationGenerator.getEqualitySpecification(attributeValuesByAttributeNames), pageable);
    return null;
  }
}
