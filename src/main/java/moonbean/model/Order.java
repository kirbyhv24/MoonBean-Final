package moonbean.model;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public class Order {
    private final String orderId;
    private final User user;
    private final List<CartItem> items;
    private final BigDecimal totalAmount;
    private String status;

    public Order(User user, List<CartItem> items, BigDecimal totalAmount) {
        this.orderId = UUID.randomUUID().toString().substring(0, 8);
        this.user = user;
        this.items = items;
        this.totalAmount = totalAmount;
        this.status = "PAID";
    }

    public String getOrderId() { return orderId; }
    public User getUser() { return user; }
    public List<CartItem> getItems() { return items; }
    public BigDecimal getTotalAmount() { return totalAmount; }
    public String getStatus() { return status; }

    public void setStatus(String status) { this.status = status; }

    @Override
    public String toString() {
        return "Order{" +
                "orderId='" + orderId + '\'' +
                ", user=" + (user != null ? user.getEmail() : "guest") +
                ", totalAmount=" + totalAmount +
                ", status='" + status + '\'' +
                '}';
    }
}
