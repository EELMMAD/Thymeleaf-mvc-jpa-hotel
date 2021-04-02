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
        // assertEquals("55", testObject.getRoomTypeId());
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
        // assertTrue(toString.contains("55"));
        assertTrue(toString.contains("Your hotel room is smarter than you"));
    }

}
