package moonbean.service.impl;

import moonbean.app.AppContext;
import moonbean.model.User;
import moonbean.repository.mongo.MongoUserRepository;
import moonbean.service.UserService;

public class UserServiceImpl implements UserService {
    private final MongoUserRepository repo;

    public UserServiceImpl(MongoUserRepository repo) {
        this.repo = repo;
    }

    @Override
    public boolean register(String first, String last, String email, String pass) {
        if (repo.emailExists(email)) return false;

        // Combine names and set defaults for ERD fields
        String fullName = first + " " + last;
        User u = new User(fullName, email, pass, "Customer", "N/A", "N/A");

        repo.save(u);
        return true;
    }

    @Override
    public User login(String email, String pass) {
        User u = repo.findByEmailAndPassword(email, pass);
        if (u != null) {
            AppContext.currentUser = u; // 👈 CRITICAL: This saves your login session!
        }
        return u;
    }
}