package ua.com.mnjl.userkeeper.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.com.mnjl.userkeeper.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
}
