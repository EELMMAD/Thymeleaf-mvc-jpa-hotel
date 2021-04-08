package se.lexicon.group.Thymeleafmvcjpahotel.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import se.lexicon.group.Thymeleafmvcjpahotel.dto.CustomerDTO;
import se.lexicon.group.Thymeleafmvcjpahotel.dto.RoomDTO;
import se.lexicon.group.Thymeleafmvcjpahotel.dto.RoomTypeDTO;
import se.lexicon.group.Thymeleafmvcjpahotel.entity.Customer;
import se.lexicon.group.Thymeleafmvcjpahotel.entity.Room;
import se.lexicon.group.Thymeleafmvcjpahotel.entity.RoomType;
import se.lexicon.group.Thymeleafmvcjpahotel.repository.CustomerRepository;
import se.lexicon.group.Thymeleafmvcjpahotel.repository.RoomRepository;
import se.lexicon.group.Thymeleafmvcjpahotel.repository.RoomTypeRepository;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CustomerServiceImplTest {
    CustomerServiceImpl testObject;

    Customer firstCustomer;
    Customer secondCustomer;

    CustomerDTO customerDTO1 = new CustomerDTO();
    CustomerDTO customerDTO2 = new CustomerDTO();

    @Autowired
    CustomerRepository customerRepository;

    @BeforeEach
    void setUp() {
        testObject = new CustomerServiceImpl(customerRepository);

        firstCustomer = new Customer("123", "Elmira", "Madadi", "elmiramadado@gmail.com");
        customerRepository.save(firstCustomer);
        customerDTO1 = new CustomerDTO(firstCustomer);

        secondCustomer = customerRepository.save(new Customer("444", "Lena", "Sadr", "lenasadr@gmail.com"));
        customerDTO2 = new CustomerDTO(secondCustomer);

    }

    @Test
    void create_successfully(){
        assertNotNull(firstCustomer);
        assertNotNull(secondCustomer);
       assertNotNull(customerDTO1);
        assertNotNull(customerDTO2);

    }

    @Test
    void getCustomerDto() {
    }

    @Test
    void testGetCustomerDto() {
    }

    @Test
    void findById() {

    }

    @Test
    void findByEmail() {

    }

    @Test
    void findAll() {
        assertEquals(2, testObject.findAll().size());
    }

    @Test
    void create() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }
}