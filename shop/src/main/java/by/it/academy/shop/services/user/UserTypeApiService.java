package by.it.academy.shop.services.user;

import by.it.academy.shop.entities.user.UserType;

import java.util.Objects;

/**
 * Сервис получение типа (уровня доступа) пользователя.
 */

public class UserTypeApiService {

    public UserType getUserType(String login) {

        if (Objects.nonNull(login) && !Objects.equals("", login) && login.compareToIgnoreCase(UserType.ADMIN.name()) == 0) {
            return UserType.ADMIN;
        } else {
            return UserType.USER;
        }
    }
}
