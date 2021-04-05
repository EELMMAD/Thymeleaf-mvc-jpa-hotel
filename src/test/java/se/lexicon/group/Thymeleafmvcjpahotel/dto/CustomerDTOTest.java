package se.lexicon.group.Thymeleafmvcjpahotel.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.lexicon.group.Thymeleafmvcjpahotel.entity.Customer;
import se.lexicon.group.Thymeleafmvcjpahotel.entity.Room;
import se.lexicon.group.Thymeleafmvcjpahotel.entity.RoomType;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class CustomerDTOTest {

    Customer firstCustomer;
    Customer secondCustomer;
    List<Customer> customerList;
    CustomerDTO customerDTO1;
    CustomerDTO customerDTO2;

    @BeforeEach
    void setUp() {

        RoomType roomType = new RoomType( "room type description");
        Room room = new Room("555", "asd", (short) 2, "room description", true, roomType);

        firstCustomer = new Customer("123", "Negar", "Madadi", "negar.madadi@gmail.com");

        firstCustomer = new Customer("1122", "Negar", "Madadi", "negar.madadi@gmail.com");
        secondCustomer = new Customer("5678", "Lena", "Sadr", "lenasadr@gmailcom");
        customerDTO1 = new CustomerDTO(firstCustomer);
        customerDTO2 = new CustomerDTO(secondCustomer);
        customerList = new ArrayList<>();
        customerList.add(firstCustomer);
        customerList.add(secondCustomer);
    }

    @Test
    void if_converts_List_of_customer_to_List_of_customerDTOs() {
        List<CustomerDTO> customerDTOS = CustomerDTO.toCustomerDto(customerList);
        assertEquals(customerList.size(), customerDTOS.size());
    }

    @Test
    void if_convert_Customer_toCustomerDto() {
        assertEquals(firstCustomer.getCustomerId(), customerDTO1.getCustomerId());
        assertEquals(firstCustomer.getFirstName(), customerDTO1.getFirstName());
        assertEquals(firstCustomer.getLastName(), customerDTO1.getLastName());
        assertEquals(firstCustomer.getEmail(), customerDTO1.getEmail());
        assertEquals(secondCustomer.getLastName(), customerDTO2.getLastName());
    }
}