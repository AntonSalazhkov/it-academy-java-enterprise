package by.it.academy.shop.dtos.user.requests;

import by.it.academy.shop.constants.Messages;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthorizationUserRequest {

    @NotBlank(message = Messages.INCORRECT_AUTHORIZATION)
    private String login;

    @NotBlank(message = Messages.INCORRECT_AUTHORIZATION)
    private String password;
}
