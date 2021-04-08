package se.lexicon.group.Thymeleafmvcjpahotel.contoller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import se.lexicon.group.Thymeleafmvcjpahotel.dto.RoomDTO;
import se.lexicon.group.Thymeleafmvcjpahotel.service.RoomService;

import java.util.List;

@Controller
@RequestMapping("/room")
public class RoomController {
    @Autowired
    private RoomService roomService;

    @GetMapping()
    public ResponseEntity<List<RoomDTO>> getAllRoom() {
        return ResponseEntity.ok(roomService.findAll());
    }

    @GetMapping("/{RoomId}")
    public ResponseEntity<RoomDTO> getRoom(@PathVariable String RoomId) {
        return ResponseEntity.ok(roomService.findById(RoomId));
    }

    @DeleteMapping("/{RoomId}")
    public ResponseEntity<Boolean> deleteRoom(@PathVariable String RoomId) {
        return ResponseEntity.ok(roomService.delete(RoomId));
    }

    @PutMapping()
    public ResponseEntity<RoomDTO> updateRoom(@RequestBody RoomDTO RoomDTO) {
        return ResponseEntity.ok(roomService.update(RoomDTO));
    }

    @PostMapping()
    public ResponseEntity<RoomDTO> addRoom(@RequestBody RoomDTO RoomDTO) {
        return ResponseEntity.ok(roomService.create(RoomDTO));
    }
}

