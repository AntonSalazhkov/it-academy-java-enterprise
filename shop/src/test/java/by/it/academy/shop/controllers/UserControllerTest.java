package by.it.academy.shop.controllers;

import by.it.academy.shop.constants.Messages;
import by.it.academy.shop.controllers.user.UserController;
import by.it.academy.shop.dtos.mail.requests.CreateMailRequest;
import by.it.academy.shop.dtos.user.requests.AuthorizationUserRequest;
import by.it.academy.shop.dtos.user.requests.RegistrationUserRequest;
import by.it.academy.shop.entities.user.User;
import by.it.academy.shop.entities.user.UserType;
import by.it.academy.shop.services.mail.MailApiService;
import by.it.academy.shop.services.user.UserService;
import com.google.gson.Gson;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@DisplayName("User controller test")
@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserService userService;
    @MockBean
    private MailApiService mailApiService;
    @Autowired
    private Gson gson;

    @Test
    @DisplayName("Authorization user test")
    void authorizationUserTest() throws Exception {
        UUID uuid = UUID.randomUUID();
        User user = new User(uuid, "admin", "1234@gmail.com", "1234zxcv", UserType.ADMIN);
        AuthorizationUserRequest authorizationUserRequest = new AuthorizationUserRequest("admin", "1234zxcv");

        when(userService.authorizationUser(authorizationUserRequest)).thenReturn(user);

        mockMvc.perform(MockMvcRequestBuilders.post("/user/authorization")
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .content(gson.toJson(authorizationUserRequest)))
                .andExpect(MockMvcResultMatchers.status().isAccepted())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.content().json(gson.toJson(uuid)))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @DisplayName("Registration user test")
    void registrationUserTest() throws Exception {
        UUID uuid = UUID.randomUUID();
        User user = new User(uuid, "admin", "1234@gmail.com", "1234zxcv", UserType.ADMIN);
        RegistrationUserRequest registrationUserRequest = new RegistrationUserRequest("admin", "1234@gmail.com",
                "1234zxcv");
        CreateMailRequest createMailRequest = new CreateMailRequest("1234@gmail.com", Messages.SUBJECT_EMAIL_MESSAGE,
                Messages.TEXT_EMAIL_MESSAGE);

        when(userService.addUser(registrationUserRequest)).thenReturn(user);
        when(mailApiService.dispatchMessage(createMailRequest)).thenReturn(true);

        mockMvc.perform(MockMvcRequestBuilders.post("/user/registration")
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .content(gson.toJson(registrationUserRequest)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.content().json(gson.toJson(uuid)))
                .andDo(MockMvcResultHandlers.print());

        assertTrue(mailApiService.dispatchMessage(createMailRequest));
    }
}
