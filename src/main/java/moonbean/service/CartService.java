package moonbean.service;

import moonbean.model.CartItem;
import java.util.ArrayList;

public interface CartService {
    ArrayList<CartItem> getItems();
    void add(String productId, int qty);
    void remove(String productId);
    void clear();
    double getTotal();
}
