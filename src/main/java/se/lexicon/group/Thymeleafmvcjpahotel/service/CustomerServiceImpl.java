package se.lexicon.group.Thymeleafmvcjpahotel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;
import se.lexicon.group.Thymeleafmvcjpahotel.dto.CustomerDTO;
import se.lexicon.group.Thymeleafmvcjpahotel.entity.Customer;
import se.lexicon.group.Thymeleafmvcjpahotel.exceptions.ResourceNotFoundException;
import se.lexicon.group.Thymeleafmvcjpahotel.repository.CustomerRepository;
import se.lexicon.group.Thymeleafmvcjpahotel.repository.RoomRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

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
    public CustomerDTO findById(String customerId) throws ResourceNotFoundException {
        if (!customerRepo.existsById(customerId))
            throw new ResourceNotFoundException("Cannot find any customer user with id: " + customerId);
        Customer customer = customerRepo.findByCustomerId(customerId);
        return getCustomerDto(customer);
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
        Customer toCreate = new Customer();
        toCreate.setEmail(customerDTO.getEmail());
        toCreate.setFirstName(customerDTO.getFirstName());
        toCreate.setLastName(customerDTO.getLastName());
        return getCustomerDto(customerRepo.save(toCreate));
    }

    @Override
    @Transactional
    public CustomerDTO update(CustomerDTO customerDTO) {
        if (customerRepo.existsById(customerDTO.getCustomerId()))
            throw new RuntimeException("customer does not exist, please create first");
        Customer customer = customerRepo.findById(customerDTO.getCustomerId()).get();
        customer.setEmail(customerDTO.getEmail());
        customer.setFirstName(customerDTO.getFirstName());
        customer.setLastName(customerDTO.getLastName());
        return getCustomerDto(customerRepo.save(customer));
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
