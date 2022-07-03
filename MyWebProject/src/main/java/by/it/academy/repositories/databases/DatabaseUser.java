package by.it.academy.repositories.databases;

import by.it.academy.constants.DatabaseRequests;
import by.it.academy.entities.user.User;
import lombok.extern.log4j.Log4j;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Log4j
public class DatabaseUser extends Database {

    private List<User> users;

    public DatabaseUser() {
        super();
        this.users = getUsersInDatabase();
    }

    public List<User> getUsers() {
        return users;
    }

    private List<User> getUsersInDatabase() {

        List<User> users = new ArrayList<>();

        String query = DatabaseRequests.SELECT_ALL_USERS;

        super.setDatabaseConnection(query);

        try {
            while (getResultSet().next()) {
                users.add(new User(getResultSet().getInt(1), getResultSet().getString(2),
                        getResultSet().getString(3), getResultSet().getString(4),
                        getResultSet().getInt(5)));
            }
        } catch (SQLException sqlEx) {
            log.info("Get products in database: " + sqlEx);
        } finally {
            super.closeStreams();
        }
        return users;
    }

    public boolean addUserToDatabase(User user) {

        if (!Objects.equals("", user.getLogin()) && !Objects.equals("", user.getEmail())
                && !Objects.equals("", user.getPassword()) && Objects.nonNull(user.getUserType())) {

            String query = DatabaseRequests.INSERT_INTO_USERS + "('" + user.getLogin() + "','" + user.getEmail() + "','"
                    + user.getPassword() + "'," + (user.getUserType().ordinal() + 1) + ")";

            super.setDatabaseConnection(query);
            super.closeStreams();
            return true;

        } else {
            return false;
        }
    }
}
