package se.iths.vuegroupprojectbackend.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CarTest {
    private Car car;
    private Car car2;


    @BeforeEach
    void setUp() {
        car = new Car(1L, "imageLink", "model", 5, 4, "Automatic", "Petrol", 200, "Car description");
        car2 = new Car(2L, "imageLink", "model", 5, 4, "Automatic", "Petrol", 200, "Car description");
    }

    @Test
    void increasePopularity() {
        int initialPopularity = car.getPopularity();
        car.increasePopularity();
        assertEquals(initialPopularity + 1, car.getPopularity(), "Popularity should increase by 1");
    }

    @Test
    void decreasePopularity() {
        int initialPopularity = car.getPopularity();
        car.decreasePopularity();
        assertEquals(initialPopularity - 1, car.getPopularity(), "Popularity should decrease by 1");
    }

    @Test
    void testEquals() {
        Car car3 = car;
        assertEquals(car, car3, "Cars with the same ID should be the same");
    }

    @Test
    void testNotEquals() {
        assertNotEquals(car, car2, "Cars with the same ID should not be the same");
    }

    @Test
    void testHashCode() {
        assertEquals(car.hashCode(), car2.hashCode(), "Cars with the same ID should have the same hash code");
    }
}