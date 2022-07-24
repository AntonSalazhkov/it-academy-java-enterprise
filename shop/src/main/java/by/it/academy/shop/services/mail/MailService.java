package by.it.academy.shop.services.mail;

import by.it.academy.shop.dtos.mail.requests.CreateMailRequest;

/**
 * Сервис обработки отправки сообщения.
 */

public interface MailService {
    /**
     * Составление сообщения для отправки пользователю.
     */
    boolean createMessage(String email, String subject, String text);

    /**
     * Отправка сообщения пользователю.
     */
    boolean dispatchMessage(CreateMailRequest createMailRequest);
}
