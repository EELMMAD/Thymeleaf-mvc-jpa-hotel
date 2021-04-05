package se.lexicon.group.Thymeleafmvcjpahotel.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import se.lexicon.group.Thymeleafmvcjpahotel.entity.Customer;

import java.util.List;


public interface CustomerRepository extends CrudRepository<Customer, String> {
    List<Customer> findAll();

    List<Customer> findAllByFirstNameContainingIgnoreCase(String FirstName);

    List<Customer> findAllByLastNameContainingIgnoreCase(String customerLastName);

    Customer findAllByEmailContainingIgnoreCase(String customerEmail);

    Customer findByCustomerId(String customerId);
}



