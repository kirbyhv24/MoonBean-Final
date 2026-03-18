package moonbean.repository.mongo;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import moonbean.model.Product;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import java.util.ArrayList;
import java.util.List;

// NEW IMPORT needed for the update filter
import static com.mongodb.client.model.Filters.eq;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public class MongoProductRepository {
    private final MongoCollection<Product> products;

    public MongoProductRepository() {
        CodecRegistry pojoCodecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
                fromProviders(PojoCodecProvider.builder().automatic(true).build()));

        MongoClient client = MongoClients.create(MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString("mongodb://localhost:27017"))
                .codecRegistry(pojoCodecRegistry)
                .build());

        MongoDatabase db = client.getDatabase("moonbean_db");
        this.products = db.getCollection("products", Product.class);
    }

    public void save(Product p) {
        products.insertOne(p);
    }

    // --- NEW METHOD: Replaces the old product data with the new stock number ---
    public void update(Product p) {
        products.replaceOne(eq("_id", p.getProductId()), p);
    }

    public List<Product> findAll() {
        return products.find().into(new ArrayList<>());
    }

    public boolean isEmpty() {
        return products.countDocuments() == 0;
    }
}