package se.lexicon.group.Thymeleafmvcjpahotel.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.lexicon.group.Thymeleafmvcjpahotel.entity.Room;
import se.lexicon.group.Thymeleafmvcjpahotel.entity.RoomType;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RoomDTOTest {
    Room firstRoom;
    Room secondRoom;
    RoomType firstRoomType;
    RoomType secondRoomType;
    RoomDTO firstRoomDTO;
    RoomDTO secondRoomDTO;
    List<Room> roomList;

    @BeforeEach
    void setUp() {
        firstRoomType = new RoomType("firstRoomDescription");
        secondRoomType = new RoomType("secondRoomDescription");

        firstRoom = new Room("102", "aa", (short) 4, "Your hotel room is smarter than you", true, firstRoomType);
        secondRoom = new Room("100", "bb", (short) 8, "Second room description", true, secondRoomType);

        firstRoomDTO = new RoomDTO(firstRoom);
        secondRoomDTO = new RoomDTO(secondRoom);

        roomList = new ArrayList<>();
        roomList.add(firstRoom);
        roomList.add(secondRoom);
    }

    @Test
    void if_convert_room_to_roomDto() {
        assertEquals(firstRoom.getRoomId(), firstRoomDTO.getRoomId());
        assertEquals(firstRoom.getCode(), firstRoomDTO.getCode());
        assertEquals(firstRoom.getFloor(), firstRoomDTO.getFloor());
        assertEquals(secondRoom.getRoomId(), secondRoomDTO.getRoomId());
        assertEquals(secondRoom.getCode(), secondRoomDTO.getCode());
        assertEquals(secondRoom.getFloor(), secondRoomDTO.getFloor());
        assertEquals(firstRoom.getDescription(), firstRoomDTO.getDescription());
        assertEquals(secondRoom.getDescription(), secondRoomDTO.getDescription());
    }

    @Test
    void if_covert_room_list_to_roomDto_list() {
        secondRoomType = new RoomType("secondRoomDescription");
        secondRoom = new Room("100", "bb", (short) 8, "Second room description", true, secondRoomType);
        List<Room> roomList = new ArrayList<>();
        roomList.add(firstRoom);
        roomList.add(secondRoom);
        List<RoomDTO> roomDtoList = RoomDTO.toRoomDto(roomList);
        assertEquals(roomList.size(), roomDtoList.size());
    }
}
