package moonbean.repository.mongo;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.*;
import moonbean.model.User;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public class MongoUserRepository {
    private final MongoCollection<User> users;

    public MongoUserRepository() {
        // This magic line tells Mongo how to map Java Classes to Collections
        CodecRegistry pojoCodecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
                fromProviders(PojoCodecProvider.builder().automatic(true).build()));

        MongoClient client = MongoClients.create(MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString("mongodb://localhost:27017"))
                .codecRegistry(pojoCodecRegistry)
                .build());

        MongoDatabase db = client.getDatabase("moonbean_db");
        // Notice we are using <User> now, not <Document>
        this.users = db.getCollection("users", User.class);
    }

    public boolean emailExists(String email) {
        return users.find(eq("email", email)).first() != null;
    }

    public void save(User user) {
        users.insertOne(user); // No more manual Document building!
    }

    public User findByEmailAndPassword(String email, String password) {
        return users.find(and(eq("email", email), eq("password", password))).first();
    }
}