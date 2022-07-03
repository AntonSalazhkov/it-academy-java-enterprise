package by.it.academy.services.user;

import by.it.academy.entities.user.User;
import by.it.academy.repositories.user.UserRepository;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Реализация сервиса обработки пользователя.
 */

public class UserApiService implements UserService<User> {
    private final UserRepository<User> repository;

    public UserApiService(UserRepository<User> repository) {
        this.repository = repository;
    }

    @Override
    public void create(User user) {
        repository.create(user);
    }

    @Override
    public void addUser(HttpServletRequest req, User user) {
        repository.addUser(req, user);
    }

    @Override
    public List<User> getUsersFromDatabase(HttpServletRequest req) {
        return repository.getUsersFromDatabase(req);
    }

    @Override
    public User authorizationUser(HttpServletRequest req, String login, String password) {
        UserAuthenticationService authenticationService = new UserAuthenticationService();
        return authenticationService.authorizationUser(repository.getUsersFromDatabase(req), login, password);
    }

    @Override
    public User registrationUser(String login, String email, String password) {
        UserAuthenticationService authenticationService = new UserAuthenticationService();

        return authenticationService.registrationUser(login, email, password);
    }

    @Override
    public User getUser() {
        return repository.getUser();
    }

    @Override
    public boolean updateUser(HttpServletRequest req, User user) {
        return repository.updateUser(req, user);
    }
}
