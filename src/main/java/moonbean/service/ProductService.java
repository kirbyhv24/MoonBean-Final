package moonbean.service;

import moonbean.model.Product;
import java.util.ArrayList;
import java.util.List;

public interface ProductService {
    ArrayList<Product> findAll();
    ArrayList<Product> sortByPriceAsc(List<Product> current);
}
