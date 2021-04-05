package se.lexicon.group.Thymeleafmvcjpahotel.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import se.lexicon.group.Thymeleafmvcjpahotel.entity.Booking;
import se.lexicon.group.Thymeleafmvcjpahotel.entity.Customer;
import se.lexicon.group.Thymeleafmvcjpahotel.entity.Room;
import se.lexicon.group.Thymeleafmvcjpahotel.entity.RoomType;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class BookingRepositoryTest {
    RoomType firstRoomType;
    RoomType secondRoomType;

    Room firstRoom;
    Room secondRoom;

    Customer firstCustomer;
    Customer secondCustomer;

    Booking firstBooking;
    Booking secondBooking;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    BookingRepository bookingRepository;

    @BeforeEach
    void setUp() {

        firstRoomType = new RoomType("First room type description");
        secondRoomType = new RoomType("Second room type description");

        firstRoom = new Room("123", "555", (short) 2, "Good Room", true, firstRoomType);
        secondRoom = new Room("678", "999", (short) 4, "The best room", true, secondRoomType);

        firstCustomer = customerRepository.save(new Customer("123", "Elmira", "Madadi", "elmiramadadi@gmail.com"));
        secondCustomer = customerRepository.save(new Customer("456", "Negar", "Madadi", "negarmadadi@gmail.com"));

        firstBooking = bookingRepository.save(new Booking("22", LocalDate.of(2021, 1, 24), "My description",
                LocalDate.of(2021, 02, 01), firstCustomer, firstRoom));


        secondBooking = bookingRepository.save(new Booking("77", LocalDate.of(2021, 2, 19), "Second description",
                LocalDate.of(2021, 3, 19), secondCustomer, secondRoom));
    }

    @Test
    void successfully_created() {
        assertNotNull(firstRoomType);
         assertNotNull(secondRoomType);
        assertNotNull(firstRoom);
        assertNotNull(secondRoom);
        assertNotNull(firstCustomer);
        assertNotNull(secondCustomer);
        assertNotNull(firstBooking);
        assertNotNull(secondBooking);
        assertNotNull(bookingRepository);
        assertNotNull(customerRepository);
    }

    @Test
    void findAll() {
        assertEquals(2, bookingRepository.findAll().size());
    }

    @Test
    void findByBookingId() {
        Booking booking = bookingRepository.findByBookingId(firstBooking.getBookingId());
        assertEquals(firstBooking.getBookingId(), booking.getBookingId());
        assertEquals(firstBooking.getDescription(), booking.getDescription());
        assertEquals(firstBooking.getCreateDate(), booking.getCreateDate());
        assertEquals(firstBooking.getBookingDate(), booking.getBookingDate());
        assertTrue(firstBooking.equals(booking));
    }

    @Test
    void findAllByCustomer_CustomerId() {
        List<Booking> firstOne = bookingRepository.findAllByCustomerCustomerId(firstCustomer.getCustomerId());
        List<Booking> secondOne = bookingRepository.findAllByCustomerCustomerId(secondCustomer.getCustomerId());

        assertNotNull(firstOne);
        assertNotNull(secondOne);
       // assertEquals(2, found1.size());
        assertTrue(firstOne.contains(firstBooking));
        assertTrue(secondOne.contains(secondBooking));
        assertEquals(1, secondOne.size());
       // assertTrue(found2.contains(loan3));
    }
}




