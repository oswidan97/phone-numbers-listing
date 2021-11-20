package com.jumia.phone.number.listing.repository;

import com.jumia.phone.number.listing.entity.Country;
import com.jumia.phone.number.listing.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Integer>, JpaSpecificationExecutor<Customer> {
  Page<Customer> findAllByCountry(Country country, Pageable pageable);

  Page<Customer> findAllByState(boolean state, Pageable pageable);


}