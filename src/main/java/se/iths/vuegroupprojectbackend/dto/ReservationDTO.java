package se.iths.vuegroupprojectbackend.dto;


public record ReservationDTO(String dateFrom, String dateTo, Long carId, String customerEmail, String customerName, String customerPhone) {
}
