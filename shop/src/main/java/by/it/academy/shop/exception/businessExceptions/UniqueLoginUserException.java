package by.it.academy.shop.exception.businessExceptions;

import javax.persistence.PersistenceException;

public class UniqueLoginUserException extends PersistenceException {
    public UniqueLoginUserException() {
    }
}
