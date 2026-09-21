package br.edu.fatecfranca.api.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.fatecfranca.api.entities.Customer;
import br.edu.fatecfranca.api.services.CustomerService;

@RestController
@RequestMapping("/customers")
public class CustomerController {

  private final CustomerService service;

  public CustomerController(CustomerService service) {
    this.service = service;
  }

  @PostMapping
  public ResponseEntity<Customer> create(@RequestBody Customer customer) {
    Customer savedCustomer = service.create(customer);

    return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(savedCustomer);
  }

  @GetMapping
  public List<Customer> findAll() {
    return service.findAll();
  }

  @GetMapping("/{id}")
  public ResponseEntity<Customer> findById(@PathVariable Long id) {
    return service.findById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
  }

  @PutMapping("/{id}")
  public ResponseEntity<Customer> update(
          @PathVariable Long id,
          @RequestBody Customer customer) {

    if (!service.existsById(id)) {
      return ResponseEntity.notFound().build();
    }

    customer.setId(id);

    return ResponseEntity.ok(service.update(customer));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    if (!service.existsById(id)) {
      return ResponseEntity.notFound().build();
    }

    service.deleteById(id);

    return ResponseEntity.noContent().build();
  }

}
