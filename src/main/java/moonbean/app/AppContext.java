package moonbean.app;

import javafx.stage.Stage;
import moonbean.model.Product;
import moonbean.repository.memory.InMemoryProductRepository;
import moonbean.repository.memory.InMemoryUserRepository;
import moonbean.service.ProductService;
import moonbean.service.UserService;
import moonbean.service.CartService;
import moonbean.service.impl.ProductServiceImpl;
import moonbean.service.impl.UserServiceImpl;
import moonbean.service.impl.CartServiceImpl;

/**
 * Global application context for MoonBean Café.
 * Holds shared repositories, services, and the primary JavaFX stage.
 */
public final class AppContext {

    // Primary stage (for navigation)
    private static Stage primaryStage;
    public static void setPrimaryStage(Stage s) { primaryStage = s; }
    public static Stage getPrimaryStage() { return primaryStage; }

    // Repositories
    public static final InMemoryProductRepository productRepo = new InMemoryProductRepository();
    public static final InMemoryUserRepository userRepo = new InMemoryUserRepository();

    // Services
    public static final ProductService productService = new ProductServiceImpl(productRepo);
    public static final CartService cartService = new CartServiceImpl();
    public static final UserService userService = new UserServiceImpl(userRepo);

    // Seed demo products
    static {
        productRepo.save(new Product("P1", "MoonBean Arabica", "Coffee", 120.00, 999));
        productRepo.save(new Product("P2", "Espresso Blend", "Coffee", 150.00, 999));
        productRepo.save(new Product("P3", "Cold Brew Bottle", "Drink", 180.00, 999));
    }

    // Prevent instantiation
    private AppContext() {}
}
