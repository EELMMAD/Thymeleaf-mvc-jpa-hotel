package se.lexicon.group.Thymeleafmvcjpahotel.dto;

import se.lexicon.group.Thymeleafmvcjpahotel.entity.RoomType;

import java.io.Serializable;
import java.util.Objects;

public class RoomTypeDTO implements Serializable {

    private String roomTypeId;
    private String description;

    public RoomTypeDTO() {
    }

    public RoomTypeDTO(String roomTypeId, String description) {
        this.roomTypeId = roomTypeId;
        this.description = description;
    }

    public RoomTypeDTO(RoomType roomType) {
        this.roomTypeId = roomType.getRoomTypeId();
        this.description = roomType.getDescription();
    }

    public String getRoomTypeId() {
        return roomTypeId;
    }

    public void setRoomTypeId(String roomTypeId) {
        this.roomTypeId = roomTypeId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoomTypeDTO that = (RoomTypeDTO) o;
        return Objects.equals(roomTypeId, that.roomTypeId) && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roomTypeId, description);
    }

    @Override
    public String toString() {
        return "RoomTypeDTO{" +
                "roomTypeId='" + roomTypeId + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}

