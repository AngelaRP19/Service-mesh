package uptc.edu.swii.customer.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uptc.edu.swii.customer.dto.CustomerRequest;
import uptc.edu.swii.customer.model.Customer;
import uptc.edu.swii.customer.repository.CustomerRepository;

@Service
public class CustomerService {
    
    @Autowired
    private CustomerRepository customerRepository;

    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    public Customer findById(String document) {
        return customerRepository.findById(document).orElse(null);
    }

    public Customer save(CustomerRequest customerRequest) {
        Customer customer = new Customer(
                customerRequest.getDocument(), 
                customerRequest.getFirstname(),
                customerRequest.getLastname(), 
                customerRequest.getAddress(), 
                customerRequest.getPhone(), 
                customerRequest.getEmail()
        );
        return customerRepository.save(customer);
    }

    public Customer update(String document, CustomerRequest customerRequest) {
        Optional<Customer> optionalCustomer = customerRepository.findById(document);
        if (optionalCustomer.isPresent()) {
            Customer existingCustomer = optionalCustomer.get();
            existingCustomer.setFirstname(customerRequest.getFirstname());
            existingCustomer.setLastname(customerRequest.getLastname());
            existingCustomer.setAddress(customerRequest.getAddress());
            existingCustomer.setPhone(customerRequest.getPhone());
            existingCustomer.setEmail(customerRequest.getEmail());
            return customerRepository.save(existingCustomer);
        }
        return null;
    }

    public boolean delete(String document) {
        if (customerRepository.existsById(document)) {
            customerRepository.deleteById(document);
            return true;
        }
        return false;
    }
}

