package moonbean.model;

import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.types.ObjectId;
import java.util.Date;
import java.util.List;
import java.util.UUID; // New import for uniqueness

public class Order {

    @BsonId
    private String orderId; // Changed to String for custom IDs

    private ObjectId userId;
    private Date date;
    private String status;
    private double totalAmount;
    private List<CartItem> items;

    // 1. Empty constructor
    public Order() {}

    // 2. Updated Constructor
    public Order(ObjectId userId, List<CartItem> items, double totalAmount) {
        this.userId = userId;
        this.items = items;
        this.totalAmount = totalAmount;
        this.date = new Date();
        this.status = "Pending";

        // --- CUSTOM UNIQUE ID GENERATION ---
        // This creates a unique ID like "MB-7F3A2B"
        this.orderId = "MB-" + UUID.randomUUID().toString().substring(0, 6).toUpperCase();
    }

    // --- Getters & Setters ---
    public String getOrderId() { return orderId; }
    public void setOrderId(String orderId) { this.orderId = orderId; }

    public ObjectId getUserId() { return userId; }
    public void setUserId(ObjectId userId) { this.userId = userId; }

    public Date getDate() { return date; }
    public void setDate(Date date) { this.date = date; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public double getTotalAmount() { return totalAmount; }
    public void setTotalAmount(double totalAmount) { this.totalAmount = totalAmount; }

    public List<CartItem> getItems() { return items; }
    public void setItems(List<CartItem> items) { this.items = items; }
}