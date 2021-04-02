package se.lexicon.group.Thymeleafmvcjpahotel.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {
    Customer testObject;

    @BeforeEach
    void setUp() {
        RoomType roomType = new RoomType("room type description");
        Room room = new Room("555", "asd", (short) 2, "room description", true, roomType);

        testObject = new Customer("123", "Negar", "Madadi", "negar.madadi@gmail.com");
    }

    @Test
    void successfully_created() {
        assertNotNull(testObject);
        assertNotEquals("Null", testObject.getCustomerId());
        assertEquals("Negar", testObject.getFirstName());
        assertEquals("Madadi", testObject.getLastName());
        assertEquals("negar.madadi@gmail.com", testObject.getEmail());
    }

    @Test
    void testHashcode() {
        RoomType roomType = new RoomType("room type description");
        Room room = new Room("555", "asd", (short) 2, "room description", true, roomType);

        Booking booking = new Booking("111", LocalDate.of(2021, 03, 05),
                "My description", LocalDate.of(2021, 03, 28), testObject, room);
        List<Booking> bookingList = new ArrayList<>();
        bookingList.add(booking);
        Customer customer1 = new Customer("123", "Negar", "Madadi", "negar.madadi@gmail.com");
        assertEquals(customer1.hashCode(), testObject.hashCode());
    }

    @Test
    void testToString() {
        String toString = testObject.toString();
        assertTrue(toString.contains(testObject.getFirstName()));
        assertTrue(toString.contains(testObject.getLastName()));
        assertTrue(toString.contains(testObject.getEmail()));
    }

}