package moonbean.service.impl;

import moonbean.repository.mongo.MongoProductRepository;
import moonbean.model.Product;
import moonbean.service.ProductService;

import java.util.ArrayList;
import java.util.List;

public class ProductServiceImpl implements ProductService {
    private final MongoProductRepository repo;

    public ProductServiceImpl(MongoProductRepository repo) {
        this.repo = repo;
    }

    @Override
    public ArrayList<Product> findAll() {
        return new ArrayList<>(repo.findAll());
    }

    @Override
    public ArrayList<Product> sortByPriceAsc(List<Product> current) {
        ArrayList<Product> copy = new ArrayList<>(current);
        // A much cleaner, modern way to sort in Java
        copy.sort((p1, p2) -> Double.compare(p1.getPrice(), p2.getPrice()));
        return copy;
    }
}