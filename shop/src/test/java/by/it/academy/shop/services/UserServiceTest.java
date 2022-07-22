package by.it.academy.shop.services;

import by.it.academy.shop.dtos.user.requests.AuthorizationUserRequest;
import by.it.academy.shop.dtos.user.requests.RegistrationUserRequest;
import by.it.academy.shop.entities.user.User;
import by.it.academy.shop.services.user.UserApiService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@DisplayName("User service test")
@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserApiService userApiService;

    private User user;

    @BeforeEach
    void dataInitialization() {
        user = new User("admin", "1234@gmail.com",
                "$2a$12$imNox.3ui4oByQQpLeS.K.EPUdYmNmIxlHSpZrPqtgd0I54XN2F7K", 0);
    }

    @RepeatedTest(3)
    @DisplayName("Add user test")
    void addUserTest() {
        RegistrationUserRequest registrationUserRequest = new RegistrationUserRequest("admin",
                "1234@gmail.com", "1234asdf");

        when(userApiService.addUser(registrationUserRequest)).thenReturn(user);

        assertEquals(user, userApiService.addUser(registrationUserRequest));
    }

    @RepeatedTest(3)
    @DisplayName("Authorization user test")
    void authorizationUserTest() {
        AuthorizationUserRequest authorizationUserRequest = new AuthorizationUserRequest("admin",
                "1234asdf");
        doReturn(user).when(userApiService).authorizationUser(authorizationUserRequest);

        assertEquals(user, userApiService.authorizationUser(authorizationUserRequest));
    }
}