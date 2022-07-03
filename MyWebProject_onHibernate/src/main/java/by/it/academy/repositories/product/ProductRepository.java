package by.it.academy.repositories.product;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Методы соединения с базой данных.
 * Обработка сущности Product.
 */

public interface ProductRepository<T> {

    /**
     * Создание нового продукта
     */
    void create(List<T> newProduct);

    /**
     * Получение сохраненного списка продуктов
     */
    List<T> getProducts();

    /**
     * Получение списка продуктов из базы данных.
     */
    List<T> getProductsFromDatabase(HttpServletRequest req);

    /**
     * Получение списка продуктов из базы данных с учетом выборов пользователя.
     */
    List<T> getProductsFromDatabase(HttpServletRequest req, String categoryType, List userChoices);

    /**
     * Получение продукта из базы данных по Id.
     */
    T getProductsFromDatabaseById(HttpServletRequest req, String productId);

    /**
     * Обновление количества продуктов на складе.
     */
    boolean updateStockBalance(HttpServletRequest req, T product, String productQuantity);

    /**
     * Добавление нового продукта в базу данных.
     */
    boolean addProduct(HttpServletRequest req, T product);

    /**
     * Установка нового остатка продукта на складе.
     */
    void newBalanceInStock(HttpServletRequest req, int newBalance, String productId);

    /**
     * Удаление продукта из базы данных.
     */
    void deleteProduct(HttpServletRequest req, String currentProductId);

    /**
     * Поиск продукта в базе данных по введенному пользователем запросу.
     */
    List<T> searchProducts(HttpServletRequest req, String userInput);
}
