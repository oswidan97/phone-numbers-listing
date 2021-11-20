package com.jumia.phone.number.listing.controller;

import com.jumia.phone.number.listing.entity.Customer;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController("/customer")
public class RestCustomerController implements CustomerController<ResponseEntity> {

  @GetMapping("/phoneNumber")
  public ResponseEntity<Customer> getPhoneNumbers(@RequestParam(value = "countryName", required = false) String countryName,
          @RequestParam(value = "valid", required = false) boolean valid, Pageable pageable) {


  }
}
