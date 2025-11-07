package moonbean.service;

import moonbean.model.User;

public interface UserService {
    boolean register(User user);
    User login(String email, String password);
}
