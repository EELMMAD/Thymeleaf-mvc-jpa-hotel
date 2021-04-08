package se.lexicon.group.Thymeleafmvcjpahotel.service;

import se.lexicon.group.Thymeleafmvcjpahotel.dto.RoomDTO;

import java.util.List;

public interface RoomService {
    List<RoomDTO> findAll();
    RoomDTO findById(String id);
    List<RoomDTO> findByRoomType(String type);
    RoomDTO create(RoomDTO roomDTO);
    RoomDTO update(RoomDTO roomDTO);
    boolean delete(String roomId);
}
