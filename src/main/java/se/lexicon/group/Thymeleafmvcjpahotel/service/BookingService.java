package se.lexicon.group.Thymeleafmvcjpahotel.service;

import se.lexicon.group.Thymeleafmvcjpahotel.dto.BookingDTO;

import java.util.List;

public interface BookingService {
    BookingDTO findById(String loanId);
    List<BookingDTO> findAll();
    BookingDTO create(BookingDTO bookingDTO);

    void cancel(String bookingId);

    List<BookingDTO> getBookingList(String customerId);

    BookingDTO update(BookingDTO bookingDTO);
    boolean delete(String bookingId);
}
