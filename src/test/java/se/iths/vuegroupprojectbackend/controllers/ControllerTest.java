package se.iths.vuegroupprojectbackend.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import se.iths.vuegroupprojectbackend.dto.CarDTO;
import se.iths.vuegroupprojectbackend.dto.ReservationDTO;
import se.iths.vuegroupprojectbackend.entities.Car;
import se.iths.vuegroupprojectbackend.services.CarService;
import se.iths.vuegroupprojectbackend.services.ReservationService;

import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.ArgumentMatchers.any;

@WebMvcTest(controllers = Controller.class)
class ControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CarService carService;

    @MockBean
    private ReservationService reservationService;

    private CarDTO car1;
    private CarDTO car2;

    @BeforeEach
    void setUp() {
        car1 = new CarDTO(1L, "imageLink", "carModel", 32, 4, "Automatic", "Petrol 95", 300, "THIS IS CAR1");
        car2 = new CarDTO(2L, "imageLink2", "carModel2", 23, 5, "Manual", "Petrol 95", 300, "THIS IS CAR2");
    }

    @Test
    void allCars() throws Exception {
        List<CarDTO> carList = List.of(car1, car2);
        when(carService.findAll()).thenReturn(carList);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/cars"))
                .andExpectAll(
                        status().isOk(),
                        content().contentType("Application/Json"),
                        content().string(containsString(car1.model())),
                        content().string(containsString(car2.model()))
                );
    }

    @Test
    void return204IfCarsAreEmpty() throws Exception {
        when(carService.findAll()).thenReturn(List.of());
        mockMvc.perform(MockMvcRequestBuilders.get("/api/cars"))
                .andExpect(status()
                        .isNoContent());
    }

    @Test
    void findCarById() throws Exception {
        when(carService.findById(1L)).thenReturn(car1);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/cars/1"))
                .andExpectAll(
                        status().isOk(),
                        content().contentType("Application/Json"),
                        content().string(containsString(car1.about()))
                );
    }

    @Test
    void return404IfCarDoesNotExist() throws Exception {
        when(carService.findById(1L)).thenReturn(null);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/cars/1"))
                .andExpect(
                        status().isNotFound()
                );
    }

    @Test
    void reserveCar() throws Exception {
        Car car = new Car(1L, "imageLink", "carModel", 32, 4, "Automatic", "Petrol 95", 300, "THIS IS CAR1");
        when(carService.findById(1L)).thenReturn(car1);
        when(carService.convertToEntity(car1)).thenReturn(car);
        doNothing().when(carService).increasePopularity(any());
        when(reservationService.saveReservation(any(ReservationDTO.class), any())).thenReturn("ASDF1234");

        mockMvc.perform(MockMvcRequestBuilders.post("/api/reservations")
                        .contentType("Application/Json")
                        .content("{\"carId\": 1, \"dateFrom\": \"2023-04-11\", \"dateTo\": \"2024-05-23\", \"customerEmail\": \"email@email.email\", \"customerName\": \"name name\", \"customerPhone\": \"0708456545\"}"))
                .andExpectAll(
                        status().isOk(),
                        content().contentType("text/plain;charset=UTF-8"),
                        content().string("ASDF1234")
                );
    }

    @Test
    void reserveCarFailureWithNull() throws Exception {
        Car car = new Car(1L, "imageLink", "carModel", 32, 4, "Automatic", "Petrol 95", 300, "THIS IS CAR1");
        when(carService.findById(1L)).thenReturn(car1);
        when(carService.convertToEntity(car1)).thenReturn(car);
        doNothing().when(carService).increasePopularity(any());
        when(reservationService.saveReservation(any(ReservationDTO.class), any())).thenReturn(null);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/reservations")
                        .contentType("Application/Json")
                        .content("{\"carId\": 1, \"dateFrom\": \"2023-04-11\", \"dateTo\": \"2024-05-23\", \"customerEmail\": \"email@email.email\", \"customerName\": \"name name\", \"customerPhone\": \"0708456545\"}"))
                .andExpectAll(
                        status().isOk(),
                        content().contentType("text/plain;charset=UTF-8"),
                        content().string("Could not reserve the car")
                );
    }
    @Test
    void reserveCarFailureEmpty() throws Exception {
        Car car = new Car(1L, "imageLink", "carModel", 32, 4, "Automatic", "Petrol 95", 300, "THIS IS CAR1");
        when(carService.findById(1L)).thenReturn(car1);
        when(carService.convertToEntity(car1)).thenReturn(car);
        doNothing().when(carService).increasePopularity(any());
        when(reservationService.saveReservation(any(ReservationDTO.class), any())).thenReturn("");

        mockMvc.perform(MockMvcRequestBuilders.post("/api/reservations")
                        .contentType("Application/Json")
                        .content("{\"carId\": 1, \"dateFrom\": \"2023-04-11\", \"dateTo\": \"2024-05-23\", \"customerEmail\": \"email@email.email\", \"customerName\": \"name name\", \"customerPhone\": \"0708456545\"}"))
                .andExpectAll(
                        status().isOk(),
                        content().contentType("text/plain;charset=UTF-8"),
                        content().string("Could not reserve the car")
                );
    }
    @Test
    void reserveCarFailureBlank() throws Exception {
        Car car = new Car(1L, "imageLink", "carModel", 32, 4, "Automatic", "Petrol 95", 300, "THIS IS CAR1");
        when(carService.findById(1L)).thenReturn(car1);
        when(carService.convertToEntity(car1)).thenReturn(car);
        doNothing().when(carService).increasePopularity(any());
        when(reservationService.saveReservation(any(ReservationDTO.class), any())).thenReturn("    ");

        mockMvc.perform(MockMvcRequestBuilders.post("/api/reservations")
                        .contentType("Application/Json")
                        .content("{\"carId\": 1, \"dateFrom\": \"2023-04-11\", \"dateTo\": \"2024-05-23\", \"customerEmail\": \"email@email.email\", \"customerName\": \"name name\", \"customerPhone\": \"0708456545\"}"))
                .andExpectAll(
                        status().isOk(),
                        content().contentType("text/plain;charset=UTF-8"),
                        content().string("Could not reserve the car")
                );
    }

    @Test
    void deleteReservation_success() throws Exception {
        String reservationNumber = "ASDF1234";
        when(reservationService.deleteReservation(reservationNumber)).thenReturn(true);

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/delete")
                        .contentType("application/json")
                        .content("{\"reservationNumber\":  \"ASDF1234\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
    }

    @Test
    void deleteReservation_failure() throws Exception {
        String reservationNumber = "ASDF1234";
        when(reservationService.deleteReservation(reservationNumber)).thenReturn(false);

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/delete")
                        .contentType("application/json")
                        .content("{\"reservationNumber\":  \"ASDF1234\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("false"));
    }
}