package se.lexicon.group.Thymeleafmvcjpahotel.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import se.lexicon.group.Thymeleafmvcjpahotel.entity.Room;
import se.lexicon.group.Thymeleafmvcjpahotel.entity.RoomType;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class RoomRepositoryTest {
    RoomType firstRoomType;
    RoomType secondRoomType;
    Room firstRoom;
    Room secondRoom;

    @Autowired
    RoomTypeRepository roomTypeRepository;

    @Autowired
    RoomRepository roomRepository;

    @BeforeEach
    void setUp() {
        firstRoomType = new RoomType("First room type description");
        secondRoomType = new RoomType("Description of second room type");

        roomTypeRepository.save(firstRoomType);
        roomTypeRepository.save(secondRoomType);

        firstRoom = roomRepository.save(new Room("444", "yyy", (short) 2, "Good room", true, firstRoomType));
        secondRoom = roomRepository.save(new Room("888", "zzz", (short) 4, "Nice room", true, secondRoomType));

        firstRoom.setAvailable(true);
    }

    @Test
    void successfully_created() {
        assertNotNull(firstRoomType);
        assertNotNull(secondRoomType);
        assertNotNull(roomTypeRepository);
        assertNotNull(firstRoom);
        assertNotNull(secondRoom);
        assertNotNull(roomRepository);
    }

    @Test
    void findAll() {
        assertEquals(2, roomRepository.findAll().size());
    }

    @Test
    void findByRoomId() {
        Room room = roomRepository.findByRoomId(firstRoom.getRoomId());
        // assertEquals(firstRoom.getRoomId(), room.getRoomId());
        assertEquals(firstRoom.getDescription(), room.getDescription());
        assertEquals(firstRoom.getRoomType(), room.getRoomType());
        assertEquals(firstRoom.getFloor(), room.getFloor());
        assertEquals(firstRoom.getCode(), room.getCode());
        assertTrue(firstRoom.equals(room));
    }

    @Test
    void findByRoomTypeRoomTypeId() {
        List<Room> room1 = roomRepository.findByRoomTypeRoomTypeId("123 45");
        List<Room> room2 = roomRepository.findByRoomTypeRoomTypeId("456 78");
        assertNotNull(room1);
        assertNotNull(room2);
    }
}












