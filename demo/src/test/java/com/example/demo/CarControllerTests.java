package com.example.demo;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.controllers.CarController;
import com.example.demo.entities.Car;
import com.example.demo.repositories.CarRepository;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;

@WebMvcTest(CarController.class)
@AutoConfigureMockMvc
class CarControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private CarRepository repository;

    @Test
    void createsAndListsCars() throws Exception {
        Car saved = car(1L, "Toyota", "ABC1D23");
        given(repository.save(any(Car.class))).willReturn(saved);
        given(repository.findAll()).willReturn(List.of(saved));

        String payload = """
                {"brand":"Toyota","model":"Corolla XEi","color":"Prata","yearManufacture":2022,
                 "imported":false,"plates":"ABC1D23","sellingDate":"2026-09-10",
                 "sellingPrice":128500.00,"customerId":1}
                """;

        mockMvc.perform(post("/cars").contentType(MediaType.APPLICATION_JSON).content(payload))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.yearManufacture").value(2022));

        mockMvc.perform(get("/cars"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].plates").value("ABC1D23"));
    }

    @Test
    void findsUpdatesAndDeletesExistingCar() throws Exception {
        Car existing = car(1L, "Toyota", "ABC1D23");
        Car updated = car(1L, "Toyota", "XYZ9Z99");
        given(repository.findById(1L)).willReturn(Optional.of(existing));
        given(repository.existsById(1L)).willReturn(true);
        given(repository.save(any(Car.class))).willReturn(updated);

        mockMvc.perform(get("/cars/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));

        mockMvc.perform(put("/cars/1").contentType(MediaType.APPLICATION_JSON).content("""
                {"brand":"Toyota","model":"Corolla XEi","color":"Preto","yearManufacture":2022,
                 "imported":false,"plates":"XYZ9Z99"}
                """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.plates").value("XYZ9Z99"));

        mockMvc.perform(delete("/cars/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void returnsNotFoundForUnknownCar() throws Exception {
        given(repository.findById(99L)).willReturn(Optional.empty());
        given(repository.existsById(eq(99L))).willReturn(false);

        mockMvc.perform(get("/cars/99")).andExpect(status().isNotFound());
        mockMvc.perform(put("/cars/99").contentType(MediaType.APPLICATION_JSON).content("{}"))
                .andExpect(status().isNotFound());
        mockMvc.perform(delete("/cars/99")).andExpect(status().isNotFound());
    }

    private Car car(Long id, String brand, String plates) {
        Car car = new Car();
        car.setId(id);
        car.setBrand(brand);
        car.setModel("Corolla XEi");
        car.setColor("Prata");
        car.setYearManufacture(2022);
        car.setImported(false);
        car.setPlates(plates);
        return car;
    }
}
