package com.spring.ws.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "address")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address {

  @Id
  @Column(name = "id")
  @SequenceGenerator(name="address_seq", sequenceName = "address_seq",allocationSize=100)
  @GeneratedValue(generator="address_seq")
  private Long id;

  @Column(name="address")
  private String address;
}
