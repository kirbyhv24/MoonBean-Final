package moonbean.service;

import moonbean.model.User;

public interface UserService {
    boolean register(String first, String last, String email, String pass);
    User login(String email, String pass);
}
