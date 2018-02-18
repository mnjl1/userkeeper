package ua.com.mnjl.userkeeper.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import org.springframework.web.context.WebApplicationContext;
import ua.com.mnjl.userkeeper.UserkeeperApplication;
import ua.com.mnjl.userkeeper.model.User;
import ua.com.mnjl.userkeeper.repository.UserRepository;

import java.awt.*;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.skyscreamer.jsonassert.JSONAssert.assertEquals;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mvc;


    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserController userController;

    private JacksonTester<User> jacksonTester;

    @Before
    public void setup(){
        JacksonTester.initFields(this, new ObjectMapper());

        mvc = MockMvcBuilders.standaloneSetup(userController).build();

    }


    @Test
    public void findByIdTest() throws Exception {

        User user = new User(1, "Dmytro",
                "Manzhula", "dmytromr", "12345");

        given(userRepository.findById((long) 1)).willReturn(java.util.Optional.of(new User(1, "Dmytro",
                "Manzhula", "dmytromr", "12345")));

        when(userRepository.getOne((long) 1)).thenReturn(user);

        MockHttpServletResponse response = mvc.perform(get("/1")
                .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();

       assertThat(response.getContentAsString()).isEqualTo(jacksonTester.write(user));

    }

}
