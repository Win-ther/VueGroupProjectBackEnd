package se.iths.vuegroupprojectbackend.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ReservationTest {

    private Reservation reservation;
    private Reservation reservation2;

    @BeforeEach
    void setUp() {
        Car car = new Car(2L, "image", "model", 420, 4, "Automatic", "Electric", 300, "aboot");

        reservation = new Reservation();
        reservation.setId(1L);
        reservation.setDateFrom(Date.valueOf("2024-03-12"));
        reservation.setDateTo(Date.valueOf("2025-03-12"));
        reservation.setCar(car);
        reservation.setCustomerEmail("Email@email.email");
        reservation.setCustomerName("Jeff Burnham");
        reservation.setCustomerPhone("0808050505");

        reservation2 = new Reservation();
        reservation2.setId(2L);
        reservation2.setDateFrom(Date.valueOf("2024-03-12"));
        reservation2.setDateTo(Date.valueOf("2025-03-12"));
        reservation2.setCar(car);
        reservation2.setCustomerEmail("Email@email.email");
        reservation2.setCustomerName("Jeff Burnham");
        reservation2.setCustomerPhone("0808050505");


    }

    @Test
    void generateReservationNumber() {
        reservation.setReservationNumber(reservation.generateReservationNumber());

        assertThat(reservation.getReservationNumber()).isEqualTo("J505242008");
    }

    @Test
    void testEquals() {
        Reservation reservation3 = reservation;
        assertEquals(reservation, reservation3, "Reservations with the same ID should be the same");
    }

    @Test
    void testNotEquals() {
        assertNotEquals(reservation, reservation2, "Reservations with the same ID should not be the same");
    }

    @Test
    void testHashCode() {
        assertEquals(reservation.hashCode(), reservation.hashCode(), "Reservations with the same ID should have the same hash code");
    }
}