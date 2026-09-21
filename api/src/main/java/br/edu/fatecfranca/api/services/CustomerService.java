package br.edu.fatecfranca.api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.edu.fatecfranca.api.entities.Customer;
import br.edu.fatecfranca.api.repositories.CustomerRepository;

@Service
public class CustomerService {

  private final CustomerRepository repository;

  public CustomerService(CustomerRepository repository) {
    this.repository = repository;
  }

  public Customer create(Customer customer) {
    return repository.save(customer);
  }

  public List<Customer> findAll() {
    return repository.findAll();
  }

  public Optional<Customer> findById(Long id) {
    return repository.findById(id);
  }

  public Customer update(Customer customer) {
    return repository.save(customer);
  }

  public boolean existsById(Long id) {
    return repository.existsById(id);
  }

  public void deleteById(Long id) {
    repository.deleteById(id);
  }
}
