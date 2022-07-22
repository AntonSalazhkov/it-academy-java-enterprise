package by.it.academy.mailapi.services.mail;

import by.it.academy.mailapi.dtos.mail.requests.MailRequest;
import by.it.academy.mailapi.dtos.mail.responses.MailResponse;

/**
 * Сервис обработки отправки сообщения.
 */

public interface MailService {
    /**
     * Отправка сообщения по указанному email с указанным заголовком и текстом.
     */
    boolean dispatchMessage(MailRequest mailRequest);

    /**
     * Отправка сообщения о статусе обрабатываемого сообщения.
     */
    void dispatchStatus(MailResponse mailResponse);
}
