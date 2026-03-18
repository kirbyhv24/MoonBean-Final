package moonbean.app;

import moonbean.repository.mongo.MongoProductRepository;
import javafx.stage.Stage;
import moonbean.model.Product;
import moonbean.model.User;
import moonbean.repository.memory.InMemoryCartRepository;
import moonbean.repository.mongo.MongoOrderRepository;
import moonbean.repository.mongo.MongoUserRepository;
import moonbean.service.CartService;
import moonbean.service.OrderService;
import moonbean.service.ProductService;
import moonbean.service.UserService;
import moonbean.service.impl.CartServiceImpl;
import moonbean.service.impl.OrderServiceImpl;
import moonbean.service.impl.ProductServiceImpl;
import moonbean.service.impl.UserServiceImpl;

public final class AppContext {
    private static Stage primaryStage;
    public static void setPrimaryStage(Stage s) { primaryStage = s; }
    public static Stage getPrimaryStage() { return primaryStage; }

    // This holds the currently logged-in user session
    public static User currentUser;

    // --- Repositories (Now wired to MongoDB!) ---
    public static final MongoProductRepository productRepo = new MongoProductRepository();
    public static final InMemoryCartRepository cartRepo = new InMemoryCartRepository(); // Cart stays in memory until checkout
    public static final MongoUserRepository userRepo = new MongoUserRepository();
    public static final MongoOrderRepository orderRepo = new MongoOrderRepository();

    // --- Services ---
    public static final ProductService productService = new ProductServiceImpl(productRepo);
    public static final CartService cartService = new CartServiceImpl(cartRepo);
    public static final UserService userService = new UserServiceImpl(userRepo);
    public static final OrderService orderService = new OrderServiceImpl(orderRepo);

    // --- Database Seeder ---
    static {
        // Only inject default products if the MongoDB collection is completely empty
        if (productRepo.isEmpty()) {
            // Using the new Product constructor that matches your ERD
            productRepo.save(new Product("P1", "MoonBean Arabica", "Coffee", 120.0, 999, "Milk, Sugar", true));
            productRepo.save(new Product("P2", "Espresso Blend", "Coffee", 150.0, 999, "Extra Shot", false));
            productRepo.save(new Product("P3", "Cold Brew Bottle", "Drink", 180.0, 999, "None", true));
            System.out.println("🌱 Seeded MongoDB with default products!");
        }
    }
}