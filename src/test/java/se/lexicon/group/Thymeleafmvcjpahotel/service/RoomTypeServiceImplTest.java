package se.lexicon.group.Thymeleafmvcjpahotel.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import se.lexicon.group.Thymeleafmvcjpahotel.dto.RoomTypeDTO;
import se.lexicon.group.Thymeleafmvcjpahotel.entity.RoomType;
import se.lexicon.group.Thymeleafmvcjpahotel.repository.RoomTypeRepository;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
class RoomTypeServiceImplTest {
    RoomTypeServiceImpl testObject;

    RoomType firstRoomType;
    RoomType secondRoomType;

    RoomTypeDTO roomTypeDTO1 = new RoomTypeDTO();
    RoomTypeDTO roomTypeDTO2 = new RoomTypeDTO();

    @Autowired
    RoomTypeRepository roomTypeRepository;

    @BeforeEach
    void setUp() {
        testObject = new  RoomTypeServiceImpl(roomTypeRepository);
        firstRoomType = roomTypeRepository.save( new RoomType("Such a nice room type"));
        secondRoomType = roomTypeRepository.save(new RoomType("Best room you have ever seen"));

        roomTypeDTO1 = new RoomTypeDTO(firstRoomType);
        roomTypeDTO2 = new RoomTypeDTO(secondRoomType);
    }

    @Test
    void successfully_created(){
        assertNotNull(testObject);
        assertNotNull(firstRoomType);
        assertNotNull(secondRoomType);
        assertNotNull(roomTypeDTO1);
        assertNotNull(roomTypeDTO2);
    }

    @Test
    void findAll() {
        assertEquals(2, testObject.findAll().size());
        assertTrue(testObject.findAll().contains(roomTypeDTO1));
        assertTrue(testObject.findAll().contains(roomTypeDTO2));
    }

    @Test
    void findById() {
        assertEquals(roomTypeDTO1, testObject.findById(firstRoomType.getRoomTypeId()));
        assertEquals(roomTypeDTO2, testObject.findById(secondRoomType.getRoomTypeId()));
    }

    @Test
    void create() {
        RoomTypeDTO roomTypeDTO3 = new RoomTypeDTO();
        roomTypeDTO3.setDescription("Third description");
        roomTypeDTO3.setRoomTypeId("000");
        roomTypeDTO3 = testObject.create(roomTypeDTO3);
        assertEquals(3, testObject.findAll().size());
        assertTrue(testObject.findAll().contains(roomTypeDTO3));
    }

    @Test
    void update() {
        firstRoomType.setDescription("This is first description");
        testObject.update(roomTypeDTO1);

        secondRoomType.setDescription("Here is the second description");
        testObject.update(roomTypeDTO2);

        assertEquals("This is first description", testObject.findById(roomTypeDTO1.getRoomTypeId()).getDescription());
        assertEquals("Here is the second description", testObject.findById(roomTypeDTO2.getRoomTypeId()).getDescription());
    }

    @Test
    void delete() {
        assertTrue(testObject.delete(roomTypeDTO1.getRoomTypeId()));
        assertEquals(1, testObject.findAll().size());

        assertTrue(testObject.findAll().contains(roomTypeDTO2));
        assertFalse(testObject.findAll().contains(roomTypeDTO1));
    }
}