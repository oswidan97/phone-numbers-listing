package com.jumia.phone.number.listing.DTO.customer;

import com.jumia.phone.number.listing.DTO.CountryDTO;

public class CustomerPhoneNumberDTO {
  private String phone;

  private boolean validPhone;

  private CountryDTO country;

  public CustomerPhoneNumberDTO() {
  }

  public CustomerPhoneNumberDTO(String phone, boolean validPhone, CountryDTO country) {
    this.phone = phone;
    this.validPhone = validPhone;
    this.country = country;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public boolean isValidPhone() {
    return validPhone;
  }

  public void setValidPhone(boolean validPhone) {
    this.validPhone = validPhone;
  }

  public CountryDTO getCountry() {
    return country;
  }

  public void setCountry(CountryDTO country) {
    this.country = country;
  }

  @Override
  public String toString() {
    return "CustomerPhoneNumberDTO{" + "phone='" + phone + '\'' + ", validPhone=" + validPhone + ", country=" + country + '}';
  }
}
