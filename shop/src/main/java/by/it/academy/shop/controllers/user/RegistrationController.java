package by.it.academy.shop.controllers.user;

import by.it.academy.shop.dtos.user.requests.RegistrationUserRequest;
import by.it.academy.shop.entities.user.User;
import by.it.academy.shop.services.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * Контроллер обработки регистрации нового пользователя.
 * В случае наличия параметров "login", "email" и "password" производит их проверку на соответствие корректности
 * ввода (по паттерну), а также уникальность "login" к имеющимся в базе данных.
 * При корректном вводе добавит пользователя в базу данных и вернет его на страницу, при не коректных данных вернет null.
 */

@Slf4j
@RestController
@RequestMapping("/registration")
@RequiredArgsConstructor
public class RegistrationController {

    private final UserService userService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public User registrationUser(@RequestBody @Validated RegistrationUserRequest registrationUserRequest) {
        return userService.addUser(registrationUserRequest);
    }
}
