package by.it.academy.services.user;

import by.it.academy.entities.user.User;
import by.it.academy.repositories.user.UserRepository;

import java.util.List;

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
    public void addUser(User user) {
        repository.addUser(user);
    }

    @Override
    public List<User> getUsersFromDatabase() {
        return repository.getUsersFromDatabase();
    }

    @Override
    public User authorizationUser(String login, String password) {
        UserAuthenticationService authenticationService = new UserAuthenticationService();
        return authenticationService.authorizationUser(repository.getUsersFromDatabase(), login, password);
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
}
