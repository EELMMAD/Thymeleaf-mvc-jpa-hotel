package se.lexicon.group.Thymeleafmvcjpahotel.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

public class Booking {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    public String bookingId;

    public LocalDate bookingDate;
    public String description;

    public LocalDate createDate;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinColumn(name = "customerId")
    private Customer customer;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinColumn(name = "roomId")
    private Room room;

    public Booking() {
    }

    public Booking(String bookingId, LocalDate bookingDate, String description, LocalDate createDate, Customer customer, Room room) {
        this.bookingId = bookingId;
        this.bookingDate = bookingDate;
        this.description = description;
        this.createDate = createDate;
        this.customer = customer;
        this.room = room;
    }

    public String getBookingId() {
        return bookingId;
    }

    public LocalDate getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(LocalDate bookingDate) {
        this.bookingDate = bookingDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Booking booking = (Booking) o;
        return Objects.equals(bookingId, booking.bookingId) &&
                Objects.equals(bookingDate, booking.bookingDate) &&
                Objects.equals(description, booking.description) &&
                Objects.equals(createDate, booking.createDate) &&
                Objects.equals(customer, booking.customer) &&
                Objects.equals(room, booking.room);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookingId, bookingDate, description, createDate, customer, room);
    }

    @Override
    public String toString() {
        return "Booking{" +
                "bookingId='" + bookingId + '\'' +
                ", bookingDate=" + bookingDate +
                ", description='" + description + '\'' +
                ", createDate=" + createDate +
                ", customer=" + customer +
                ", room=" + room +
                '}';
    }
}
