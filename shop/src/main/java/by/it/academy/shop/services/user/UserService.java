package by.it.academy.shop.services.user;

import by.it.academy.shop.dtos.user.requests.AuthorizationUserRequest;
import by.it.academy.shop.dtos.user.requests.RegistrationUserRequest;
import by.it.academy.shop.entities.user.User;

/**
 * Сервис обработки пользователя.
 */

public interface UserService {

    /**
     * Добавление нового пользователя
     */
    User addUser(RegistrationUserRequest registrationUserRequest);

    /**
     * Авторизация пользователя по логину и паролю
     */
    User authorizationUser(AuthorizationUserRequest authorizationUserRequest);
}
