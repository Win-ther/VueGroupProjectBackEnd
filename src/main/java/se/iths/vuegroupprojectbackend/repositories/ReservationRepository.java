package se.iths.vuegroupprojectbackend.repositories;

import org.springframework.data.repository.ListCrudRepository;
import se.iths.vuegroupprojectbackend.entities.Reservation;

public interface ReservationRepository extends ListCrudRepository<Reservation, Long> {
}
