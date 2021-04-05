package se.lexicon.group.Thymeleafmvcjpahotel.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.lexicon.group.Thymeleafmvcjpahotel.entity.RoomType;

import static org.junit.jupiter.api.Assertions.*;

class RoomTypeDTOTest {
    RoomType firstRoomType;
    RoomType secondRoomType;
    RoomTypeDTO firstRoomTypeDto;
    RoomTypeDTO secondRoomTypeDto;

    @BeforeEach
    void setUp() {
        firstRoomType = new RoomType("first description");
        secondRoomType = new RoomType("second description");
        firstRoomTypeDto = new RoomTypeDTO(firstRoomType);
        secondRoomTypeDto = new RoomTypeDTO(secondRoomType);
    }

    @Test
    void if_convert_roomType_to_roomTypeDto() {
        assertEquals(firstRoomType.getRoomTypeId(), firstRoomTypeDto.getRoomTypeId());
        assertEquals(secondRoomType.getRoomTypeId(), secondRoomTypeDto.getRoomTypeId());
        assertEquals(firstRoomType.getDescription(), firstRoomTypeDto.getDescription());
        assertEquals(secondRoomType.getDescription(), secondRoomTypeDto.getDescription());
    }
}