package by.it.academy.shop.dtos.mail.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * Сооставляемое сообщение для отправки пользователю.
 * Присутствует валидация по полям.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MailResponse {

    @NotBlank
    private String email;

    @NotBlank
    private String subject;

    @NotBlank
    private String text;
}
