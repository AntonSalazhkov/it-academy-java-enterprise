package by.it.academy.services.user;

import by.it.academy.entities.user.User;
import by.it.academy.entities.user.UserType;
import org.mindrot.jbcrypt.BCrypt;

import java.util.List;
import java.util.Optional;

public class UserAuthenticationService {

    private final String loginPattern = "^[a-zA-Z0-9]{2,15}$";
    private final String emailPattern = "^.+@[a-zA-Z0-9]+(\\.[a-zA-Z]{2,})$";
    private final String passwordPattern = "(?=.*[a-zA-Z])(?=.*[0-9]).{8,}";

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

    public User registrationUser(String login, String email, String password) {

        UserTypeApiService userTypeApiService = new UserTypeApiService();
        UserType userType = userTypeApiService.getUserType(login);

        if (checkInputUser(login, loginPattern) && checkInputUser(email, emailPattern) && checkInputUser(password, passwordPattern)) {
            return new User(login, email, BCrypt.hashpw(password, BCrypt.gensalt()), userType);
        } else {
            return null;
        }
    }

    public boolean checkInputUser(String input, String pattern) {
        return input.matches(pattern);
    }
}
