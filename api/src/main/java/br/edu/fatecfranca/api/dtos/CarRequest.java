package br.edu.fatecfranca.api.dtos;

import java.math.BigDecimal;
import java.time.LocalDate;

public record CarRequest(
   String brand,
   String model,
   String color,
   Long yearManufacture,
   Boolean imported,
   String plates,
   LocalDate sellingDate,
   BigDecimal sellingPrice,
   Long customerId
) {}
