package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.Car;

public interface CarRepository extends JpaRepository<Car, Long> {
}
