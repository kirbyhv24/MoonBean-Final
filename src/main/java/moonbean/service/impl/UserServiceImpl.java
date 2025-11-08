package moonbean.service.impl;

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
        User u = new User(first, last, email, pass);
        return repo.save(u);
    }

    @Override
    public User login(String email, String pass) {
        return repo.findByEmailAndPassword(email, pass);
    }
}
