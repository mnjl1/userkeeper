package ua.com.mnjl.userkeeper.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;
import ua.com.mnjl.userkeeper.model.User;
import ua.com.mnjl.userkeeper.repository.UserRepository;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void findByIdTest(){

        User user = new User(1,"Vasia", "Petrov", "vasiapet", "12345");

        when(userRepository.getOne(1L)).thenReturn(user);

        User result = userService.findById(1L);

        assertEquals(user, result);

    }

    @Test
    public void findByFirstNameTest(){

        User user = new User(1,"Vasia", "Petrov", "vasiapet", "12345");
        when(userRepository.findByFirstName("Vasia")).thenReturn(user);

        User result = userService.findByFirstName("Vasia");

        assertEquals(user, result);

    }
}
