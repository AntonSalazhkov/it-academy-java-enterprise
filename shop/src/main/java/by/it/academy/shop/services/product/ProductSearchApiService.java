package by.it.academy.shop.services.product;

import by.it.academy.shop.dtos.product.requests.ShowProductRequest;
import by.it.academy.shop.entities.product.Product;
import by.it.academy.shop.exception.businessExceptions.ProductsNotFoundException;
import by.it.academy.shop.repositories.product.ProductRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Сервис поиска продуктов.
 */

public class ProductSearchApiService {

    private final List<Product> products;

    public ProductSearchApiService(ProductRepository repository, ShowProductRequest showProductRequests) {
        this.products = productsSearch(repository, showProductRequests);
    }

    /**
     * Получить список сохраненных продуктов.
     */
    public List<Product> getProducts() {
        if (products.isEmpty()) {
            throw new ProductsNotFoundException();
        }
        return products;
    }

    /**
     * Получить список продуктов соответствующий всем выбранным параметрам пользователем.
     * Выбранные параметры пользователем поступают в виде списков enum.
     * При отсутствии выбранных параметров происходит возврат полного списка продуктов в базе данных.
     */
    private List<Product> productsSearch(ProductRepository repository, ShowProductRequest showProductRequests) {

        List<Product> currentProducts = repository.findAll();
        List<Product> sortedProduct = new ArrayList<>();

        if (!showProductRequests.getProductCategories().isEmpty()) {
            showProductRequests.getProductCategories()
                    .forEach(selectedParameter -> sortedProduct.addAll(repository.getByProductCategory(selectedParameter)));

            currentProducts.retainAll(sortedProduct);
            sortedProduct.clear();
        }

        if (!showProductRequests.getProductTypes().isEmpty()) {
            showProductRequests.getProductTypes()
                    .forEach(selectedParameter -> sortedProduct.addAll(repository.getByProductType(selectedParameter)));

            currentProducts.retainAll(sortedProduct);
            sortedProduct.clear();
        }

        if (!showProductRequests.getProductColours().isEmpty()) {
            showProductRequests.getProductColours()
                    .forEach(selectedParameter -> sortedProduct.addAll(repository.getByProductColour(selectedParameter)));

            currentProducts.retainAll(sortedProduct);
            sortedProduct.clear();
        }

        if (!showProductRequests.getUserInputProductName().isEmpty()) {
            sortedProduct.addAll(repository.getByNameContainingIgnoreCase(showProductRequests.getUserInputProductName()));

            currentProducts.retainAll(sortedProduct);
            sortedProduct.clear();
        }
        return currentProducts;
    }
}
