package br.edu.fatecfranca.api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.edu.fatecfranca.api.entities.Car;
import br.edu.fatecfranca.api.repositories.CarRepository;

@Service
public class CarService {

    private final CarRepository repository;

    public CarService(CarRepository repository) {
        this.repository = repository;
    }

    public Car create(Car car) {
        return repository.save(car);
    }

    public List<Car> findAll() {
        return repository.findAll();
    }

    public Optional<Car> findById(Long id) {
        return repository.findById(id);
    }

    public Car update(Car car) {
        return repository.save(car);
    }

    public boolean existsById(Long id) {
        return repository.existsById(id);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
