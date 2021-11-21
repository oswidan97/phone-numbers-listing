package com.jumia.phone.number.listing.service;

import com.jumia.phone.number.listing.DTO.customer.CustomerDTO;
import com.jumia.phone.number.listing.DTO.customer.CustomerPhoneNumberDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomerService {
  Page<CustomerPhoneNumberDTO> getPhoneNumbers(String country, Boolean validPhone, Pageable pageable);

  void addCustomer(CustomerDTO customerDTO);
}
