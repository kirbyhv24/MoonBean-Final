package moonbean.app;

import javafx.stage.Stage;
import moonbean.model.User;
import moonbean.repository.memory.InMemoryCartRepository;
import moonbean.repository.memory.InMemoryProductRepository;
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

    public static User currentUser;

    // repositories
    public static final InMemoryProductRepository productRepo = new InMemoryProductRepository();
    public static final InMemoryCartRepository cartRepo = new InMemoryCartRepository();
    public static final MongoUserRepository userRepo = new MongoUserRepository(); // now MongoDB!

    // services
    public static final ProductService productService = new ProductServiceImpl(productRepo);
    public static final CartService cartService = new CartServiceImpl(cartRepo);
    public static final UserService userService = new UserServiceImpl(userRepo);
    public static final OrderService orderService = new OrderServiceImpl();

    // seed demo products
    static {
        productRepo.save(new moonbean.model.Product("P1", "MoonBean Arabica", "Coffee", 120.0, 999));
        productRepo.save(new moonbean.model.Product("P2", "Espresso Blend", "Coffee", 150.0, 999));
        productRepo.save(new moonbean.model.Product("P3", "Cold Brew Bottle", "Drink", 180.0, 999));
    }
}
