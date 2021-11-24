package com.jumia.phone.number.listing.unittest;

import com.jumia.phone.number.listing.entity.Country;
import com.jumia.phone.number.listing.entity.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ValidPhoneTests {
  private Customer customer;

  @BeforeEach
  void initUseCase() {
    customer = new Customer();
    Country country = new Country();
    country.setRegex("\\(237\\)?[2368]\\d{7,8}");
    customer.setCountry(country);
  }

  @Test
  void validPhone() {
    customer.setPhone("(237)21478569");
    customer.validatePhoneNumber();
    assertTrue(customer.isValidPhone());
  }

  @Test
  void invalidPhone() {
    customer.setPhone("(237)2147856");
    customer.validatePhoneNumber();
    assertFalse(customer.isValidPhone());
  }
}
