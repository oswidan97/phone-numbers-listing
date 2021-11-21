package com.jumia.phone.number.listing.DTO.customer;

import com.jumia.phone.number.listing.DTO.CountryDTO;

public class CustomerDTO {

  private Integer id;

  private String name;

  private String phone;

  private CountryDTO country;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public CountryDTO getCountry() {
    return country;
  }

  public void setCountry(CountryDTO country) {
    this.country = country;
  }
}
