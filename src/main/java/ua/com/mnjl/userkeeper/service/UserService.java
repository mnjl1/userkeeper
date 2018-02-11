package ua.com.mnjl.userkeeper.service;

import ua.com.mnjl.userkeeper.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User save(User user);


    Optional<User> getUserById(long id);

    List<User> getAll();
}
