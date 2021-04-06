package se.lexicon.group.Thymeleafmvcjpahotel.service;

import se.lexicon.group.Thymeleafmvcjpahotel.dto.RoomTypeDTO;
import java.util.List;

public interface RoomTypeService {
    List<RoomTypeDTO> findAll();
    RoomTypeDTO findById(String id);
    RoomTypeDTO create(RoomTypeDTO roomDTO);
    RoomTypeDTO update(RoomTypeDTO roomDTO);
    boolean delete(String roomTypeId);
}
