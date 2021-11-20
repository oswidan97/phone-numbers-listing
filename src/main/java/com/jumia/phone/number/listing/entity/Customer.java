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

  @OneToOne
  private Country country;


  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public boolean isPhoneNumberValid() {
    return Pattern.matches(country.getRegex(), phone);
  }
}
