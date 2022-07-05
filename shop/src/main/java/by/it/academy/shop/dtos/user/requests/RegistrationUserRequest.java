package by.it.academy.shop.dtos.user.requests;

import by.it.academy.shop.constants.Messages;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationUserRequest {

    @Pattern(regexp = "^[a-zA-Z0-9]{2,15}$", message = Messages.INCORRECT_REGISTRATION)
    private String login;

    @Pattern(regexp = "^.+@[a-zA-Z0-9]+(\\.[a-zA-Z]{2,})$", message = Messages.INCORRECT_REGISTRATION)
    private String email;

    @Pattern(regexp = "(?=.*[a-zA-Z])(?=.*[0-9]).{8,}", message = Messages.INCORRECT_REGISTRATION)
    private String password;
}
