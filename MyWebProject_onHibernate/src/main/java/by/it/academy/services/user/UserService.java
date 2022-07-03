package by.it.academy.services.user;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Сервис обработки пользователя.
 */

public interface UserService<T> {

    /**
     * Создание пользователя
     */
    void create(T user);

    /**
     * Добавление пользователя
     */
    void addUser(HttpServletRequest req, T user);

    /**
     * Получение списка пользователей
     */
    List<T> getUsersFromDatabase(HttpServletRequest req);

    /**
     * Авторизация пользователя по логину и паролю
     */
    T authorizationUser(HttpServletRequest req, String login, String password);

    /**
     * Регистрация пользователя по введенным данным
     */
    T registrationUser(String login, String email, String password);

    /**
     * Получение пользователя
     */
    T getUser();

    /**
     * Обновление пользователя
     */
    boolean updateUser(HttpServletRequest req, T user);
}
