package se.lexicon.group.Thymeleafmvcjpahotel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;
import se.lexicon.group.Thymeleafmvcjpahotel.dto.CustomerDTO;
import se.lexicon.group.Thymeleafmvcjpahotel.dto.RoomDTO;
import se.lexicon.group.Thymeleafmvcjpahotel.entity.Customer;
import se.lexicon.group.Thymeleafmvcjpahotel.entity.Room;
import se.lexicon.group.Thymeleafmvcjpahotel.entity.RoomType;
import se.lexicon.group.Thymeleafmvcjpahotel.exceptions.ResourceNotFoundException;
import se.lexicon.group.Thymeleafmvcjpahotel.repository.CustomerRepository;
import se.lexicon.group.Thymeleafmvcjpahotel.repository.RoomRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Configurable
public class CustomerServiceImpl implements CustomerService {
    CustomerRepository customerRepo;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepo = customerRepository;
    }

    protected CustomerDTO getCustomerDto(Customer customer) {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstName(customer.getFirstName());
        customerDTO.setLastName(customer.getLastName());
        customerDTO.setEmail(customer.getEmail());
        return customerDTO;
    }

    protected List<CustomerDTO> getCustomerDto(List<Customer> customers) {
        List<CustomerDTO> results = new ArrayList<>();
        for (Customer l : customers){
            CustomerDTO customerDTO = getCustomerDto(l);
            results.add(customerDTO);
        } return results;
    }

    @Override
    public CustomerDTO findById(String customerId) throws RuntimeException {
        if (!customerRepo.existsById(customerId))
            throw new RuntimeException("There is no customer with id: " + customerId);
        Customer customer = customerRepo.findByCustomerId(customerId);
        return new CustomerDTO(customer);
    }

    @Override
    public CustomerDTO findByEmail(String customerEmail) throws IllegalArgumentException, ResourceNotFoundException{
        if (customerEmail == null)
            throw new IllegalArgumentException("Email should not be empty");
        Customer customer=customerRepo.findAllByEmailContainingIgnoreCase(customerEmail);
        if ( customer== null)
            throw new ResourceNotFoundException("Cannot find any customer user with email: " + customerEmail);
        return getCustomerDto(customer);
    }

    @Override
    public List<CustomerDTO> findAll() {
        List<Customer> result = customerRepo.findAll();
        return getCustomerDto(result);
    }

    @Override
    @Transactional
    public CustomerDTO create(CustomerDTO customerDTO) throws RuntimeException {
        if (customerRepo.existsById(customerDTO.getCustomerId()))
            throw new RuntimeException("Customer already exists, please update");
        Customer toCreate = new Customer(customerDTO.getCustomerId(), customerDTO.getFirstName(), customerDTO.getLastName(), customerDTO.getEmail());
        return new CustomerDTO(customerRepo.save(toCreate));
    }

    @Override
    @Transactional
    public CustomerDTO update(CustomerDTO customerDTO) throws RuntimeException{
            Optional<Customer> optionalCustomer = customerRepo.findById(customerDTO.getCustomerId());
            if (!optionalCustomer.isPresent())
                throw new RuntimeException("Customer doesn't exist");
            Customer toUpdated = optionalCustomer.get();
            if (!toUpdated.getFirstName().equals(customerDTO.getFirstName()))
                toUpdated.setFirstName(customerDTO.getFirstName());

        if (!toUpdated.getLastName().equals(customerDTO.getLastName()))
            toUpdated.setLastName(customerDTO.getLastName());

            if (!toUpdated.getEmail().equals(customerDTO.getEmail()))
                toUpdated.setEmail(customerDTO.getEmail());

            return new CustomerDTO(customerRepo.save(toUpdated));
    }

    @Override
    @Transactional
    public boolean delete(String customerId) {
        if (!customerRepo.findById(customerId).isPresent())
            throw new ResourceNotFoundException("Can not find the customer with id: " + customerId);
        boolean deleted = false;
        if (customerRepo.existsById(customerId)){
            customerRepo.delete(customerRepo.findById(customerId).get());
            deleted = true;
        }
        return deleted;
    }
}
