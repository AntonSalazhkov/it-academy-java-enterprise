package by.it.academy.repositories.user;

import by.it.academy.entities.user.User;
import by.it.academy.repositories.databases.DatabaseUser;

import java.util.List;
import java.util.Objects;

public class UserApiRepository implements UserRepository<User> {
    private User user;

    public UserApiRepository() {
    }

    @Override
    public void create(User user) {
        this.user = user;
    }

    @Override
    public void addUser(User user) {
        DatabaseUser databaseUser = new DatabaseUser();

        if (Objects.nonNull(user) && databaseUser.addUserToDatabase(user)) {
            this.user = user;
        } else {
            this.user = null;
        }
    }

    @Override
    public List<User> getUsersFromDatabase() {
        final DatabaseUser databaseUser = new DatabaseUser();
        return databaseUser.getUsers();
    }


    @Override
    public User getUser() {
        return this.user;
    }
}
