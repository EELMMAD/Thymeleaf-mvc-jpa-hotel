package se.lexicon.group.Thymeleafmvcjpahotel.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import se.lexicon.group.Thymeleafmvcjpahotel.dto.RoomDTO;
import se.lexicon.group.Thymeleafmvcjpahotel.dto.RoomTypeDTO;
import se.lexicon.group.Thymeleafmvcjpahotel.entity.Room;
import se.lexicon.group.Thymeleafmvcjpahotel.entity.RoomType;
import se.lexicon.group.Thymeleafmvcjpahotel.repository.RoomRepository;
import se.lexicon.group.Thymeleafmvcjpahotel.repository.RoomTypeRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
class RoomServiceTest {
    RoomService testObject;

    RoomTypeServiceImpl roomTypeServiceImp;
    RoomServiceImpl roomServiceImp;

    Room firstRoom;
    Room secondRoom;

    RoomType firstRoomType;
    RoomType secondRoomType;
    RoomType thirdRoomType;

    RoomTypeDTO roomTypeDTO1 = new RoomTypeDTO();
    RoomTypeDTO roomTypeDTO2 = new RoomTypeDTO();

    RoomDTO roomDTO1 = new RoomDTO();
    RoomDTO roomDTO2 = new RoomDTO();

    @Autowired
    RoomTypeRepository roomTypeRepository;

    @Autowired
    RoomRepository roomRepository;

    @BeforeEach
    void setUp() {
        testObject = new RoomServiceImpl(roomRepository);

        roomTypeServiceImp = new RoomTypeServiceImpl(roomTypeRepository);
        roomServiceImp = new RoomServiceImpl(roomRepository);

        firstRoomType = roomTypeRepository.save(new RoomType("Such a nice room type"));
        secondRoomType = roomTypeRepository.save(new RoomType("Best room you have ever seen"));
        thirdRoomType = roomTypeRepository.save(new RoomType("Such a nice roomType"));

        roomTypeDTO1 = new RoomTypeDTO(firstRoomType);
        roomTypeDTO2 = new RoomTypeDTO(secondRoomType);

        firstRoom = roomRepository.save(new Room("102", "aa", (short) 4, "Your hotel room is smarter than you", true, firstRoomType));
        secondRoom = roomRepository.save(new Room("100", "bb", (short) 8, "Second room description", true, secondRoomType));
        roomDTO1 = new RoomDTO(firstRoom);
        roomDTO2 = new RoomDTO(secondRoom);
    }

    @Test
    void successfully_created() {
        assertNotNull(roomTypeServiceImp);
        assertNotNull(firstRoomType);
        assertNotNull(secondRoomType);
        assertNotNull(roomTypeDTO1);
        assertNotNull(roomTypeDTO2);
        assertNotNull(firstRoom);
        assertNotNull(secondRoom);
        assertNotNull(roomDTO1);
        assertNotNull(roomDTO2);
        assertNotNull(testObject);
    }

    @Test
    void findAll() {
        assertEquals(2, testObject.findAll().size());
        assertTrue(testObject.findAll().contains(roomDTO1));
        assertTrue(testObject.findAll().contains(roomDTO2));
    }

    @Test
    void findById() {
        assertEquals(roomDTO1, testObject.findById(firstRoom.getRoomId()));
        assertEquals(roomDTO2, testObject.findById(secondRoom.getRoomId()));
    }

    @Test
    void create() {
        RoomDTO roomDTO3 = new RoomDTO();
        RoomType roomType3 = roomTypeRepository.save(new RoomType("new new"));
        RoomTypeDTO roomTypeDTO3 = new RoomTypeDTO(roomType3);
        roomDTO3.setCode("ddd");
        roomDTO3.setRoomId("ppp");
        roomDTO3.setDescription("3 description");
        roomDTO3.setRoomType(roomTypeDTO3);
        roomDTO3.setAvailable(true);
        roomDTO3 = testObject.create(roomDTO3);

        assertEquals(3, testObject.findAll().size());
        assertTrue(testObject.findAll().contains(roomDTO3));
        assertTrue(testObject.findAll().contains(roomDTO3));
    }

    @Test
    void update() {
        roomDTO2.setDescription("Wonderful description");
        roomDTO2.setFloor((short) 3);
        testObject.update(roomDTO2);
        assertEquals("Wonderful description", roomDTO2.getDescription());
    }

    @Test
    void delete() {
        assertTrue(testObject.delete(roomDTO1.getRoomId()));
        assertEquals(1, testObject.findAll().size());

        assertTrue(testObject.findAll().contains(roomDTO2));
        assertFalse(testObject.findAll().contains(roomDTO1));
    }
}