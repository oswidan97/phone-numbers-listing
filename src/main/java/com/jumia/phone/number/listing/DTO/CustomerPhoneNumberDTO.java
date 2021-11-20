package com.jumia.phone.number.listing.DTO;

public class CustomerPhoneNumberDTO {
  private String phone;

  private boolean validPhone;

  private CountryDTO country;

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
}
