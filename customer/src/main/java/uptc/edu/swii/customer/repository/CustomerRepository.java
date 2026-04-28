package uptc.edu.swii.customer.repository;

import uptc.edu.swii.customer.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, String> {

}
