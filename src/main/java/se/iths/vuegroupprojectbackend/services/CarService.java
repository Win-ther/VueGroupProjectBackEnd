package se.iths.vuegroupprojectbackend.services;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import se.iths.vuegroupprojectbackend.dto.CarDTO;
import se.iths.vuegroupprojectbackend.entities.Car;
import se.iths.vuegroupprojectbackend.repositories.CarRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CarService {

    CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public List<CarDTO> findAll() {
        List<Car> cars = carRepository.findAll();
        return cars.stream()
                .map(this::convertToDTO)
                .toList();
    }

    public CarDTO findById(Long id) {
        Optional<Car> car = carRepository.findById(id);
        if (car.isPresent()) {
            return convertToDTO(carRepository.findById(id).get());
        }
        return null;
    }

    public CarDTO convertToDTO(Car car) {
        return new CarDTO(
                car.getId(),
                car.getImage(),
                car.getModel(),
                car.getPopularity(),
                car.getSeats(),
                car.getTransmission(),
                car.getFuel(),
                car.getPrice(),
                car.getAbout()
        );
    }
    public Car convertToEntity(CarDTO car) {
        return new Car(
                car.id(),
                car.image(),
                car.model(),
                car.popularity(),
                car.seats(),
                car.transmission(),
                car.fuel(),
                car.price(),
                car.about()
        );
    }
}
