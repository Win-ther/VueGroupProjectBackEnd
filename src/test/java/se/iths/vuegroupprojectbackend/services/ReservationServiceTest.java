package se.iths.vuegroupprojectbackend.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import se.iths.vuegroupprojectbackend.dto.ReservationDTO;
import se.iths.vuegroupprojectbackend.entities.Car;
import se.iths.vuegroupprojectbackend.entities.Reservation;
import se.iths.vuegroupprojectbackend.repositories.ReservationRepository;

import java.sql.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = ReservationService.class)
@ExtendWith(SpringExtension.class)
class ReservationServiceTest {

    @MockBean
    ReservationRepository reservationRepository;

    @Autowired
    private ReservationService reservationService;

    private Reservation reservation;
    private ReservationDTO reservationDTO;
    private Car car;

    @BeforeEach
    void setUp() {
        car = new Car(2L, "image", "model", 420, 4, "Automatic", "Electric", 300, "aboot");

        reservation = new Reservation();
        reservation.setId(1L);
        reservation.setDateFrom(Date.valueOf("2024-03-12"));
        reservation.setDateTo(Date.valueOf("2025-03-12"));
        reservation.setCar(car);
        reservation.setCustomerEmail("Email@email.email");
        reservation.setCustomerName("Jeff Burnham");
        reservation.setCustomerPhone("0808050505");
        reservation.setReservationNumber(reservation.generateReservationNumber());

        reservationDTO = new ReservationDTO("2024-03-12","2025-03-12",2L, "Email@email.email", "Jeff Burnham","0808050505");
    }

    @Test
    void deleteReservationWithReservationNumberShouldReturnTrue() {
        when(reservationRepository.findByReservationNumber(reservation.getReservationNumber())).thenReturn(reservation);

        assertThat(reservationService.deleteReservation(reservation.getReservationNumber())).isTrue();
    }

    @Test
    void deleteReservationWithNullShouldReturnFalse() {
        when(reservationRepository.findByReservationNumber(reservation.getReservationNumber())).thenReturn(null);

        assertThat(reservationService.deleteReservation(reservation.getReservationNumber())).isFalse();
    }

    @Test
    void saveReservation() {
        when(reservationRepository.save(any())).thenReturn(reservation);

        assertThat(reservationService.saveReservation(reservationDTO, car)).isEqualTo(reservation.getReservationNumber());
    }
}