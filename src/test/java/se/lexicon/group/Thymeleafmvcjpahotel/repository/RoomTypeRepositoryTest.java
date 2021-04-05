package se.lexicon.group.Thymeleafmvcjpahotel.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import se.lexicon.group.Thymeleafmvcjpahotel.entity.RoomType;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class RoomTypeRepositoryTest {
    RoomType firstRoomType;
    RoomType secondRoomType;

    @Autowired
    RoomTypeRepository roomTypeRepository;

    @BeforeEach
    void setUp() {
        firstRoomType = new RoomType("First room type description");
        secondRoomType = new RoomType("Description of second room type");

        roomTypeRepository.save(firstRoomType);
        roomTypeRepository.save(secondRoomType);
    }

    @Test
    void successfully_created() {
        assertNotNull(firstRoomType);
        assertNotNull(secondRoomType);
        assertNotNull(roomTypeRepository);
    }

    @Test
    void findAll() {
        assertEquals(2, roomTypeRepository.findAll().size());
    }

}