package se.lexicon.group.Thymeleafmvcjpahotel.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RoomTest {
    Room testObject;
    RoomType roomType;

    @BeforeEach
    void setUp() {
        roomType = new RoomType("Room type description");
        testObject = new Room("102", "22", (short) 2, "Your hotel room is smarter than you", true, roomType);
    }

    @Test
    void successfully_created() {
        assertNotNull(testObject);
        assertNotEquals("Null", testObject.getRoomId());
        assertEquals("102", testObject.getRoomId());
        assertEquals("22", testObject.getCode());
        assertEquals("Your hotel room is smarter than you", testObject.getDescription());
        assertEquals((short) 2, testObject.getFloor());
        assertEquals(true, testObject.getAvailable());
        assertEquals(roomType, testObject.getRoomType());
    }

    @Test
    void testEquals() {
        RoomType roomType1 = new RoomType("Room type description");
        Room room1 = new Room("102", "22", (short) 2, "Your hotel room is smarter than you", true, roomType1);
        assertTrue(testObject.equals(room1));
    }

    @Test
    void testHashCode() {
        RoomType roomType1 = new RoomType("Room type description");
        Room room1 = new Room("102", "22", (short) 2, "Your hotel room is smarter than you", true, roomType1);
        assertEquals(room1.hashCode(), testObject.hashCode());
    }

    @Test
    void testToString() {
        String toString = testObject.toString();
        assertTrue(toString.contains("0"));
        assertTrue(toString.contains("102"));
        assertTrue(toString.contains("22"));
        assertTrue(toString.contains("Your hotel room is smarter than you"));
        assertTrue(toString.contains("Room type description"));
    }

    @Test
    void getDescription() {
        assertEquals("Your hotel room is smarter than you", testObject.getDescription());
    }

    @Test
    void setDescription() {
        testObject.setDescription("Your hotel room is smarter than you");
        assertEquals("Your hotel room is smarter than you", testObject.getDescription());
    }
}