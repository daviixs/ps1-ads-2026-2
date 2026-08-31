package br.edu.fatecfranca.api.entities;

import java.time.LocalDate;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "customers")
public class Customer {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String name;

  @Column(name = "ident_document", nullable = false, unique = true)
  private String identDocument;

  @Column(name = "birth_date")
  private LocalDate birthDate;

  @Column(name = "street_name", nullable = false)
  private String streetName;

  @Column(name = "house_number", nullable = false)
  private String houseNumber;

  @Column(nullable = true)
  private String complements;

  @Column(nullable = false)
  private String district;

  @Column(nullable = false)
  private String municipality;

  @JdbcTypeCode(SqlTypes.CHAR)
  @Column(nullable = false, length = 2, columnDefinition = "CHAR(2)")
  private String state;

  @Column(nullable = false)
  private String phone;

  @Column(nullable = false, unique = true)
  private String email;

  public Customer() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getIdentDocument() {
    return identDocument;
  }

  public void setIdentDocument(String identDocument) {
    this.identDocument = identDocument;
  }

  public LocalDate getBirthDate() {
    return birthDate;
  }

  public void setBirthDate(LocalDate birthDate) {
    this.birthDate = birthDate;
  }

  public String getStreetName() {
    return streetName;
  }

  public void setStreetName(String streetName) {
    this.streetName = streetName;
  }

  public String getHouseNumber() {
    return houseNumber;
  }

  public void setHouseNumber(String houseNumber) {
    this.houseNumber = houseNumber;
  }

  public String getComplements() {
    return complements;
  }

  public void setComplements(String complements) {
    this.complements = complements;
  }

  public String getDistrict() {
    return district;
  }

  public void setDistrict(String district) {
    this.district = district;
  }

  public String getMunicipality() {
    return municipality;
  }

  public void setMunicipality(String municipality) {
    this.municipality = municipality;
  }

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }
  
}
