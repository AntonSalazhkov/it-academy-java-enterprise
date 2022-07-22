package by.it.academy.shop.exception.businessExceptions;

import javax.persistence.PersistenceException;

/**
 * Исключение непройденной авторизации пользователя.
 */

public class AuthorizationUserException extends PersistenceException {
    public AuthorizationUserException() {
    }
}
