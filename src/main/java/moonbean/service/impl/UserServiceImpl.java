package moonbean.service.impl;

import moonbean.model.User;
import moonbean.repository.memory.InMemoryUserRepository;
import moonbean.service.UserService;

public class UserServiceImpl implements UserService {
    private final InMemoryUserRepository repo;
    public UserServiceImpl(InMemoryUserRepository repo) { this.repo = repo; }

    @Override
    public boolean register(User user) {
        if (user.getEmail().isEmpty() || user.getPassword().isEmpty()) return false;
        if (repo.findByEmail(user.getEmail()) != null) return false;
        repo.save(user);
        return true;
    }

    @Override
    public User login(String email, String password) {
        User u = repo.findByEmail(email);
        if (u != null && u.getPassword().equals(password)) return u;
        return null;
    }
}
