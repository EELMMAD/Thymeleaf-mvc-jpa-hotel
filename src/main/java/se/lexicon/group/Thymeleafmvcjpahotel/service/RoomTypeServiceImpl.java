package se.lexicon.group.Thymeleafmvcjpahotel.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;
import se.lexicon.group.Thymeleafmvcjpahotel.dto.RoomTypeDTO;
import se.lexicon.group.Thymeleafmvcjpahotel.entity.RoomType;
import se.lexicon.group.Thymeleafmvcjpahotel.repository.RoomTypeRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Configurable
public class RoomTypeServiceImpl implements RoomTypeService {
    RoomTypeRepository roomTypeRepo;

    @Autowired
    public RoomTypeServiceImpl(RoomTypeRepository roomTypeRepository) {
        this.roomTypeRepo = roomTypeRepository;
    }


    @Override
    public List<RoomTypeDTO> findAll() {
        List<RoomType> roomTypes = roomTypeRepo.findAll();
        List<RoomTypeDTO> dtoLis = new ArrayList<RoomTypeDTO>();
        for (RoomType roomType : roomTypes)
            dtoLis.add(new RoomTypeDTO(roomType));
        return dtoLis;
    }

    @Override
    public RoomTypeDTO findById(String id) {
        Optional<RoomType> roomType = roomTypeRepo.findById(id);
        if (roomType.isPresent())
            return new RoomTypeDTO(roomType.get());
        throw new RuntimeException("Room Type doesn't exist");
    }

    @Override
    public RoomTypeDTO create(RoomTypeDTO roomDTO) {
        RoomType roomType = new RoomType();
        roomType.setDescription(roomType.getDescription());
        return new RoomTypeDTO(roomTypeRepo.save(roomType));
    }

    @Override
    public RoomTypeDTO update(RoomTypeDTO roomDTO) {
        Optional<RoomType> roomTypeO = roomTypeRepo.findById(roomDTO.getRoomTypeId());
        if (!roomTypeO.isPresent())
            throw new RuntimeException("Room Type doesn't exist");

        RoomType roomType = roomTypeO.get();
        roomType.setDescription(roomType.getDescription());
        return new RoomTypeDTO(roomTypeRepo.save(roomType));
    }

    @Override
    public boolean delete(String roomTypeId) {
        if (!roomTypeRepo.findById(roomTypeId).isPresent())
            throw new RuntimeException("There is no room with id: " + roomTypeId);
        roomTypeRepo.delete(roomTypeRepo.findById(roomTypeId).get());
        return true;
    }
}
