package se.lexicon.group.Thymeleafmvcjpahotel.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import se.lexicon.group.Thymeleafmvcjpahotel.entity.Booking;
import java.util.List;

public interface BookingRepository extends CrudRepository<Booking, String> {

    List<Booking> findAll();

    List<Booking> findAllByCustomerCustomerId(String customerId);

    Booking findByBookingId(String bookId);
}



