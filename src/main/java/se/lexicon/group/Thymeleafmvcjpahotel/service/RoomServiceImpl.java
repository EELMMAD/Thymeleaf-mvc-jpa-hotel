package se.lexicon.group.Thymeleafmvcjpahotel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;
import se.lexicon.group.Thymeleafmvcjpahotel.dto.RoomDTO;
import se.lexicon.group.Thymeleafmvcjpahotel.dto.RoomTypeDTO;
import se.lexicon.group.Thymeleafmvcjpahotel.entity.Room;
import se.lexicon.group.Thymeleafmvcjpahotel.entity.RoomType;
import se.lexicon.group.Thymeleafmvcjpahotel.repository.RoomRepository;
import se.lexicon.group.Thymeleafmvcjpahotel.repository.RoomTypeRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Configurable
public class RoomServiceImpl implements RoomService {
    RoomRepository roomRepo;

    @Autowired
    public RoomServiceImpl(RoomRepository roomTypeRepository) {
        this.roomRepo = roomTypeRepository;
    }

    @Autowired
    RoomTypeRepository typeRepository;


    @Override
    public List<RoomDTO> findAll() {
        List<Room> rooms = roomRepo.findAll();
        List<RoomDTO> result = new ArrayList<>();
        for (Room room : rooms) {
            RoomDTO roomDTO = new RoomDTO(room);
            result.add(roomDTO);
        }
        return result;
    }

    @Override
    public RoomDTO findById(String roomId) {
        if (roomId == null || roomId.equals(""))
            throw new IllegalArgumentException("Title is necessary");
        RoomDTO dto = new RoomDTO(roomRepo.findByRoomId(roomId));
        return dto;
    }

    @Override
    public List<RoomDTO> findByRoomType(String type) {
        if (type == null || type.equals(""))
            throw new IllegalArgumentException("Room type is necessary");
        List<Room> rooms = roomRepo.findByRoomTypeRoomTypeId(type);
        List<RoomDTO> result = new ArrayList<>();
        for (Room room : rooms) {
            RoomDTO roomDTO = new RoomDTO(room);
            result.add(roomDTO);
        }
        return result;
    }

    @Override
        public RoomDTO create (RoomDTO roomDTO)  throws RuntimeException {
        if (roomDTO.getRoomId().equals(""))
            throw new RuntimeException("Room id is invalid");
        if (roomRepo.findById(roomDTO.getRoomId()).isPresent())
           throw new RuntimeException("Room already exists, please update");
       Room room = new Room(roomDTO.getRoomId(), roomDTO.getCode(), roomDTO.getFloor(),
                            roomDTO.getDescription(), roomDTO.getAvailable(),new RoomType(roomDTO.getDescription()));
        room.setAvailable(roomDTO.isAvailable());
        return new RoomDTO(roomRepo.save(room));
    }

    @Override
    @Transactional
    public RoomDTO update(RoomDTO roomDTO) throws RuntimeException{
            Optional<Room> optionalRoom = roomRepo.findById(roomDTO.getRoomId());
            if (!optionalRoom.isPresent())
                throw new RuntimeException("Room doesn't exist");
            Room toUpdated = optionalRoom.get();
            if (!toUpdated.getCode().equals(roomDTO.getCode()))
                toUpdated.setCode(roomDTO.getCode());

            if (toUpdated.getAvailable() != roomDTO.getAvailable())
                toUpdated.setAvailable(roomDTO.getAvailable());

            if (toUpdated.getFloor() != roomDTO.getFloor())
                toUpdated.setFloor(roomDTO.getFloor());

            if (!toUpdated.getRoomType().equals(roomDTO.getRoomType()))
                toUpdated.setRoomType(new RoomType(roomDTO.getDescription()));

            if (!toUpdated.getDescription().equals(roomDTO.getDescription()))
                toUpdated.setDescription(roomDTO.getDescription());

            return new RoomDTO(roomRepo.save(toUpdated));
    }

    @Override
    @Transactional
    public boolean delete(String roomId) {
        if (!roomRepo.findById(roomId).isPresent())
            throw new RuntimeException("There is no room with id: " + roomId);
        boolean deleted = false;
        if (roomRepo.existsById(roomId)) {
            roomRepo.delete(roomRepo.findById(roomId).get());
            deleted = true;
        }
        return deleted;
    }
}
