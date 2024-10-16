package se.iths.vuegroupprojectbackend.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import se.iths.vuegroupprojectbackend.dto.CarDTO;
import se.iths.vuegroupprojectbackend.dto.DeleteReservationDTO;
import se.iths.vuegroupprojectbackend.dto.ReservationDTO;
import se.iths.vuegroupprojectbackend.entities.Car;
import se.iths.vuegroupprojectbackend.entities.Reservation;
import se.iths.vuegroupprojectbackend.services.CarService;
import se.iths.vuegroupprojectbackend.services.ReservationService;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class Controller {
    private final CarService carService;
    private final ReservationService reservationService;

    public Controller(CarService carService, ReservationService reservationService) {
        this.carService = carService;
        this.reservationService = reservationService;
    }

    @GetMapping("cars")
    List<CarDTO> allCars() {
        var cars = carService.findAll();
        if(cars.isEmpty())
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        return cars;
    }

    @GetMapping("cars/{id}")
    CarDTO findCarById(@PathVariable Long id) {
        var car = carService.findById(id);
        if (car == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return car;
    }

    @PostMapping("reservations")
    String reserveCar(@RequestBody ReservationDTO reservationDTO) {
        CarDTO car = carService.findById(reservationDTO.carId());
        String reservationNumber = reservationService.saveReservation(reservationDTO, carService.convertToEntity(car));
        if (reservationNumber != null && !reservationNumber.isEmpty() && !reservationNumber.isBlank()) {
            carService.increasePopularity(carService.convertToEntity(car));
            return reservationNumber;
        }
        return "Could not reserve the car";
    }

    @DeleteMapping("delete")
    boolean deleteReservation(@RequestBody DeleteReservationDTO reservationNumber) {
        return reservationService.deleteReservation(reservationNumber.reservationNumber());
    }

}
