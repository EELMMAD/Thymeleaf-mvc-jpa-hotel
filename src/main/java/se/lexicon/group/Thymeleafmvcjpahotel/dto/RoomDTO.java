package se.lexicon.group.Thymeleafmvcjpahotel.dto;


import se.lexicon.group.Thymeleafmvcjpahotel.entity.Room;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class RoomDTO implements Serializable {

    private String roomId;
    @NotBlank(message = "Required field")
    @Size(min = 2, max = 255, message = "Need to have at least 2 letters")
    private String code;

    private RoomTypeDTO roomType;
    private String description;
    private Boolean available;

    private Short floor;

    public boolean isAvailable() {
        return available;
    }

    public RoomDTO() {
    }

    public RoomDTO(String roomId, @NotBlank(message = "Required field") @Size(min = 2, max = 255, message = "Need to have at least 2 letters") String code,
                   RoomTypeDTO roomType, String description, Boolean available, Short floor) {
        this.roomId = roomId;
        this.code = code;
        this.roomType = roomType;
        this.description = description;
        this.available = available;
        this.floor = floor;
    }

    public RoomDTO(Room room) {
        this.roomId = room.getRoomId();
        this.code = room.getCode();
        this.floor = room.getFloor();
        this.roomType = new RoomTypeDTO(room.getRoomType());
        this.description = room.getDescription();
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public RoomTypeDTO getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomTypeDTO roomType) {
        this.roomType = roomType;
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

    public Short getFloor() {
        return floor;
    }

    public void setFloor(Short floor) {
        this.floor = floor;
    }

    public static List<RoomDTO> toRoomDto(List<Room> rooms) {
        List<RoomDTO> result = new ArrayList<>();
        for (Room room : rooms) {
            RoomDTO roomDto = new RoomDTO(room);
            result.add(roomDto);
        }
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoomDTO roomDTO = (RoomDTO) o;
        return Objects.equals(roomId, roomDTO.roomId) && Objects.equals(code, roomDTO.code) && Objects.equals(roomType, roomDTO.roomType) && Objects.equals(description, roomDTO.description) && Objects.equals(available, roomDTO.available) && Objects.equals(floor, roomDTO.floor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roomId, code, roomType, description, available, floor);
    }

    @Override
    public String toString() {
        return "RoomDTO{" +
                "roomId='" + roomId + '\'' +
                ", code='" + code + '\'' +
                ", roomType=" + roomType +
                ", description='" + description + '\'' +
                ", available=" + available +
                ", floor=" + floor +
                '}';
    }
}
