package com.jumia.phone.number.listing.controller;

import org.springframework.data.domain.Pageable;

public interface CustomerController<T> {
  T getPhoneNumbers(String country, boolean valid, Pageable pageable);
}
