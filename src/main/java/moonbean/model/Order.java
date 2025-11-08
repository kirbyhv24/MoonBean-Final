package moonbean.model;

import java.util.List;

public class Order {
    private static int counter = 1;  // static so it keeps counting
    private String orderId;
    private User user;
    private List<CartItem> items;
    private double total;

    public Order(User user, List<CartItem> items, double total) {
        this.orderId = String.format("ORD%03d", counter++);  // e.g., ORD001, ORD002
        this.user = user;
        this.items = items;
        this.total = total;
    }

    public String getOrderId() {
        return orderId;
    }

    public User getUser() {
        return user;
    }

    public List<CartItem> getItems() {
        return items;
    }

    public double getTotalAmount() {
        return total;
    }

    @Override
    public String toString() {
        return "Order ID: " + orderId + ", Total: ₱" + total;
    }
}
