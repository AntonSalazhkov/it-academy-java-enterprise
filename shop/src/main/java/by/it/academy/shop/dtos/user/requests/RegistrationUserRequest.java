package by.it.academy.shop.dtos.user.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;

/**
 * Запрос по регистрации нового пользователя.
 * Присутствует валидация по полям, введенные поля должны соответствовать определенным паттернам.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationUserRequest {

    @Pattern(regexp = "^[a-zA-Z0-9]{2,15}$")
    private String login;

    @Pattern(regexp = "^.+@[a-zA-Z0-9]+(\\.[a-zA-Z]{2,})$")
    private String email;

    @Pattern(regexp = "(?=.*[a-zA-Z])(?=.*[0-9]).{8,}")
    private String password;
}
