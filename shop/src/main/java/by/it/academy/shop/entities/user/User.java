package by.it.academy.shop.entities.user;

import lombok.*;

import javax.persistence.*;
import java.util.UUID;

/**
 * Сущность User, записываемая в базу данных как таблица USERS.
 * Имеет enum поле userType, которое устанавливает уровень доступа для пользователя.
 */

@Entity
@Table(name = "USERS_5")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String login;
    private String email;
    private String password;

    @Column(name = "USER_TYPE")
    @Enumerated
    private UserType userType;

    public User(String login, String email, String password, int userType) {
        this.login = login;
        this.email = email;
        this.password = password;
        this.userType = UserType.values()[userType];
    }
}
