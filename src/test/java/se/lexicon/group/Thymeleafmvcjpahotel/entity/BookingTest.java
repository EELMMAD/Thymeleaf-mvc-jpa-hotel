package se.lexicon.group.Thymeleafmvcjpahotel.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BookingTest {
    Booking testObject;
    Customer customer;
    Room room;
    RoomType roomType;

    @BeforeEach
    void setUp() {

        roomType = new RoomType("room type description");
        room = new Room("555", "asd", (short) 2, "room description", true, roomType);

        testObject = new Booking("111", LocalDate.of(2021, 03, 05),
                "My description", LocalDate.of(2021, 03, 28), customer, room);
        List<Booking> bookingList = new ArrayList<>();
        bookingList.add(testObject);

        customer = new Customer("123", "Negar", "Madadi", "negar.madadi@gmail.com");
    }

    @Test
    void successfully_created() {
        assertNotNull(testObject);
        assertNotNull(roomType);
        assertNotNull(room);
        assertNotNull(customer);
        assertNotEquals("Null", testObject.getBookingId());
        assertEquals("111", testObject.getBookingId());
        assertEquals("My description", testObject.getDescription());
    }

    @Test
    void testHashCode() {
        Booking booking = new Booking("123", LocalDate.of(2020, 2, 2), "singleRoom", LocalDate.now(), customer, room);
        assertNotEquals(booking.hashCode(), testObject.hashCode());
    }

    @Test
    void testToString() {
        String toString = testObject.toString();
        assertTrue(toString.contains("0"));
        assertTrue(toString.contains(testObject.getDescription()));
        assertTrue(toString.contains(testObject.getBookingId()));
    }
}