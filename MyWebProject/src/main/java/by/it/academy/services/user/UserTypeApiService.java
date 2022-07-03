package by.it.academy.services.user;

import by.it.academy.entities.user.UserType;

import java.util.Objects;

public class UserTypeApiService {

    public UserType getUserType(String login) {

        if (Objects.nonNull(login) && !Objects.equals("", login) && login.compareToIgnoreCase(UserType.ADMIN.name()) == 0) {
            return UserType.ADMIN;
        } else {
            return UserType.USER;
        }
    }
}
