package se.lexicon.group.Thymeleafmvcjpahotel.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Room {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String roomId;

    private String code;
    private Short floor;
    private String description;
    private Boolean available;

    @ManyToOne(cascade ={CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinColumn(name = "room_type_id",referencedColumnName = "roomTypeId")
    private RoomType roomType;

    public Room(){}

    public Room(String roomId, String code, Short floor, String description, Boolean available, RoomType roomType) {
        this.roomId = roomId;
        this.code = code;
        this.floor = floor;
        this.description = description;
        this.available = available;
        this.roomType = roomType;
    }

    public String getRoomId() {
        return roomId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Short getFloor() {
        return floor;
    }

    public void setFloor(Short floor) {
        this.floor = floor;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return Objects.equals(roomId, room.roomId) &&
                Objects.equals(code, room.code) &&
                Objects.equals(floor, room.floor) &&
                Objects.equals(description, room.description) &&
                Objects.equals(available, room.available) &&
                Objects.equals(roomType, room.roomType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roomId, code, floor, description, available, roomType);
    }

    @Override
    public String toString() {
        return "Room{" +
                "roomId='" + roomId + '\'' +
                ", code='" + code + '\'' +
                ", floor=" + floor +
                ", description='" + description + '\'' +
                ", available=" + available +
                ", roomType=" + roomType +
                '}';
    }
}
