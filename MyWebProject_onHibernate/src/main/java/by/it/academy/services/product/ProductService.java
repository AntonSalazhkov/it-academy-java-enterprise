package by.it.academy.services.product;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Сервис обработки продукта.
 */

public interface ProductService<T> {

    /**
     * Создание списка продуктов
     */
    void create(List<T> products);

    /**
     * Получение списка продуктов
     */
    List<T> getProducts();

    /**
     * Нахождение списка продуктов
     */
    List<T> showProduct(HttpServletRequest req);

    /**
     * Нахождение списка продуктов соответствующий выборам пользователя
     */
    List<T> showProduct(HttpServletRequest req, String[] userChoiceCategories, String[] userChoiceType, String[] userChoiceColours);

    /**
     * Нахождение продукта по Id
     */
    T showProductById(HttpServletRequest req, String productId);

    /**
     * Получение стоимости корзины
     */
    String getValueBasket(T product, String quantityProduct);

    /**
     * Обработка покупки товара
     */
    boolean buyProduct(HttpServletRequest req, T product, String quantityProduct);

    /**
     * Добавление продукта
     */
    boolean addProduct(HttpServletRequest req, T product);

    /**
     * Обновление продукта
     */
    void updateProducts(HttpServletRequest req, String[] productIds, String[] quantityProduct);

    /**
     * Нахождение продукта по введенному запросу пользователя
     */
    List<T> searchProducts(HttpServletRequest req, String userInput);
}
