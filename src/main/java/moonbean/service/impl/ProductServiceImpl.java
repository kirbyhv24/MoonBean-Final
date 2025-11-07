package moonbean.service.impl;

import moonbean.model.Product;
import moonbean.repository.memory.InMemoryProductRepository;
import moonbean.service.ProductService;

import java.util.ArrayList;
import java.util.List;


public class ProductServiceImpl implements ProductService {
    private final InMemoryProductRepository repo;
    public ProductServiceImpl(InMemoryProductRepository repo) { this.repo = repo; }

    @Override
    public ArrayList<Product> findAll() { return repo.findAll(); }

    @Override
    public ArrayList<Product> sortByPriceAsc(List<Product> current) {
        ArrayList<Product> copy = new ArrayList<>(current);
        for (int i = 0; i < copy.size() - 1; i++) {
            for (int j = 0; j < copy.size() - i - 1; j++) {
                if (copy.get(j).getPrice() > copy.get(j + 1).getPrice()) {
                    Product tmp = copy.get(j);
                    copy.set(j, copy.get(j + 1));
                    copy.set(j + 1, tmp);
                }
            }
        }
        return copy;
    }
}
