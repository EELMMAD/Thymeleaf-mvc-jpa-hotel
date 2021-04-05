package se.lexicon.group.Thymeleafmvcjpahotel.dto;


import se.lexicon.group.Thymeleafmvcjpahotel.entity.Customer;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CustomerDTO implements Serializable {
    private String customerId;

    @NotBlank(message = "Required field")
    @Size(min = 2, max = 255, message = "Need to have at least 2 letters")
    private String firstName;
    @NotBlank(message = "Required field")
    @Size(min = 2, max = 255, message = "Need to have at least 2 letters")
    private String lastName;
    @NotBlank(message = "Required field")
    @Email(regexp = "^(\\D)+(\\w)*((\\.(\\w)+)?)+@(\\D)+(\\w)*((\\.(\\D)+(\\w)*)+)?(\\.)[a-z]{2,}$", flags = Pattern.Flag.CASE_INSENSITIVE, message = "Email format is invalid")
    private String email;

    public CustomerDTO() {
    }

    public CustomerDTO(String customerId, @NotBlank(message = "Required field") @Size(min = 2, max = 255, message = "Need to have at least 2 letters") String firstName, @NotBlank(message = "Required field") @Size(min = 2, max = 255, message = "Need to have at least 2 letters") String lastName, @NotBlank(message = "Required field") @Email(regexp = "^(\\D)+(\\w)*((\\.(\\w)+)?)+@(\\D)+(\\w)*((\\.(\\D)+(\\w)*)+)?(\\.)[a-z]{2,}$", flags = Pattern.Flag.CASE_INSENSITIVE, message = "Email format is invalid") String email) {
        this.customerId = customerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }
    public CustomerDTO(Customer customer)
    {
        this.customerId = customer.getCustomerId();
        this.firstName = customer.getFirstName();
        this.lastName = customer.getLastName();
        this.email = customer.getEmail();
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    //converting  list of Customer to list of CustomerDTO
    public static List<CustomerDTO> toCustomerDto(List<Customer> customers) {
        List<CustomerDTO> result = new ArrayList<>();
        for (Customer customer : customers) {
            CustomerDTO customerDTO = new CustomerDTO(customer);
            result.add(customerDTO);
        }
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerDTO that = (CustomerDTO) o;
        return Objects.equals(customerId, that.customerId) && Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName) && Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerId, firstName, lastName, email);
    }

    @Override
    public String toString() {
        return "CustomerDTO{" +
                "customerId='" + customerId + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}

