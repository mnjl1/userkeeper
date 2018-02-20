package ua.com.mnjl.userkeeper.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ua.com.mnjl.userkeeper.model.User;
import ua.com.mnjl.userkeeper.service.UserService;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void findByIdTest() {

       User user = new User(1, "Dmytro", "Manzhula", "dmytromr", "12345");
        when(userService.findById(1L)).thenReturn(user);

        User resultUser = userController.getUser(1L);

        assertEquals(user, resultUser);
    }

    @Test
    public void findByIdUrlTest() throws Exception {

        given(this.userController.getUser(1L)).willReturn(new User(1, "Dmytro", "Manzhula",
                "dmytromr", "12345"));

        this.mockMvc.perform(get("/user/1").accept(MediaType.APPLICATION_JSON_UTF8))
                    .andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
    }
}
