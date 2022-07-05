package by.it.academy.shop.services.product;

import by.it.academy.shop.dtos.product.requests.AddProductRequest;
import by.it.academy.shop.dtos.product.requests.ShowDetailsRequest;
import by.it.academy.shop.dtos.product.requests.ShowProductRequest;
import by.it.academy.shop.entities.product.Product;

import java.util.List;

/**
 * Сервис обработки продукта.
 */

public interface ProductService {

    /**
     * Добавление продукта
     */
    Product addProduct(AddProductRequest addProductRequests);

    /**
     * Нахождение списка продуктов соответствующий выборам пользователя
     */
    List<Product> showProduct(ShowProductRequest showProductRequests);

    /**
     * Нахождение продукта по Id
     */
    Product showProductById(ShowDetailsRequest showDetailsRequest);
}
