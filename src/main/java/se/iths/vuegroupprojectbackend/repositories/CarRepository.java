package se.iths.vuegroupprojectbackend.repositories;

import org.springframework.data.repository.ListCrudRepository;
import se.iths.vuegroupprojectbackend.entities.Car;

public interface CarRepository extends ListCrudRepository<Car, Long> {
}
