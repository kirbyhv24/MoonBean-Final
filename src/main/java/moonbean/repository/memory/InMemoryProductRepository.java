package moonbean.repository.memory;

import moonbean.model.Product;
import java.util.ArrayList;

public class InMemoryProductRepository {
    private final ArrayList<Product> data = new ArrayList<>();

    public void save(Product p) {
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).getId().equals(p.getId())) {
                data.set(i, p);
                return;
            }
        }
        data.add(p);
    }

    public ArrayList<Product> findAll() {
        return new ArrayList<>(data);
    }
}
