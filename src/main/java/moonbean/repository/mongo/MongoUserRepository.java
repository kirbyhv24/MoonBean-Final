package moonbean.repository.mongo;

import com.mongodb.client.*;
import org.bson.Document;
import moonbean.model.User;

import static com.mongodb.client.model.Filters.eq;

public class MongoUserRepository {

    private final MongoCollection<Document> users;

    public MongoUserRepository() {
        MongoClient client = MongoClients.create("mongodb://localhost:27017");
        MongoDatabase db = client.getDatabase("moonbean_db");
        users = db.getCollection("users");
    }

    public boolean emailExists(String email) {
        return users.find(eq("email", email)).first() != null;
    }

    public boolean save(User user) {
        Document doc = new Document("firstName", user.getFirstName())
                .append("lastName", user.getLastName())
                .append("email", user.getEmail())
                .append("password", user.getPassword());
        users.insertOne(doc);
        return false;
    }

    public User findByEmailAndPassword(String email, String password) {
        Document doc = users.find(eq("email", email))
                .filter(eq("password", password))
                .first();

        if (doc == null) return null;

        return new User(
                doc.getObjectId("_id").toString(),
                doc.getString("firstName"),
                doc.getString("lastName"),
                doc.getString("email"),
                doc.getString("password")
        );
    }
}
