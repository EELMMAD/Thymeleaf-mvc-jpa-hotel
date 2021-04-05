package se.lexicon.group.Thymeleafmvcjpahotel.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoomTypeTest {
    RoomType testObject;

    @BeforeEach
    void setUp() {
        testObject = new RoomType("Your hotel room is smarter than you");
    }

    @Test
    void successfully_created() {
        assertNotNull(testObject);
        assertEquals("Your hotel room is smarter than you", testObject.getDescription());
    }

    @Test
    void testEquals() {
        RoomType room = new RoomType("Your hotel room is smarter than you");
        assertTrue(testObject.equals(room));
    }

    @Test
    void testHashCode() {
        RoomType room = new RoomType("Your hotel room is smarter than you");
        assertEquals(room.hashCode(), testObject.hashCode());
    }

    @Test
    void testToString() {
        String toString = testObject.toString();
        assertTrue(toString.contains("Your hotel room is smarter than you"));
    }

    @Test
    void getRoomTypeId() {
        assertEquals(null, testObject.getRoomTypeId());
    }

    @Test
    void getDescription() {
        assertEquals("Your hotel room is smarter than you", testObject.getDescription());
    }

    @Test
    void setDescription() {
        testObject.setDescription("Smart room");
        assertEquals("Smart room", testObject.getDescription());
    }
}
