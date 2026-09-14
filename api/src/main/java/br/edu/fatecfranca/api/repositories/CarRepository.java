package br.edu.fatecfranca.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.fatecfranca.api.entities.Car;

public interface CarRepository extends JpaRepository<Car, Long> {
}
