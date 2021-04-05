package se.lexicon.group.Thymeleafmvcjpahotel.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import se.lexicon.group.Thymeleafmvcjpahotel.entity.Customer;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CustomerRepositoryTest {
    Customer firstCustomer;
    Customer secondCustomer;
    Customer thirdCustomer;

    @Autowired
    CustomerRepository customerRepository;

    @BeforeEach
    void setUp() {
        firstCustomer = customerRepository.save(new Customer("123", "Elmira", "Madadi", "elmiramadadi@gmail.com"));
        secondCustomer = customerRepository.save(new Customer("456", "Negar", "Madadi", "negarmadadi@gmail.com"));
        thirdCustomer = customerRepository.save(new Customer("789", "Lena", "Sadr", "lenasadr@gmail.com"));
    }

    @Test
    void successfully_created() {
        assertNotNull(firstCustomer);
        assertNotNull(secondCustomer);
        assertNotNull(thirdCustomer);
        assertNotNull(customerRepository);
    }

    @Test
    void findAll() {
        assertEquals(3, customerRepository.findAll().size());
    }

    @Test
    void findAllByCustomerFirstNameContainingIgnoreCase() {
        //Arrange
        String search = "NEGAR";

        //Act
        List<Customer> foundCustomerMatchingSearch = customerRepository.findAllByFirstNameContainingIgnoreCase(search);

        //Assert
        assertNotNull(foundCustomerMatchingSearch);
        assertFalse(foundCustomerMatchingSearch.isEmpty());
        assertEquals(1, foundCustomerMatchingSearch.size());
        assertTrue(foundCustomerMatchingSearch.contains(secondCustomer));
    }

    @Test
    void findAllByCustomerLastNameContainingIgnoreCase() {
        //Arrange
        String search = "SADR";

        //Act
        List<Customer> foundCustomerMatchingSearch = customerRepository.findAllByLastNameContainingIgnoreCase(search);

        //Assert
        assertNotNull(foundCustomerMatchingSearch);
        assertFalse(foundCustomerMatchingSearch.isEmpty());
        assertEquals(1, foundCustomerMatchingSearch.size());
    }

    @Test
    void findAllByCustomerEmailContainingIgnoreCase() {
        Customer customerFound1 = customerRepository.findAllByEmailContainingIgnoreCase("elmIraMadadi@gmail.com");
        assertEquals(firstCustomer.getCustomerId(), customerFound1.getCustomerId());
        assertEquals(firstCustomer.getFirstName(), customerFound1.getFirstName());
        assertEquals(firstCustomer.getLastName(), customerFound1.getLastName());
        assertEquals(firstCustomer.getEmail(), customerFound1.getEmail());

        Customer customerFound2 = customerRepository.findAllByEmailContainingIgnoreCase("negarmadadi@gmail.com");
        assertEquals(secondCustomer.getCustomerId(), customerFound2.getCustomerId());
        assertEquals(secondCustomer.getFirstName(), customerFound2.getFirstName());
        assertEquals(secondCustomer.getLastName(), customerFound2.getLastName());
        assertEquals(secondCustomer.getEmail(), customerFound2.getEmail());


        Customer customerFound3 = customerRepository.findAllByEmailContainingIgnoreCase("lenasadr@gmail.com");
        assertEquals(thirdCustomer.getCustomerId(), customerFound3.getCustomerId());
        assertEquals(thirdCustomer.getFirstName(), customerFound3.getFirstName());
        assertEquals(thirdCustomer.getLastName(), thirdCustomer.getLastName());
        assertEquals(thirdCustomer.getEmail(), customerFound3.getEmail());
    }

    @Test
    void findByCustomerId() {
        Customer customer = customerRepository.findByCustomerId(firstCustomer.getCustomerId());
        assertEquals(firstCustomer.getCustomerId(), customer.getCustomerId());
        assertEquals(firstCustomer.getFirstName(), customer.getFirstName());
        assertEquals(firstCustomer.getLastName(), customer.getLastName());
        assertEquals(firstCustomer.getEmail(), customer.getEmail());
        assertEquals(firstCustomer.getBookingList(), customer.getBookingList());
        assertTrue(firstCustomer.equals(customer));
    }
}




