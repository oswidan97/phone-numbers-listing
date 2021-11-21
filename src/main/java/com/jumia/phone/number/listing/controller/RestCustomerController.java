package com.jumia.phone.number.listing.controller;

import com.jumia.phone.number.listing.DTO.customer.CustomerDTO;
import com.jumia.phone.number.listing.DTO.customer.CustomerPhoneNumberDTO;
import com.jumia.phone.number.listing.service.CustomerService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
public class RestCustomerController implements CustomerController<ResponseEntity<?>> {
  private final CustomerService customerService;

  public RestCustomerController(CustomerService customerService) {
    this.customerService = customerService;
  }
  @Override
  @GetMapping("/phoneNumber")
  public ResponseEntity<Page<CustomerPhoneNumberDTO>> getPhoneNumbers(@RequestParam(value = "countryName", required = false) String countryName,
          @RequestParam(value = "valid", required = false) Boolean valid, Pageable pageable) {
    return new ResponseEntity<>(customerService.getPhoneNumbers(countryName, valid, pageable), HttpStatus.OK);
  }

  @Override
  @PostMapping("/")
  public ResponseEntity<?> addCustomer(@RequestBody CustomerDTO customerDTO) {
    customerService.addCustomer(customerDTO);
    return new ResponseEntity<>(HttpStatus.OK);
  }

}
