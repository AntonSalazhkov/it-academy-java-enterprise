package by.it.academy.shop.repositories.product;

import by.it.academy.shop.entities.product.Product;
import by.it.academy.shop.entities.product.ProductCategory;
import by.it.academy.shop.entities.product.ProductColour;
import by.it.academy.shop.entities.product.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

/**
 * Методы соединения с базой данных.
 * Обработка сущности Product.
 */

public interface ProductRepository extends JpaRepository<Product, UUID> {
    /**
     * Получение списка продуктов из базы данных по категории.
     */
    List<Product> getByProductCategory(ProductCategory productCategory);

    /**
     * Получение списка продуктов из базы данных по типу.
     */
    List<Product> getByProductType(ProductType productType);

    /**
     * Получение списка продуктов из базы данных по цвету.
     */
    List<Product> getByProductColour(ProductColour productColour);

    /**
     * Поиск продукта в базе данных по введенному пользователем запросу.
     */
    List<Product> getByNameContainingIgnoreCase(String userInputProductName);
}
