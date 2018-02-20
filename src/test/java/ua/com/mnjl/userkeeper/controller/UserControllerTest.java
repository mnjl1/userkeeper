package ua.com.mnjl.userkeeper.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import ua.com.mnjl.userkeeper.UserkeeperApplication;
import ua.com.mnjl.userkeeper.model.User;
import ua.com.mnjl.userkeeper.repository.UserRepository;
import ua.com.mnjl.userkeeper.service.UserService;

import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(classes = UserkeeperApplication.class)
@WebAppConfiguration
public class UserControllerTest {


    private MockMvc mockMvc;

    private User user;

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;


    @Before
    public void setup() throws  Exception{
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void findByIdTest() throws Exception {

       User user = new User(1, "Dmytro", "Manzhula", "dmytromr", "12345");
        when(userService.findById(1L)).thenReturn(user);

        User resultUser = userController.getUser(1L);

        assertEquals(user, resultUser);
    }

    @Test
    public void findByIdUrlTest() throws Exception {
        User user = new User(1, "Dmytro", "Manzhula", "dmytromr", "12345");

        when(userController.getUser(1L)).thenReturn(user);


        mockMvc.perform(get("/user/{id}", 1L)).andExpect(status().isOk()) // в этой строке java.lang.NullPointerException
                .andExpect(content().contentType(MediaType.APPLICATION_JSON.getType()))
                .andExpect((ResultMatcher) jsonPath("$[1].id", is(1)));
    }
}
