package moonbean.repository.mongo;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import moonbean.model.Order;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import java.util.ArrayList;
import java.util.List;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public class MongoOrderRepository {
    private final MongoCollection<Order> orders;

    public MongoOrderRepository() {
        // Setup POJO mapping so it can handle the Order and embedded CartItems automatically
        CodecRegistry pojoCodecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
                fromProviders(PojoCodecProvider.builder().automatic(true).build()));

        MongoClient client = MongoClients.create(MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString("mongodb://localhost:27017"))
                .codecRegistry(pojoCodecRegistry)
                .build());

        MongoDatabase db = client.getDatabase("moonbean_db");
        // Link to the 'orders' collection
        this.orders = db.getCollection("orders", Order.class);
    }

    // Save a completed order to the database
    public void save(Order order) {
        orders.insertOne(order);
    }

    // Retrieve all orders
    public List<Order> findAll() {
        return orders.find().into(new ArrayList<>());
    }
}