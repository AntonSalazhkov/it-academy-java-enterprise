package by.it.academy.shop.exception.businessExceptions;

import javax.persistence.PersistenceException;

/**
 * Исключение поиска продукта по заданным пользовательским параметрам.
 */

public class ProductsNotFoundException extends PersistenceException {
    public ProductsNotFoundException() {
    }
}
