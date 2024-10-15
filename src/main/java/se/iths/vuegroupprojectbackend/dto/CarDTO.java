package se.iths.vuegroupprojectbackend.dto;

public record CarDTO(Long id, String image, String model, int popularity, int seats, String transmission, String fuel, int price, String about) {
}
