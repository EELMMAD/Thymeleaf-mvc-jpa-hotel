package se.lexicon.group.Thymeleafmvcjpahotel.service;

import se.lexicon.group.Thymeleafmvcjpahotel.dto.CustomerDTO;

import java.util.List;

public interface CustomerService {
    CustomerDTO findById(String customerId);
    CustomerDTO findByEmail(String customerEmail);
    List<CustomerDTO> findAll();
    CustomerDTO create(CustomerDTO customerDTO);
    CustomerDTO update(CustomerDTO customerDTO);
    boolean delete(String customerId);
}
