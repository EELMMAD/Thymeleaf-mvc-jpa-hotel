package se.lexicon.group.Thymeleafmvcjpahotel.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.Objects;

@Entity
public class RoomType {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String roomTypeId;

    private String description;

    public RoomType() {
    }
    public RoomType( String description) {
        // this.roomTypeId = roomTypeId;
        this.description = description;
    }

    public String getRoomTypeId() {
        return roomTypeId;
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
        RoomType roomType = (RoomType) o;
        return Objects.equals(roomTypeId, roomType.roomTypeId) && Objects.equals(description, roomType.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roomTypeId, description);
    }

    @Override
    public String toString() {
        return "RoomType{" +
                "roomTypeId='" + roomTypeId + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
