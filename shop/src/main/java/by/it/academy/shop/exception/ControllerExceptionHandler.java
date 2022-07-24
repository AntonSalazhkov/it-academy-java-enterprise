package by.it.academy.shop.exception;

import by.it.academy.shop.constants.Messages;
import by.it.academy.shop.dtos.exception.ResponseError;
import by.it.academy.shop.exception.businessExceptions.AuthorizationUserException;
import by.it.academy.shop.exception.businessExceptions.ProductsNotFoundException;
import by.it.academy.shop.exception.businessExceptions.UniqueLoginUserException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.EntityNotFoundException;
import java.net.ConnectException;

/**
 * Контроллер обработки исключений.
 */

@Slf4j
@RestControllerAdvice
public class ControllerExceptionHandler {
    /**
     * Обработка исключений поиска продукта по заданным пользовательским параметрам.
     */
    @ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
    @ExceptionHandler(ProductsNotFoundException.class)
    public ResponseError handleProductsNotFoundException(ProductsNotFoundException e) {
        log.info("Received exception by method \"handleProductsNotFoundException\" " + e);
        return new ResponseError(Messages.PRODUCTS_NOT_FOUND, e.toString());
    }

    /**
     * Обработка исключений ненахождения сущности в базе данных.
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseError handleEntityNotFoundException(EntityNotFoundException e) {
        log.info("Received exception by method \"handleEntityNotFoundException\" " + e);
        return new ResponseError(Messages.INCORRECT_REQUEST_ENTITY_NOT_FOUND, e.toString());
    }

    /**
     * Обработка исключений некорректных форматов параметров запроса.
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidFormatException.class)
    public ResponseError handleIllegalArgumentException(InvalidFormatException e) {
        log.info("Received exception by method \"handleIllegalArgumentException\" " + e);
        return new ResponseError(Messages.INCORRECT_REQUEST_INVALID_FORMAT, e.toString());
    }

    /**
     * Обработка исключений непройденной авторизации пользователя.
     */
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(AuthorizationUserException.class)
    public ResponseError handleAuthorizationUserException(AuthorizationUserException e) {
        log.info("Received exception by method \"handleAuthorizationUserException\" " + e);
        return new ResponseError(Messages.INCORRECT_AUTHORIZATION, e.toString());
    }

    /**
     * Обработка исключений ввода пользователем не уникального логина.
     */
    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(UniqueLoginUserException.class)
    public ResponseError handleUniqueLoginUserException(UniqueLoginUserException e) {
        log.info("Received exception by method \"handleUniqueLoginUserException\" " + e);
        return new ResponseError(Messages.INCORRECT_UNIQUE_LOGIN, e.toString());
    }

    /**
     * Обработка исключений соединений с внешними API.
     */
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    @ExceptionHandler(ConnectException.class)
    public void connectException(ConnectException e) {
        log.info("Received exception by method \"connectException\" " + e);
    }
}
