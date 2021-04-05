package se.lexicon.group.Thymeleafmvcjpahotel.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import se.lexicon.group.Thymeleafmvcjpahotel.entity.RoomType;
import java.util.List;


public interface RoomTypeRepository extends CrudRepository<RoomType, String> {
    List<RoomType> findAll();
}
