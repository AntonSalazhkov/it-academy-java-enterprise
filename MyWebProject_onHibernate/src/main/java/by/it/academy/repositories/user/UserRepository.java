package by.it.academy.repositories.user;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Методы соединения с базой данных.
 * Обработка сущности User.
 */

public interface UserRepository<T> {

    /**
     * Создание нового пользователя.
     */
    void create(T newUser);

    /**
     * Получение списка пользователей из базы данных
     */
    List<T> getUsersFromDatabase(HttpServletRequest req);

    /**
     * Добавление нового пользователя в базу данных.
     */
    void addUser(HttpServletRequest req, T user);

    /**
     * Получение сохраненного пользователя.
     */
    T getUser();

    /**
     * Обновление пользователя в базе данных.
     */
    boolean updateUser(HttpServletRequest req, T user);
}
