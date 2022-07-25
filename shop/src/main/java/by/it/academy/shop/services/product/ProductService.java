package by.it.academy.shop.services.product;

import by.it.academy.shop.dtos.product.requests.CreateProductRequest;
import by.it.academy.shop.dtos.product.requests.ListProductRequest;
import by.it.academy.shop.dtos.product.requests.UpdateProductRequest;
import by.it.academy.shop.entities.product.Product;

import java.util.List;
import java.util.UUID;

/**
 * Сервис обработки продукта.
 */

public interface ProductService {
    /**
     * Добавление нового продукта
     */
    Product addProduct(CreateProductRequest addProductRequests);

    /**
     * Нахождение списка продуктов соответствующий выборам пользователя
     */
    List<Product> showProduct(ListProductRequest showProductRequests);

    /**
     * Нахождение продукта по Id
     */
    Product showProductById(UUID id);

    /**
     * Обновление продукта
     */
    Product updateProduct(UpdateProductRequest updateProductRequest);

    /**
     * Удаление продукта по Id
     */
    boolean clearQuantityProduct(UUID id);
}
