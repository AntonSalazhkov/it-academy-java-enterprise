package by.it.academy.shop.controllers.user;

import by.it.academy.shop.dtos.user.requests.AuthorizationUserRequest;
import by.it.academy.shop.dtos.user.requests.RegistrationUserRequest;
import by.it.academy.shop.entities.user.User;
import by.it.academy.shop.services.mail.MailApiService;
import by.it.academy.shop.services.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * Контроллер обработки регистрации и авторизации пользователя.
 * В случае обращения по адрессу "/authorization" и наличия параметров "login" и "password" производится проверка
 * на соответствие имеющимся в базе данных пользователям.
 * При совпадении данных параметров вернет полученного пользователя, при не совпадении каких-либо параметров вернет null.
 * <p>
 * В случае обращения по адрессу "/registration" и наличия параметров "login", "email" и "password" производится их проверка
 * на соответствие корректности ввода (по паттерну), а также уникальность "login" к имеющимся в базе данных.
 * При корректном вводе добавит пользователя в базу данных и вернет его на страницу, при не коректных данных вернет null.
 */

@Slf4j
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final MailApiService mailApiService;

    @RequestMapping("/authorization")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public User authorizationUser(@RequestBody @Validated AuthorizationUserRequest authorizationUserRequest) {
        return userService.authorizationUser(authorizationUserRequest);
    }

    @RequestMapping("/registration")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public User registrationUser(@RequestBody @Validated RegistrationUserRequest registrationUserRequest) {
        User user = userService.addUser(registrationUserRequest);
        mailApiService.dispatchMessage(user);
        return user;
    }
}
