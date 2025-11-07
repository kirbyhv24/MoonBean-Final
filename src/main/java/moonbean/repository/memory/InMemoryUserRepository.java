package moonbean.repository.memory;

import moonbean.model.User;
import java.util.ArrayList;

public class InMemoryUserRepository {
    private final ArrayList<User> users = new ArrayList<>();

    public void save(User user) {
        users.add(user);
    }

    public User findByEmail(String email) {
        for (User u : users)
            if (u.getEmail().equalsIgnoreCase(email)) return u;
        return null;
    }

    public ArrayList<User> findAll() { return new ArrayList<>(users); }
}
