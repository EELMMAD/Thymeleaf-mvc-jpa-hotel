package se.lexicon.group.Thymeleafmvcjpahotel.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.util.Assert;
import se.lexicon.group.Thymeleafmvcjpahotel.dto.BookingDTO;
import se.lexicon.group.Thymeleafmvcjpahotel.dto.CustomerDTO;
import se.lexicon.group.Thymeleafmvcjpahotel.dto.RoomDTO;
import se.lexicon.group.Thymeleafmvcjpahotel.dto.RoomTypeDTO;
import se.lexicon.group.Thymeleafmvcjpahotel.entity.Booking;
import se.lexicon.group.Thymeleafmvcjpahotel.entity.Customer;
import se.lexicon.group.Thymeleafmvcjpahotel.entity.Room;
import se.lexicon.group.Thymeleafmvcjpahotel.entity.RoomType;
import se.lexicon.group.Thymeleafmvcjpahotel.repository.BookingRepository;
import se.lexicon.group.Thymeleafmvcjpahotel.repository.CustomerRepository;
import se.lexicon.group.Thymeleafmvcjpahotel.repository.RoomRepository;
import se.lexicon.group.Thymeleafmvcjpahotel.repository.RoomTypeRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class BookingServiceImplTest {

    BookingServiceImpl testObject;
    RoomServiceImpl testObject2;
    CustomerServiceImpl testObject3;
    RoomTypeServiceImpl testObject4;

    @Autowired
    BookingRepository bookingRepository;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    RoomRepository roomRepository;
    @Autowired
    RoomTypeRepository roomTypeRepository;

    Booking booking2;
    Booking booking1;
    Customer customer1;
    Customer customer2;
    Room room1;
    Room room2;
    RoomType roomType1;
    RoomType roomType2;

    BookingDTO bookingDTO1 = new BookingDTO();
    BookingDTO bookingDTO2 = new BookingDTO();
    CustomerDTO customerDTO1 = new CustomerDTO();
    CustomerDTO customerDTO2 = new CustomerDTO();
    RoomDTO roomDTO1 = new RoomDTO();
    RoomDTO roomDTO2 = new RoomDTO();
    RoomTypeDTO roomTypeDTO1 = new RoomTypeDTO();
    RoomTypeDTO roomTypeDTO2 = new RoomTypeDTO();


    @BeforeEach
    void setUp() {
        testObject = new BookingServiceImpl(bookingRepository, customerRepository, roomRepository, roomTypeRepository);
        testObject2 = new RoomServiceImpl(roomRepository);
        testObject3 = new CustomerServiceImpl(customerRepository);

        roomType1 = new RoomType("first room type");
        roomType2 = new RoomType("second definition");

        room1 = roomRepository.save(new Room("1", "one", (short)4, "vip room", true, roomType1));
        room2 =roomRepository.save( new Room("22", "five", (short)6, "such a nice room", true, roomType2));

        customer1 = customerRepository.save(new Customer("123","elmira", "madadi", "elmiramadadi@gmail.com"));
        customer2 = customerRepository.save(new Customer("469", "Lena", "Sadr", "lenaSadr@gmail.com"));

        booking1 =bookingRepository.save(new Booking("bbb", LocalDate.of(2021, 3,28), "description", LocalDate.now(), customer1,room1));
        booking2 =bookingRepository.save( new Booking("hkl", LocalDate.of(2021, 2,20), " wonderful", LocalDate.now(), customer2,room2));

        bookingDTO1 = new BookingDTO(booking1);
        bookingDTO2 = new BookingDTO(booking2);
    }
    @Test
    void create_successfully() {
        assertNotNull(customer1);
        assertNotNull(customerDTO1);
        assertNotNull(customer2);
        assertNotNull(customerDTO2);
        assertNotNull(room1);
        assertNotNull(roomDTO1);
        assertNotNull(room2);
        assertNotNull(roomDTO2);
        assertNotNull(booking1);
        assertNotNull(bookingDTO1);
        assertNotNull(booking2);
        assertNotNull(bookingDTO2);

        assertNotNull(roomTypeRepository);
        assertNotNull(roomRepository);
        assertNotNull(customerRepository);
        assertNotNull(bookingRepository);
    }

    @Test
    void findById() {
            assertEquals(bookingDTO1, testObject.findById(booking1.getBookingId()));
            assertEquals(bookingDTO2, testObject.findById(booking2.getBookingId()));
    }

    @Test
    void findAll() {
        assertEquals(2, testObject.findAll().size());
    }

    @Test
    void create() {
        BookingDTO bookingDTO3 = new BookingDTO();
        bookingDTO3.setBookingId("000");
        bookingDTO3.setBookingDate(LocalDate.now());
        bookingDTO3.setCreateDate(LocalDate.now());

        Customer c =customerRepository.save(new Customer("119", "eli", "mira", "elimira@gmail.com") );
        CustomerDTO customerDTO3 = new CustomerDTO(c);
        bookingDTO3.setCustomer(customerDTO3);

        RoomType roomType3 = roomTypeRepository.save(new RoomType("new new"));
        Room room3 = roomRepository.save(new Room("ddd", "ppp", (short)9, "3 description", true, roomType3 ));

        RoomDTO roomDTO3 = new RoomDTO(room3);
        bookingDTO3.setRoom(roomDTO3);

        bookingDTO3 = testObject.create(bookingDTO3);
        assertEquals(3, testObject.findAll().size());
        assertTrue(testObject.findAll().contains(bookingDTO3));
    }


    @Test
    void update() {
        bookingDTO2.setBookingDate(LocalDate.of(2016, 6, 8));
        bookingDTO2 = testObject.update(bookingDTO2);
        assertEquals(LocalDate.of(2016, 6, 8), bookingDTO2.getBookingDate());

        try {
            bookingDTO2.setBookingId("000");
            bookingDTO2.setBookingDate(LocalDate.now());
            bookingDTO2.setCreateDate(LocalDate.now());
            Customer c = customerRepository.save(new Customer("119", "eli", "mira", "elimira@gmail.com"));
            CustomerDTO customerDTO1 = new CustomerDTO(c);
            bookingDTO2.setCustomer(customerDTO2);

            RoomType roomType2 = roomTypeRepository.save(new RoomType("new new"));
            Room room2 = roomRepository.save(new Room("ddd", "ppp", (short) 9, "3 description", true, roomType2));

            RoomDTO roomDTO2 = new RoomDTO(room2);
            bookingDTO2.setRoom(roomDTO2);


            bookingDTO2.setCustomer(customerDTO1);
            bookingDTO2 = testObject.update(bookingDTO2);
            assertTrue(testObject.findAll().contains(bookingDTO2));
            assertEquals(customerDTO1, bookingDTO2.getCustomer());
        }catch (RuntimeException e){}
    }

    @Test
    void delete() {
        assertTrue(testObject.delete(bookingDTO1.getBookingId()));
        assertEquals(1, testObject.findAll().size());

        assertTrue(testObject.findAll().contains(bookingDTO2));
        assertFalse(testObject.findAll().contains(bookingDTO1));
    }
}