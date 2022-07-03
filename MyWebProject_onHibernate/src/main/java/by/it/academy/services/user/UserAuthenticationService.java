package by.it.academy.services.user;

import by.it.academy.entities.user.User;
import org.mindrot.jbcrypt.BCrypt;

import java.util.List;
import java.util.Optional;

/**
 * Сервис авторизации пользователя.
 */

public class UserAuthenticationService {

    private final String loginPattern = "^[a-zA-Z0-9]{2,15}$";
    private final String emailPattern = "^.+@[a-zA-Z0-9]+(\\.[a-zA-Z]{2,})$";
    private final String passwordPattern = "(?=.*[a-zA-Z])(?=.*[0-9]).{8,}";

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
     * Регистрация нового пользователя
     * Проверка на соответствие патернам введенных логина, email и пароля пользователя
     */
    public User registrationUser(String login, String email, String password) {

        UserTypeApiService userTypeApiService = new UserTypeApiService();
        int userType = userTypeApiService.getUserType(login).ordinal();

        if (checkInputUser(login, loginPattern) && checkInputUser(email, emailPattern) && checkInputUser(password, passwordPattern)) {
            return new User(login, email, BCrypt.hashpw(password, BCrypt.gensalt()), userType);
        } else {
            return null;
        }
    }

    /**
     * Проверка введенных данных на соответствие патернам.
     */
    public boolean checkInputUser(String input, String pattern) {
        return input.matches(pattern);
    }
}
