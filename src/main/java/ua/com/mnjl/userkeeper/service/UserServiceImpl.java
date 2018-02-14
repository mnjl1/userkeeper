package ua.com.mnjl.userkeeper.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.mnjl.userkeeper.model.User;
import ua.com.mnjl.userkeeper.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public User findById(long id) {
        return userRepository.getOne(id);
    }

    @Override
    public User findByFirstName(String firstName) {
        return userRepository.findByFirstName(firstName);
    }


    @Override
    public List<User> getAll() {
        return (List<User>) userRepository.findAll();
    }
}
