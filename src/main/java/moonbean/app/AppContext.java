package moonbean.app;

import javafx.stage.Stage;
import moonbean.model.Product;
import moonbean.model.User;
import moonbean.repository.memory.InMemoryProductRepository;
import moonbean.repository.memory.InMemoryUserRepository;
import moonbean.service.*;
import moonbean.service.impl.*;

public final class AppContext {

    // ---- Repositories ----
    public static final InMemoryProductRepository productRepo = new InMemoryProductRepository();
    public static final InMemoryUserRepository userRepo = new InMemoryUserRepository();

    // ---- Services ----
    public static final ProductService productService = new ProductServiceImpl(productRepo);
    public static final CartService cartService = new CartServiceImpl();
    public static final UserService userService = new UserServiceImpl(userRepo);
    public static final OrderService orderService = new OrderServiceImpl();

    // ---- Current Logged-in User ----
    public static User currentUser;

    // ---- Stage Management ----
    private static Stage primaryStage;
    public static void setPrimaryStage(Stage s) { primaryStage = s; }
    public static Stage getPrimaryStage() { return primaryStage; }

    // ---- Seed Demo Products ----
    static {
        productRepo.save(new Product("P1", "MoonBean Arabica", "Coffee", 120.00, 999));
        productRepo.save(new Product("P2", "Espresso Blend", "Coffee", 150.00, 999));
        productRepo.save(new Product("P3", "Cold Brew Bottle", "Drink", 180.00, 999));
    }

    private AppContext() {}
}
