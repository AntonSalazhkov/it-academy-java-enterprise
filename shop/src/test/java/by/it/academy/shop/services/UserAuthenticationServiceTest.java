package by.it.academy.shop.services;

import by.it.academy.shop.entities.user.User;
import by.it.academy.shop.entities.user.UserType;
import by.it.academy.shop.exception.businessExceptions.AuthorizationUserException;
import by.it.academy.shop.exception.businessExceptions.UniqueLoginUserException;
import by.it.academy.shop.services.user.UserAuthenticationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mindrot.jbcrypt.BCrypt;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("User authentication service test")
@ExtendWith(MockitoExtension.class)
public class UserAuthenticationServiceTest {

    private User user1;
    private User user2;
    private List<User> users;

    @BeforeEach
    void dataInitialization() {
        user1 = new User("sergei", "1234@gmail.com",
                "$2a$12$imNox.3ui4oByQQpLeS.K.EPUdYmNmIxlHSpZrPqtgd0I54XN2F7K", 0);
        user2 = new User("anton", "5678@gmail.com",
                "$2a$12$Aiws0Kk2B8.9228YVybzcefG6l/UbJzapNVSHh/WCVdDwy6W7Vx/.", 1);

        users = new ArrayList<>();
        users.add(user1);
        users.add(user2);
    }

    @RepeatedTest(3)
    @DisplayName("Authorization user test")
    void authorizationUserTest() {
        UserAuthenticationService userAuthenticationService = new UserAuthenticationService();

        assertEquals(user1, userAuthenticationService.authorizationUser(users, "sergei", "1234asdf"));
        assertThrows(AuthorizationUserException.class, () -> userAuthenticationService
                .authorizationUser(users, "", "1234asdf"));
        assertThrows(AuthorizationUserException.class, () -> userAuthenticationService
                .authorizationUser(users, "admin", ""));
    }

    @RepeatedTest(3)
    @DisplayName("Registration user test")
    void registrationUserTest() {
        UserAuthenticationService userAuthenticationService = new UserAuthenticationService();
        User user3 = userAuthenticationService.registrationUser(users, "maxim", "5678@gmail.com", "1234zxcv");
        User user4 = userAuthenticationService.registrationUser(users, "admin", "5678@gmail.com", "1234zxcv");

        assertEquals("maxim", user3.getLogin());
        assertEquals("5678@gmail.com", user3.getEmail());
        assertTrue(BCrypt.checkpw("1234zxcv", user3.getPassword()));
        assertEquals(UserType.USER, user3.getUserType());

        assertEquals(UserType.ADMIN, user4.getUserType());

        assertThrows(UniqueLoginUserException.class, () -> userAuthenticationService
                .registrationUser(users, "Anton", "5678@gmail.com", "1234zxcv"));
    }
}
