package by.it.academy.shop.services.user;

import by.it.academy.shop.entities.user.User;
import org.mindrot.jbcrypt.BCrypt;

import java.util.List;
import java.util.Optional;

/**
 * Сервис авторизации пользователя.
 */

public class UserAuthenticationService {

    /**
     * Авторизация пользователя
     * Проверка введеных логина и пароля пользователя
     */
    public User authorizationUser(List<User> users, String login, String password) {

        Optional<User> userOptional = users
                .stream()
                .filter(user -> user.getLogin().equals(login))
                .filter(user -> BCrypt.checkpw(password, user.getPassword()))
                .findFirst();

        if (userOptional.isPresent()) {
            return userOptional.get();
        } else {
            return null;
        }
    }

    /**
     * Регистрация нового пользователя.
     * Проверка на уникальность логина пользователя.
     */
    public User registrationUser(List<User> users, String login, String email, String password) {

        Optional<User> userOptional = users
                .stream()
                .filter(user -> user.getLogin().equals(login.toLowerCase()))
                .findFirst();

        if (!userOptional.isPresent()) {
            UserTypeApiService userTypeApiService = new UserTypeApiService();
            int userType = userTypeApiService.getUserType(login).ordinal();

            return new User(login.toLowerCase(), email, BCrypt.hashpw(password, BCrypt.gensalt()), userType);
        } else {
            return null;
        }
    }
}
