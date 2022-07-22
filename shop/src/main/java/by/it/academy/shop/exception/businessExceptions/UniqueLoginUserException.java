package by.it.academy.shop.exception.businessExceptions;

import javax.persistence.PersistenceException;

/**
 * Исключение ввода пользователем не уникального логина.
 */

public class UniqueLoginUserException extends PersistenceException {
    public UniqueLoginUserException() {
    }
}
