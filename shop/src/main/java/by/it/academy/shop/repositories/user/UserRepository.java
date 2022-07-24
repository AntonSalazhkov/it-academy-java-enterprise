package by.it.academy.shop.repositories.user;

import by.it.academy.shop.entities.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * Методы обработки сущности User.
 */

public interface UserRepository extends JpaRepository<User, UUID> {
}
