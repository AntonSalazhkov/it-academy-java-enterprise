package by.it.academy.shop.dtos.mail.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * Обработка ответа по отправке сообщения.
 * Присутствует валидация по полю.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MailRequest {

    @NotNull
    private boolean messageDispatchStatus;
}
