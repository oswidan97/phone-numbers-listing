package com.jumia.phone.number.listing.service;

import com.jumia.phone.number.listing.DTO.CustomerPhoneNumberDTO;
import org.springframework.data.domain.Pageable;

public interface CustomerService {
  CustomerPhoneNumberDTO getPhoneNumbers(String country, boolean validPhone, Pageable pageable);
}
