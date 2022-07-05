package by.it.academy.shop.controllers.user;

import by.it.academy.shop.dtos.user.requests.AuthorizationUserRequest;
import by.it.academy.shop.entities.user.User;
import by.it.academy.shop.services.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * Контроллер обработки авторизации пользователя.
 * В случае наличия параметров "login" и "password" производит их проверку на соответствие имеющимся в базе данных.
 * При совпадении данных параметров вернет полученного пользователя, при не совпадении каких-либо параметров вернет null.
 */

@Slf4j
@RestController
@RequestMapping("/authorization")
@RequiredArgsConstructor
public class AuthorizationController {

    private final UserService userService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public User authorizationUser(@RequestBody @Validated AuthorizationUserRequest authorizationUserRequest) {
        return userService.authorizationUser(authorizationUserRequest);
    }
}
