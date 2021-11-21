package com.jumia.phone.number.listing.controller;

import com.jumia.phone.number.listing.DTO.customer.CustomerDTO;
import org.springframework.data.domain.Pageable;

public interface CustomerController<T> {
  T getPhoneNumbers(String country, Boolean valid, Pageable pageable);

  T addCustomer(CustomerDTO customerDTO);
}
