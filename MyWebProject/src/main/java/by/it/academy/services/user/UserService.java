package by.it.academy.services.user;

import java.util.List;

public interface UserService<T> {

    void create(T user);

    void addUser(T user);

    List<T> getUsersFromDatabase();

    T authorizationUser(String login, String password);

    T registrationUser(String login, String email, String password);

    T getUser();
}
