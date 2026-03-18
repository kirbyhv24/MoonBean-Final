package moonbean.model;

import org.bson.codecs.pojo.annotations.BsonId;

public class Product {

    @BsonId
    private String productId; // Renamed to match ERD

    private String name;
    private String category;
    private double price;
    private int stock;

    // --- New fields from ERD ---
    private String customizationOptions;
    private boolean subscriptionAvailable;

    // 1. Empty constructor (Required by MongoDB)
    public Product() {}

    // 2. Full constructor for creating products
    public Product(String productId, String name, String category, double price, int stock, String customizationOptions, boolean subscriptionAvailable) {
        this.productId = productId;
        this.name = name;
        this.category = category;
        this.price = price;
        this.stock = stock;
        this.customizationOptions = customizationOptions;
        this.subscriptionAvailable = subscriptionAvailable;
    }

    // --- Getters & Setters ---
    public String getProductId() { return productId; }
    public void setProductId(String productId) { this.productId = productId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }

    public String getCustomizationOptions() { return customizationOptions; }
    public void setCustomizationOptions(String customizationOptions) { this.customizationOptions = customizationOptions; }

    public boolean isSubscriptionAvailable() { return subscriptionAvailable; }
    public void setSubscriptionAvailable(boolean subscriptionAvailable) { this.subscriptionAvailable = subscriptionAvailable; }
}