package se.iths.vuegroupprojectbackend.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.iths.vuegroupprojectbackend.dto.ReservationDTO;
import se.iths.vuegroupprojectbackend.entities.Car;
import se.iths.vuegroupprojectbackend.entities.Reservation;
import se.iths.vuegroupprojectbackend.repositories.ReservationRepository;

import java.sql.Date;

@Service
@Transactional
public class ReservationService {

    ReservationRepository reservationRepository;

    ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public boolean deleteReservation(String reservationNumber) {
        var reservations = reservationRepository.findAll();
        Reservation deleteReservation = null;
        for (Reservation reservation : reservations) {
            if (reservationNumber.contains(reservation.getReservationNumber())) {
                deleteReservation = reservation;
            }
        }
        if (deleteReservation == null) {
            return false;
        }
        reservationRepository.delete(deleteReservation);
        return true;
    }

    public String saveReservation(ReservationDTO reservationDTO, Car car) {
        Reservation reservation = new Reservation();
        reservation.setDateFrom(Date.valueOf(reservationDTO.dateFrom()));
        reservation.setDateTo(Date.valueOf(reservationDTO.dateTo()));
        reservation.setCar(car);
        reservation.setCustomerEmail(reservationDTO.customerEmail());
        reservation.setCustomerName(reservationDTO.customerName());
        reservation.setCustomerPhone(reservationDTO.customerPhone());
        reservation.setReservationNumber(reservation.generateReservationNumber());
        return reservationRepository.save(reservation).getReservationNumber();
    }

}
