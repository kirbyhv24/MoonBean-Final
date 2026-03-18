package moonbean.service.impl;

import moonbean.app.AppContext;
import moonbean.model.CartItem;
import moonbean.model.Order;
import moonbean.model.Product; // Need this to edit the stock
import moonbean.repository.mongo.MongoOrderRepository;
import moonbean.service.OrderService;

import java.util.ArrayList;
import java.util.List;

public class OrderServiceImpl implements OrderService {
    private final MongoOrderRepository repo;

    public OrderServiceImpl(MongoOrderRepository repo) {
        this.repo = repo;
    }

    @Override
    public Order placeOrder() {
        List<CartItem> cartItems = AppContext.cartService.getItems();

        if (cartItems.isEmpty()) throw new IllegalStateException("Cart is empty.");
        if (AppContext.currentUser == null) throw new IllegalStateException("Please log in to checkout.");

        // --- NEW LOGIC: Deduct stock from the database ---
        for (CartItem item : cartItems) {
            Product p = item.getProduct();

            // Calculate what the new stock should be
            int newStock = p.getStock() - item.getQuantity();

            // Prevent buying more than what exists (Real-world safeguard!)
            if (newStock < 0) {
                throw new IllegalStateException("Not enough stock for " + p.getName());
            }

            // Set the new stock and send the update to MongoDB
            p.setStock(newStock);
            AppContext.productRepo.update(p);
        }
        // --------------------------------------------------

        // Create the final order
        Order order = new Order(
                AppContext.currentUser.getId(),
                new ArrayList<>(cartItems),
                AppContext.cartService.getTotal()
        );

        repo.save(order); // Save order permanently
        AppContext.cartService.clear(); // Empty the cart

        return order;
    }

    @Override
    public List<Order> getAllOrders() {
        return repo.findAll();
    }
}