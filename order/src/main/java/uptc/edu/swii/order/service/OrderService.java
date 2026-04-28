package uptc.edu.swii.order.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uptc.edu.swii.order.model.Order;
import uptc.edu.swii.order.repository.OrderRepository;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository){
        this.orderRepository = orderRepository;
    }

    public Order save(Order order){
        return orderRepository.save(order);
    }

    public List<Order> findAll(){
        return orderRepository.findAll();
    }
    
    public Order findById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    public List<Order> findByCustomerId(String customerid){
        return orderRepository.findByCustomerid(customerid);
    }

    public Order update(Long id, Order orderDetails) {
        Optional<Order> optionalOrder = orderRepository.findById(id);
        if (optionalOrder.isPresent()) {
            Order existingOrder = optionalOrder.get();
            existingOrder.setCustomerid(orderDetails.getCustomerid());
            existingOrder.setStatus(orderDetails.getStatus());
            return orderRepository.save(existingOrder);
        }
        return null;
    }

    public boolean delete(Long id) {
        if (orderRepository.existsById(id)) {
            orderRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
