package se.lexicon.group.Thymeleafmvcjpahotel.contoller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import se.lexicon.group.Thymeleafmvcjpahotel.dto.RoomTypeDTO;
import se.lexicon.group.Thymeleafmvcjpahotel.service.RoomTypeService;

import java.util.List;

@Controller
@RequestMapping("/roomType")
public class RoomTypeController {

    @Autowired
    private RoomTypeService roomTypeService;

    @GetMapping()
    public ResponseEntity<List<RoomTypeDTO>> getAllRoomType() {
        return ResponseEntity.ok(roomTypeService.findAll());
    }

    @GetMapping("/{RoomTypeId}")
    public ResponseEntity<RoomTypeDTO> getRoomType(@PathVariable String RoomTypeId) {
        return ResponseEntity.ok(roomTypeService.findById(RoomTypeId));
    }

    @DeleteMapping("/{RoomTypeId}")
    public ResponseEntity<Boolean> deleteRoomType(@PathVariable String RoomTypeId) {
        return ResponseEntity.ok(roomTypeService.delete(RoomTypeId));
    }

    @PutMapping()
    public ResponseEntity<RoomTypeDTO> updateRoomType(@RequestBody RoomTypeDTO RoomTypeDTO) {
        return ResponseEntity.ok(roomTypeService.update(RoomTypeDTO));
    }

    @PostMapping()
    public ResponseEntity<RoomTypeDTO> addRoomType(@RequestBody RoomTypeDTO RoomTypeDTO) {
        return ResponseEntity.ok(roomTypeService.create(RoomTypeDTO));
    }
}

