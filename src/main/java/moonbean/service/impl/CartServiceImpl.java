package moonbean.service.impl;

import moonbean.app.AppContext;
import moonbean.model.CartItem;
import moonbean.model.Product;
import moonbean.repository.memory.InMemoryCartRepository;
import moonbean.service.CartService;

import java.util.ArrayList;

public class CartServiceImpl implements CartService {
    private final InMemoryCartRepository repo = new InMemoryCartRepository();

    @Override
    public ArrayList<CartItem> getItems() { return repo.findAll(); }

    @Override
    public void add(String productId, int qty) {
        if (qty <= 0) return;
        Product p = AppContext.productService.findAll()
                .stream().filter(x -> x.getId().equals(productId)).findFirst().orElse(null);
        if (p != null) repo.save(new CartItem(p, qty));
    }

    @Override public void remove(String productId) { repo.removeByProductId(productId); }
    @Override public void clear() { repo.clear(); }

    @Override
    public double getTotal() {
        double total = 0;
        for (CartItem c : repo.findAll()) total += c.getSubtotal();
        return total;
    }
}
