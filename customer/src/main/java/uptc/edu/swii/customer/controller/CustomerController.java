package uptc.edu.swii.customer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import uptc.edu.swii.customer.dto.CustomerRequest;
import uptc.edu.swii.customer.model.Customer;
import uptc.edu.swii.customer.service.CustomerService;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers() {
        return ResponseEntity.ok(customerService.findAll());
    }

    @GetMapping("/{document}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable String document) {
        Customer customer = customerService.findById(document);
        return customer != null ? ResponseEntity.ok(customer) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Customer> createCustomer(@RequestBody CustomerRequest customerRequest) {
        Customer createdCustomer = customerService.save(customerRequest);
        return ResponseEntity.ok(createdCustomer);
    }

    @PutMapping("/{document}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable String document, @RequestBody CustomerRequest customerRequest) {
        Customer updatedCustomer = customerService.update(document, customerRequest);
        return updatedCustomer != null ? ResponseEntity.ok(updatedCustomer) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{document}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable String document) {
        boolean deleted = customerService.delete(document);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}


