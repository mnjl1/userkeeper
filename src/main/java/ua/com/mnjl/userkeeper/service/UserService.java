package ua.com.mnjl.userkeeper.service;

import ua.com.mnjl.userkeeper.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User save(User user);


    User findById(long id);

    User findByFirstName(String firstName);

    List<User> getAll();
}
