package com.jumia.phone.number.listing.entity;

import javax.persistence.*;

@Entity
public class Country {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id", nullable = false)
  private Integer id;

  @Column
  private String name;

  @Column(name = "phone_number_code")
  private String phoneNumberCode;

  @Column
  private String regex;

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

  public String getRegex() {
    return regex;
  }

  public void setRegex(String regex) {
    this.regex = regex;
  }

}
