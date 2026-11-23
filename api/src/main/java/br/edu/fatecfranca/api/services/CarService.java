package br.edu.fatecfranca.api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.edu.fatecfranca.api.dtos.CarRequest;
import br.edu.fatecfranca.api.entities.Car;
import br.edu.fatecfranca.api.entities.Customer;
import br.edu.fatecfranca.api.repositories.CarRepository;
import br.edu.fatecfranca.api.repositories.CustomerRepository;
import br.edu.fatecfranca.api.validators.ManufactureYearValidator;
import br.edu.fatecfranca.api.validators.RequestValidator;
import br.edu.fatecfranca.api.validators.SaleDataValidator;
import br.edu.fatecfranca.api.validators.UniquePlatesValidator;

@Service
public class CarService {

    private final CarRepository repository;
    private final CustomerRepository customerRepository;
    private final RequestValidator<CarRequest> validator;

    public CarService(CarRepository repository, CustomerRepository customerRepository) {
        this.repository = repository;
        this.customerRepository = customerRepository;

        RequestValidator<CarRequest> platesValidator =
            new UniquePlatesValidator(repository, null);

        RequestValidator<CarRequest> saleDataValidator =
            new SaleDataValidator(platesValidator);

        this.validator = new ManufactureYearValidator(saleDataValidator);
    }

    public Car create(CarRequest request) {
        validator.validate(request, null);

        Car car = new Car();
        copyToEntity(request, car);

        return repository.save(car);
    }

    public List<Car> findAll() {
        return repository.findAll();
    }

    public Optional<Car> findById(Long id) {
        return repository.findById(id);
    }

    public Car update(Long id, CarRequest request) {
        validator.validate(request, id);

        Car car = new Car();
        copyToEntity(request, car);
        car.setId(id);

        return repository.save(car);
    }

    public boolean existsById(Long id) {
        return repository.existsById(id);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    private void copyToEntity(CarRequest request, Car car) {
        car.setBrand(request.brand());
        car.setModel(request.model());
        car.setColor(request.color());
        car.setYearManufacture(
            request.yearManufacture() == null ? null : request.yearManufacture().intValue()
        );
        car.setImported(request.imported());
        car.setPlates(request.plates());
        car.setSellingDate(request.sellingDate());
        car.setSellingPrice(request.sellingPrice());
        car.setCustomer(findCustomer(request.customerId()));
    }

    private Customer findCustomer(Long customerId) {
        if (customerId == null) {
            return null;
        }

        return customerRepository.findById(customerId)
            .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "O cliente informado não existe."
            ));
    }
}
