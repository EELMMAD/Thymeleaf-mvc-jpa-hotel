package se.lexicon.group.Thymeleafmvcjpahotel.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import se.lexicon.group.Thymeleafmvcjpahotel.entity.Room;

import java.util.List;
import java.util.Optional;

public interface RoomRepository extends CrudRepository<Room, String> {
    List<Room> findAll();

    Optional<Room> findById(String id);

    List<Room> findByRoomTypeRoomTypeId(String roomTypeId);

    Room findByRoomId(String roomId);
}




