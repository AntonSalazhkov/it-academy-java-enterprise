package by.it.academy.repositories.user;

import by.it.academy.entities.user.User;
import by.it.academy.repositories.connection.UserTransactions;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;

/**
 * Реализация методов соединения с базой данных.
 * Обработка сущности User.
 */

public class UserApiRepository implements UserRepository<User> {
    private User user;

    public UserApiRepository() {
    }

    @Override
    public void create(User user) {
        this.user = user;
    }

    @Override
    public void addUser(HttpServletRequest req, User user) {
        final UserTransactions userTransactions = new UserTransactions(req);

        if (Objects.nonNull(user) && userTransactions.addUserByJPA(user)) {
            this.user = user;
        } else {
            this.user = null;
        }
    }

    @Override
    public List<User> getUsersFromDatabase(HttpServletRequest req) {
        final UserTransactions userTransactions = new UserTransactions(req);
        return userTransactions.getUsersByJPA();
    }

    @Override
    public User getUser() {
        return this.user;
    }

    @Override
    public boolean updateUser(HttpServletRequest req, User user) {
        final UserTransactions userTransactions = new UserTransactions(req);
        return userTransactions.updateUserByJPA(user);
    }
}
