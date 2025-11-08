package moonbean.service.impl;

import moonbean.app.AppContext;
import moonbean.model.CartItem;
import moonbean.model.Order;
import moonbean.model.User;
import moonbean.service.OrderService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class OrderServiceImpl implements OrderService {

    private final List<Order> orders = new ArrayList<>();

    @Override
    public Order placeOrder() {
        List<CartItem> cartItems = AppContext.cartService.getItems();

        if (cartItems.isEmpty()) {
            throw new IllegalStateException("Cart is empty.");
        }

        User user = AppContext.currentUser;
        BigDecimal total = BigDecimal.valueOf(AppContext.cartService.getTotal());

        // convert BigDecimal to double for Order constructor
        Order order = new Order(user, new ArrayList<>(cartItems), total.doubleValue());
        orders.add(order);

        // clear the cart
        AppContext.cartService.clear();

        return order;
    }

    @Override
    public List<Order> getAllOrders() {
        return orders;
    }
}
