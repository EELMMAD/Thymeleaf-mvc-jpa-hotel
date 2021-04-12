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
import se.lexicon.group.Thymeleafmvcjpahotel.exceptions.ResourceNotFoundException;
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
    void create_successfully() {
        assertNotNull(firstCustomer);
        assertNotNull(secondCustomer);
        assertNotNull(customerDTO1);
        assertNotNull(customerDTO2);
    }


    @Test
    void findAll() {
        assertEquals(2, testObject.findAll().size());
    }

    @Test
    void create() {
            CustomerDTO customerDTO3 = new CustomerDTO();
            customerDTO3.setCustomerId("858");
            customerDTO3.setFirstName("Danial");
            customerDTO3.setLastName("Kim");
            customerDTO3.setEmail("danialkim@gmail.com");
            customerDTO3 = testObject.create(customerDTO3);

            assertEquals(3, testObject.findAll().size());
    }

    @Test
    void update() {
            customerDTO2.setFirstName("Johanna");
            customerDTO2.setLastName("Gustavsoon");
            customerDTO2.setEmail("johannagustavsoon@gmail.com");
            testObject.update(customerDTO2);
            assertEquals("Johanna", customerDTO2.getFirstName());
            assertEquals("Gustavsoon", customerDTO2.getLastName());
            assertEquals("johannagustavsoon@gmail.com", customerDTO2.getEmail());
    }

    @Test
    void delete() {
        try {
            assertTrue(testObject.delete(customerDTO1.getCustomerId()));
            assertEquals(1, testObject.findAll().size());

            assertTrue(testObject.findAll().contains(customerDTO2));
            assertFalse(testObject.findAll().contains(customerDTO1));
        } catch (ResourceNotFoundException e) {
        }
    }
}