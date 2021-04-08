package se.lexicon.group.Thymeleafmvcjpahotel.contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import se.lexicon.group.Thymeleafmvcjpahotel.dto.BookingDTO;
import se.lexicon.group.Thymeleafmvcjpahotel.service.BookingService;

import java.util.List;

@Controller
@RequestMapping("/booking")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @GetMapping()
    public ResponseEntity<List<BookingDTO>> getAllBooking() {
        return ResponseEntity.ok(bookingService.findAll());
    }

    @GetMapping("/getAllCustomersBooking/{CustomerId}")
    public ResponseEntity<List<BookingDTO>> getAllCustomersBooking(@PathVariable String CustomerId) {
        return ResponseEntity.ok(bookingService.getBookingList(CustomerId));
    }

    @GetMapping("/{BookingId}")
    public ResponseEntity<BookingDTO> getBooking(@PathVariable String BookingId) {
        return ResponseEntity.ok(bookingService.findById(BookingId));
    }

    @DeleteMapping("/{BookingId}")
    public ResponseEntity<Boolean> deleteBooking(@PathVariable String BookingId) {
        return ResponseEntity.ok(bookingService.delete(BookingId));
    }

    @PutMapping()
    public ResponseEntity<BookingDTO> updateBooking(@RequestBody BookingDTO bookingDTO) {
        return ResponseEntity.ok(bookingService.update(bookingDTO));
    }

    @PutMapping("/cancel/{BookingId}")
    public ResponseEntity<?> cancel(@PathVariable String BookingId) {
        bookingService.cancel(BookingId);
        return ResponseEntity.ok().build();
    }

    @PostMapping()
    public ResponseEntity<BookingDTO> addBooking(@RequestBody BookingDTO bookingDTO) {
        return ResponseEntity.ok(bookingService.create(bookingDTO));
    }
}

