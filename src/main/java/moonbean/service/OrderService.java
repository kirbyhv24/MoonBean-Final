package moonbean.service;

import moonbean.model.Order;

import java.util.List;

public interface OrderService {
    Order placeOrder();
    List<Order> getAllOrders();  // renamed for consistency with implementation
}
