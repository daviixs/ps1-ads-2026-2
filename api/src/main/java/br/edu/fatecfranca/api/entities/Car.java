package br.edu.fatecfranca.api.entities;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "cars")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String brand;

    @Column(nullable = false)
    private String model;

    @Column(nullable = false)
    private String color;

    @Column(name = "year_manufacture", nullable = false)
    private Integer yearManufacture;

    @Column(nullable = false)
    private Boolean imported;

    @Column(nullable = false, unique = true)
    private String plates;

    @Column(name = "selling_date")
    private LocalDate sellingDate;

    @Column(name = "selling_price", precision = 12, scale = 2)
    private BigDecimal sellingPrice;

    @Column(name = "customer_id")
    private Long customerId;

    public Car() {
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getBrand() { return brand; }
    public void setBrand(String brand) { this.brand = brand; }
    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }
    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }
    public Integer getYearManufacture() { return yearManufacture; }
    public void setYearManufacture(Integer yearManufacture) { this.yearManufacture = yearManufacture; }
    public Boolean getImported() { return imported; }
    public void setImported(Boolean imported) { this.imported = imported; }
    public String getPlates() { return plates; }
    public void setPlates(String plates) { this.plates = plates; }
    public LocalDate getSellingDate() { return sellingDate; }
    public void setSellingDate(LocalDate sellingDate) { this.sellingDate = sellingDate; }
    public BigDecimal getSellingPrice() { return sellingPrice; }
    public void setSellingPrice(BigDecimal sellingPrice) { this.sellingPrice = sellingPrice; }
    public Long getCustomerId() { return customerId; }
    public void setCustomerId(Long customerId) { this.customerId = customerId; }
}
