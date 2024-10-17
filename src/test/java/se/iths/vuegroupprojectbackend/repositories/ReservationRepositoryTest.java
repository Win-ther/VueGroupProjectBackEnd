package se.iths.vuegroupprojectbackend.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import se.iths.vuegroupprojectbackend.entities.Car;
import se.iths.vuegroupprojectbackend.entities.Reservation;

import java.sql.Date;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestPropertySource(properties = {
        "spring.datasource.url=jdbc:tc:mysql:8.3.0:///mydatabase"
})
class ReservationRepositoryTest {

    @Autowired
    private ReservationRepository reservationRepository;

    @Test
    void findByReservationNumber() {
        Car car = new Car(2L, "image", "model", 420, 4, "Automatic", "Electric", 300, "aboot");
        var reservation = new Reservation();
        reservation.setId(1L);
        reservation.setDateFrom(Date.valueOf("2024-03-12"));
        reservation.setDateTo(Date.valueOf("2025-03-12"));
        reservation.setCar(car);
        reservation.setCustomerEmail("Email@email.email");
        reservation.setCustomerName("Jeff Burnham");
        reservation.setCustomerPhone("0808050505");
        reservation.setReservationNumber(reservation.getReservationNumber());

        reservationRepository.save(reservation);

        assertThat(reservationRepository.findByReservationNumber(reservation.getReservationNumber())).isEqualTo(reservation);
    }
}