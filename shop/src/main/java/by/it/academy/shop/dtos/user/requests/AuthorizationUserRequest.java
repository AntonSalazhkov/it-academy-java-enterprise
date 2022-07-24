package by.it.academy.shop.dtos.user.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * Запрос по авторизации пользователя.
 * Присутствует валидация по полям.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthorizationUserRequest {

    @NotBlank
    private String login;

    @NotBlank
    private String password;
}
