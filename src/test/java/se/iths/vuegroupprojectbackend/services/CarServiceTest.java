package se.iths.vuegroupprojectbackend.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import se.iths.vuegroupprojectbackend.dto.CarDTO;
import se.iths.vuegroupprojectbackend.entities.Car;
import se.iths.vuegroupprojectbackend.repositories.CarRepository;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {CarService.class})
@ExtendWith(SpringExtension.class)
class CarServiceTest {

    @MockBean
    CarRepository carRepository;

    @Autowired
    CarService carService;

    private Car car1;
    private Car car2;

    @BeforeEach
    void setUp() {


        car1 = new Car(1L, "image1", "model1", 10, 4, "Automatic", "Petrol", 200, "about car1");
        car2 = new Car(2L, "image2", "model2", 20, 5, "Manual", "Diesel", 300, "about car2");
    }

    @Test
    void findAll() {
        when(carRepository.findAll()).thenReturn(List.of(car1, car2));

        List<CarDTO> cars = carService.findAll();

        assertThat(cars).hasSize(2);
        assertThat(cars.get(0).model()).isEqualTo("model1");
        assertThat(cars.get(1).model()).isEqualTo("model2");

        verify(carRepository, times(1)).findAll();
    }

    @Test
    void findById() {
        when(carRepository.findById(1L)).thenReturn(Optional.of(car1));

        CarDTO carDTO = carService.findById(1L);

        assertThat(carDTO).isNotNull();
        assertThat(carDTO.model()).isEqualTo("model1");

        verify(carRepository, times(1)).findById(1L);
    }

    @Test
    void notFoundById() {
        when(carRepository.findById(999L)).thenReturn(Optional.empty());

        CarDTO carDTO = carService.findById(999L);

        assertThat(carDTO).isNull();

        verify(carRepository, times(1)).findById(999L);
    }

    @Test
    void convertToDTO() {
        CarDTO carDTO = carService.convertToDTO(car1);

        assertThat(carDTO).isNotNull();
        assertThat(carDTO.model()).isEqualTo("model1");
        assertThat(carDTO.seats()).isEqualTo(4);
        assertThat(carDTO.fuel()).isEqualTo("Petrol");
    }

    @Test
    void convertToEntity() {
        CarDTO carDTO = new CarDTO(1L,"imageLink", "model1", 4, 4, "Automatic", "Petrol", 200, "about lol");

        Car car = carService.convertToEntity(carDTO);

        assertThat(car).isNotNull();
        assertThat(car.getModel()).isEqualTo("model1");
        assertThat(car.getSeats()).isEqualTo(4);
        assertThat(car.getFuel()).isEqualTo("Petrol");
    }

    @Test
    void increasePopularity() {
        carService.increasePopularity(car1);

        assertThat(car1.getPopularity()).isEqualTo(11);

        verify(carRepository, times(1)).save(car1);
    }

}