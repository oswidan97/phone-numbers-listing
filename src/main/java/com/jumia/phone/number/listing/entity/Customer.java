package com.jumia.phone.number.listing.entity;

import javax.persistence.*;
import java.util.regex.Pattern;

@Entity
public class Customer {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id", nullable = false)
  private Integer id;

  @Column
  private String name;

  @Column
  private String phone;

  @Column(name = "valid_phone")
  private boolean validPhone;

  @OneToOne
  private Country country;

  public Customer() {
  }

  public Customer(String name, String phone, boolean validPhone) {
    this.name = name;
    this.phone = phone;
    this.validPhone = validPhone;
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

  public Country getCountry() {
    return country;
  }

  public void setCountry(Country country) {
    this.country = country;
  }

  public void validatePhoneNumber() {
    validPhone = Pattern.matches(country.getRegex(), phone);
  }

  @Override
  public String toString() {
    return "Customer{" + "id=" + id + ", name='" + name + '\'' + ", phone='" + phone + '\'' + ", validPhone=" + validPhone + ", country=" + country +
            '}';
  }
}
