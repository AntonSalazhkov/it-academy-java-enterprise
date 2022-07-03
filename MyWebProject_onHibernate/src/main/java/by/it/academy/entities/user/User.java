package by.it.academy.entities.user;

import by.it.academy.util.annotation.SetUUID;
import by.it.academy.util.annotation.SetUUIDAnnotationAnalyzer;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Сущность User, записываемая в базу данных как таблица USERS.
 * Дополнительно имеется конструктор без поля "id".
 *
 * @SetUUID является собственной аннотацией с вызовом обработчика в конструкторе как SetUUIDAnnotationAnalyzer.parse(this);
 * Аннотация устанавливает полю "id" - UUID, если таковое имеется и оно равно null.
 */

@Entity
@Table(name = "USERS")
@Data
@NoArgsConstructor
@SetUUID           // Own annotation
public class User {

    @Id
    private String id;

    private String login;
    private String email;
    private String password;

    @Column(name = "USER_TYPE")
    @Enumerated
    private UserType userType;

    public User(String login, String email, String password, int userType) {
        SetUUIDAnnotationAnalyzer.parse(this);
        this.login = login;
        this.email = email;
        this.password = password;
        this.userType = UserType.values()[userType];
    }
}
