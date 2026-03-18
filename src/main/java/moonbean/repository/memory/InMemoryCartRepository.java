package moonbean.repository.memory;

import moonbean.model.CartItem;
import java.util.ArrayList;

public class InMemoryCartRepository {
    private final ArrayList<CartItem> cart = new ArrayList<>();

    public ArrayList<CartItem> findAll() { return new ArrayList<>(cart); }

    public void save(CartItem item) {
        for (CartItem c : cart) {
            if (c.getProduct().getProductId().equals(item.getProduct().getProductId())) {
                c.setQuantity(c.getQuantity() + item.getQuantity());
                return;
            }
        }
        cart.add(item);
    }

    public void clear() { cart.clear(); }

    public void removeByProductId(String id) {
        cart.removeIf(i -> i.getProduct().getProductId().equals(id));
    }
}
