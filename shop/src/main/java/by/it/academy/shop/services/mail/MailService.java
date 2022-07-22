package by.it.academy.shop.services.mail;

import by.it.academy.shop.dtos.mail.responses.MailResponse;

/**
 * Сервис обработки отправки сообщения.
 */

public interface MailService {
    /**
     * Составление сообщения для отправки пользователю.
     */
    void createMessage(String email, String subject, String text);

    /**
     * Отправка сообщения пользователю.
     */
    void dispatchMessage(MailResponse mailResponse);
}
