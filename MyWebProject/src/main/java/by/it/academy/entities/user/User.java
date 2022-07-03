package by.it.academy.entities.user;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class User {

    int id;
    String login;
    String email;
    String password;
    UserType userType;

    public User(String login, String email, String password, UserType userType) {
        this.login = login;
        this.email = email;
        this.password = password;
        this.userType = userType;
    }

    public User(int id, String login, String email, String password, int userType) {
        this.id = id;
        this.login = login;
        this.email = email;
        this.password = password;
        this.userType = UserType.values()[userType - 1];
    }
}
