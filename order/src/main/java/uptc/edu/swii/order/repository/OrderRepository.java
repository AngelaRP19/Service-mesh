package uptc.edu.swii.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uptc.edu.swii.order.model.Order;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByCustomerid(String customerid);

}