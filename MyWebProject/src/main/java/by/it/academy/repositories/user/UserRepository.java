package by.it.academy.repositories.user;

import java.util.List;

public interface UserRepository<T> {

    void create(T newUser);

    List<T> getUsersFromDatabase();

    void addUser(T user);

    T getUser();
}
