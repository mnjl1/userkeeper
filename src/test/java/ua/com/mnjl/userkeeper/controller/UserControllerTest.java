package ua.com.mnjl.userkeeper.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ua.com.mnjl.userkeeper.model.User;
import ua.com.mnjl.userkeeper.repository.UserRepository;
import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {

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

        given(userRepository.getOne((long) 1)).willReturn(new User(1, "Dmytro",
                "Manzhula", "dmytromr", "12345"));

        when(userRepository.getOne((long) 1)).thenReturn(user);

        MockHttpServletResponse response = mvc.perform(get("/1")
                .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();

       assertThat(response.getContentAsString()).isEqualTo(jacksonTester.write(user).getJson());
    }
}
