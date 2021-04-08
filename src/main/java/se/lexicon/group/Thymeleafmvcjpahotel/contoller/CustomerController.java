package se.lexicon.group.Thymeleafmvcjpahotel.contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import se.lexicon.group.Thymeleafmvcjpahotel.dto.CustomerDTO;
import se.lexicon.group.Thymeleafmvcjpahotel.service.CustomerService;

import java.util.List;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping()
    public ResponseEntity<List<CustomerDTO>> getAllCustomer() {
        return ResponseEntity.ok(customerService.findAll());
    }

    @GetMapping("/{CustomerId}")
    public ResponseEntity<CustomerDTO> getCustomer(@PathVariable String CustomerId) {
        return ResponseEntity.ok(customerService.findById(CustomerId));
    }

    @DeleteMapping("/{CustomerId}")
    public ResponseEntity<Boolean> deleteCustomer(@PathVariable String CustomerId) {
        return ResponseEntity.ok(customerService.delete(CustomerId));
    }

    @PutMapping()
    public ResponseEntity<CustomerDTO> updateCustomer(@RequestBody CustomerDTO CustomerDTO) {
        return ResponseEntity.ok(customerService.update(CustomerDTO));
    }

    @PostMapping()
    public ResponseEntity<CustomerDTO> addCustomer(@RequestBody CustomerDTO CustomerDTO) {
        return ResponseEntity.ok(customerService.create(CustomerDTO));
    }
}

