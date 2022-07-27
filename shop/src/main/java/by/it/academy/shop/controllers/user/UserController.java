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
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * Контроллер обработки запросов пользователя.
 */

@Slf4j
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final MailApiService mailApiService;

    /**
     * Метод authorizationUser прослушивает адресс /user/authorization, в случае наличия параметров "login" и "password"
     * производится их проверка на соответствие имеющимся в базе данных данным пользователей.
     * При совпадении данных параметров вернет полученного пользователя, при не совпадении каких-либо параметров вернет
     * сообщение о не корректности введенных данных авторипзации.
     */
    @RequestMapping("/authorization")
    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public UUID authorizationUser(@RequestBody @Validated AuthorizationUserRequest authorizationUserRequest) {
        User user = userService.authorizationUser(authorizationUserRequest);
        return user.getId();
    }

    /**
     * Метод registrationUser прослушивает адресс /user/registration, в случае наличия параметров "login", "email" и "password"
     * производится их проверка на соответствие корректности ввода (по паттерну), а также уникальность "login" к имеющимся
     * в базе данных. При корректном вводе добавит пользователя в базу данных и вернет его на страницу, при не коректных
     * данных вернет сообщение о некорректном вводе данных или сообщение о нарушении уникальности логина.
     */
    @RequestMapping("/registration")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UUID registrationUser(@RequestBody @Validated RegistrationUserRequest registrationUserRequest) {
        User user = userService.addUser(registrationUserRequest);

        boolean message = mailApiService.createMessage(user.getEmail(), Messages.SUBJECT_EMAIL_MESSAGE, Messages.TEXT_EMAIL_MESSAGE);
        log.info("Forming a request to send a message {}", message);

        return user.getId();
    }
}
