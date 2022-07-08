package by.it.academy.shop.services.mail;

import by.it.academy.shop.entities.user.User;

public interface MailService {
    void dispatchMessage(User user);
}
