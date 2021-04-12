package se.lexicon.group.Thymeleafmvcjpahotel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;
import se.lexicon.group.Thymeleafmvcjpahotel.dto.BookingDTO;
import se.lexicon.group.Thymeleafmvcjpahotel.entity.Booking;
import se.lexicon.group.Thymeleafmvcjpahotel.entity.Customer;
import se.lexicon.group.Thymeleafmvcjpahotel.entity.Room;
import se.lexicon.group.Thymeleafmvcjpahotel.repository.BookingRepository;
import se.lexicon.group.Thymeleafmvcjpahotel.repository.CustomerRepository;
import se.lexicon.group.Thymeleafmvcjpahotel.repository.RoomRepository;
import se.lexicon.group.Thymeleafmvcjpahotel.repository.RoomTypeRepository;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Configurable
public class BookingServiceImpl implements BookingService {

    BookingRepository bookingRepository;
    CustomerRepository customerRepository;
    RoomRepository roomRepository;
    RoomTypeRepository roomTypeRepository;

    @Autowired
    public BookingServiceImpl(BookingRepository bookingRepo,  CustomerRepository customerRepo,
                              RoomRepository roomRepo,  RoomTypeRepository roomTypeRepo){
        this.bookingRepository = bookingRepo;
        this.customerRepository = customerRepo;
        this.roomRepository = roomRepo;
        this.roomTypeRepository = roomTypeRepo;
    }

    @Transactional
    public Customer getCustomer(String customerId) {
        return customerRepository.findByCustomerId(customerId);
    }

    @Transactional
    public Room getRoom(String roomId) {
        return roomRepository.findByRoomId(roomId);
    }

    @Override
    public BookingDTO findById(String bookingId) {

        Booking booking = bookingRepository.findById(bookingId).orElseThrow(() -> new RuntimeException(
                bookingId + " id does not match with any Booking"));
        return new BookingDTO(booking);
    }

    @Override
    public List<BookingDTO> findAll() {
        List<Booking> bookingLists = bookingRepository.findAll();
        if(bookingLists.isEmpty()) throw new RuntimeException("Could not find any booking");
      return BookingDTO.toBookingDTOs(bookingLists);
    }

    @Override
    @Transactional
    public BookingDTO create(BookingDTO bookingDTO) {
        if (bookingDTO.getBookingId().equals("0") || bookingDTO.getBookingId().equals(""))
            throw new RuntimeException("booking id is invalid");
        Booking booking=new Booking();
        booking.setCustomer(getCustomer(bookingDTO.getCustomer().getCustomerId()));
        Room room=getRoom(bookingDTO.getRoom().getRoomId());
       if (!room.getAvailable())
            throw new RuntimeException("room id is full");
        booking.setRoom(room);
        booking.setCreateDate(LocalDate.now());
        booking.setBookingDate(bookingDTO.getBookingDate());
        return new BookingDTO(bookingRepository.save(booking));
    }

    @Override
    public void cancel(String bookingId) {
        Booking booking=bookingRepository.findByBookingId(bookingId);
        if (booking==null)
            throw new RuntimeException("booking id is invalid");

        Room room=booking.getRoom();
        room.setAvailable(true);
        roomRepository.save(room);
    }

    @Override
    public List<BookingDTO> getBookingList(String customerId) {
        List<Booking> bookingList = bookingRepository.findAllByCustomerCustomerId(customerId);
        List<BookingDTO> bookingDTO=new ArrayList<BookingDTO>();
        for (Booking booking: bookingList)
            bookingDTO.add(new BookingDTO(booking));
        return bookingDTO;
    }

    @Override
    @Transactional
    public BookingDTO update(BookingDTO bookingDTO) {
        Optional<Booking> optionalBooking = bookingRepository.findById(bookingDTO.getBookingId());
        if (!optionalBooking.isPresent())
            throw new RuntimeException("customer doesn't exist");
        Booking toUpdated = optionalBooking.get();

        toUpdated.setBookingDate(bookingDTO.getBookingDate());
        return new BookingDTO(bookingRepository.save(toUpdated));
    }

    @Override
    @Transactional
    public boolean delete(String bookingId) {
        if (!bookingRepository.findById(bookingId).isPresent())
            throw new RuntimeException("There is no booking with id: " + bookingId);
        boolean deleted = false;
        if (bookingRepository.existsById(bookingId)) {
            bookingRepository.delete(bookingRepository.findById(bookingId).get());
            deleted = true;
        }
        return deleted;
    }
    }
