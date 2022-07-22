package by.it.academy.mailapi.dtos.mail.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * Обработка запроса по отправке сообщения.
 * Присутствует валидация по полям.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MailRequest {

    @NotBlank
    private String email;

    @NotBlank
    private String subject;

    @NotBlank
    private String text;
}
