package com.jumia.phone.number.listing.DTO;

public class CountryDTO {
  private Integer id;

  private String name;

  private String phoneNumberCode;

  public CountryDTO() {
  }

  public CountryDTO(Integer id, String name, String phoneNumberCode) {
    this.id = id;
    this.name = name;
    this.phoneNumberCode = phoneNumberCode;
  }

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

  public String getPhoneNumberCode() {
    return phoneNumberCode;
  }

  public void setPhoneNumberCode(String phoneNumberCode) {
    this.phoneNumberCode = phoneNumberCode;
  }

  @Override
  public String toString() {
    return "CountryDTO{" + "id=" + id + ", name='" + name + '\'' + ", phoneNumberCode='" + phoneNumberCode + '\'' + '}';
  }
}
