package se.lexicon.group.Thymeleafmvcjpahotel.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.lexicon.group.Thymeleafmvcjpahotel.entity.Customer;
import se.lexicon.group.Thymeleafmvcjpahotel.entity.Room;
import se.lexicon.group.Thymeleafmvcjpahotel.entity.RoomType;
import java.time.LocalDate;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class BookingDTOTest {
    Room firstRoom;
    Room secondRoom;
    RoomType firstRoomType;
    RoomType secondRoomType;
    RoomDTO firstRoomDTO;
    RoomDTO secondRoomDTO;

    Customer firstCustomer;
    Customer secondCustomer;
    List<Customer> customerList;
    CustomerDTO firstCustomerDTO;
    CustomerDTO secondCustomerDto;
    BookingDTO bookingDTO;

    @BeforeEach
    void setUp() {
        firstRoomType = new RoomType( "firstRoomDescription");
        secondRoomType = new RoomType( "secondRoomDescription");

        firstRoom = new Room("102", "aa", (short)4, "Your hotel room is smarter than you", true, firstRoomType);
        secondRoom = new Room("100", "bb", (short)8, "Second room description",  true, secondRoomType);

        firstRoomDTO = new RoomDTO(firstRoom);
        secondRoomDTO = new RoomDTO(secondRoom);

        firstCustomer = new Customer("1122", "Negar", "Madadi", "negar.madadi@gmail.com");
        secondCustomer = new Customer("5678", "Lena", "Sadr", "lenasadr@gmailcom");
        firstCustomerDTO = new CustomerDTO(firstCustomer);
        secondCustomerDto = new CustomerDTO(secondCustomer);

        bookingDTO = new BookingDTO("109", firstCustomerDTO, firstRoomDTO, LocalDate.of(2021, 03, 12), LocalDate.now());
    }

    @Test
    void successfully_created(){
        assertNotNull(firstRoomType);
        assertNotNull(secondRoomType);
        assertNotNull(firstRoom);
        assertNotNull(secondRoom);
        assertNotNull(firstRoomDTO);
        assertNotNull(secondRoomDTO);
        assertNotNull(firstCustomer);
        assertNotNull(secondCustomer);
        assertNotNull(firstCustomerDTO);
        assertNotNull(secondCustomerDto);
        assertNotNull(bookingDTO);
    }

    @Test
    void creating_new_bookingDto_object() {
        assertEquals(firstRoomDTO.getRoomId(), bookingDTO.getRoom().getRoomId());
        assertEquals(firstRoomDTO.getDescription(), bookingDTO.getRoom().getDescription());
        assertEquals(firstRoomDTO.getRoomType(), bookingDTO.getRoom().getRoomType());
        assertEquals(firstRoomDTO.getCode(), bookingDTO.getRoom().getCode());
        assertEquals(firstRoomDTO.getFloor(), bookingDTO.getRoom().getFloor());
        assertEquals(firstRoomDTO.getAvailable(), bookingDTO.getRoom().getAvailable());
        assertEquals(firstCustomerDTO.getFirstName(), bookingDTO.getCustomer().getFirstName());
        assertEquals(firstCustomerDTO.getLastName(), bookingDTO.getCustomer().getLastName());
        assertEquals(firstCustomerDTO.getEmail(), bookingDTO.getCustomer().getEmail());
        assertEquals(firstCustomerDTO.getCustomerId(), bookingDTO.getCustomer().getCustomerId());
    }
}