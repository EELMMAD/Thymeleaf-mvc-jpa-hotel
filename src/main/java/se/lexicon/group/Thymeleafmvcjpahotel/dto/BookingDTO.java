package se.lexicon.group.Thymeleafmvcjpahotel.dto;


import se.lexicon.group.Thymeleafmvcjpahotel.entity.Booking;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.PastOrPresent;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class BookingDTO implements Serializable {
    @Null(message = "booking id in necessary")
    private String bookingId;

    @NotNull(message = "customer which booked the room in necessary")
    private CustomerDTO customer;

    @NotNull(message = "booking room is not set")
    private RoomDTO room;

    @NotNull(message = "Required field")
    @PastOrPresent(message = "bookingDate cannot be in the past")
    private LocalDate bookingDate;
    @NotNull(message = "Required field")
    @PastOrPresent(message = "bookingDate cannot be in the past")
    private LocalDate createDate;

    public BookingDTO() {
    }

    public BookingDTO(@Null(message = "booking id in necessary") String bookingId, @NotNull(message = "customer which booked the room in necessary") CustomerDTO customer, @NotNull(message = "booking room is not set") RoomDTO room, @NotNull(message = "Required field") @PastOrPresent(message = "bookingDate cannot be in the past") LocalDate bookingDate, @NotNull(message = "Required field") @PastOrPresent(message = "bookingDate cannot be in the past") LocalDate createDate) {
        this.bookingId = bookingId;
        this.customer = customer;
        this.room = room;
        this.bookingDate = bookingDate;
        this.createDate = createDate;
    }

    public BookingDTO(Booking booking) {
        this.bookingId = booking.getBookingId();
        this.customer = new CustomerDTO(booking.getCustomer());
        this.room = new RoomDTO(booking.getRoom());
        this.bookingDate = booking.getBookingDate();
        this.createDate = booking.getCreateDate();
    }

    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public CustomerDTO getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDTO customer) {
        this.customer = customer;
    }

    public RoomDTO getRoom() {
        return room;
    }

    public void setRoom(RoomDTO room) {
        this.room = room;
    }

    public LocalDate getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(LocalDate bookingDate) {
        this.bookingDate = bookingDate;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookingDTO that = (BookingDTO) o;
        return Objects.equals(bookingId, that.bookingId) &&
                Objects.equals(customer, that.customer) &&
                Objects.equals(room, that.room) &&
                Objects.equals(bookingDate, that.bookingDate) &&
                Objects.equals(createDate, that.createDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookingId, customer, room, bookingDate, createDate);
    }

    @Override
    public String toString() {
        return "BookingDTO{" +
                "bookingId='" + bookingId + '\'' +
                ", customer=" + customer +
                ", room=" + room +
                ", bookingDate=" + bookingDate +
                ", createDate=" + createDate +
                '}';
    }
}

