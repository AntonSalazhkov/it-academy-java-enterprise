package by.it.academy.shop.controllers.user;

import by.it.academy.shop.constants.Messages;
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

import java.util.UUID;

/**
 * Контроллер обработки пользователя.
 * Метод authorizationUser прослушивает адресс /authorization, в случае наличия параметров "login" и "password" производится
 * их проверка на соответствие имеющимся в базе данных данным пользователей.
 * При совпадении данных параметров вернет полученного пользователя, при не совпадении каких-либо параметров вернет
 * сообщение о не корректности введенных данных авторипзации.
 * <p>
 * Метод registrationUser прослушивает адресс /registration, в случае наличия параметров "login", "email" и "password" производится
 * их проверка на соответствие корректности ввода (по паттерну), а также уникальность "login" к имеющимся в базе данных.
 * При корректном вводе добавит пользователя в базу данных и вернет его на страницу, при не коректных данных вернет сообщение
 * о некорректном вводе данных или сообщение о нарушении уникальности логина.
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
    public UUID authorizationUser(@RequestBody @Validated AuthorizationUserRequest authorizationUserRequest) {
        User user =  userService.authorizationUser(authorizationUserRequest);
        return user.getId();
    }

    @RequestMapping("/registration")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public UUID registrationUser(@RequestBody @Validated RegistrationUserRequest registrationUserRequest) {
        User user = userService.addUser(registrationUserRequest);

        mailApiService.createMessage(user.getEmail(), Messages.SUBJECT_EMAIL_MESSAGE, Messages.TEXT_EMAIL_MESSAGE);
        return user.getId();
    }
}
