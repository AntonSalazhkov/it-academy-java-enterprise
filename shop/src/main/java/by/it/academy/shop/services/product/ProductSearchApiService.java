package by.it.academy.shop.services.product;

import by.it.academy.shop.dtos.product.requests.ShowDetailsRequest;
import by.it.academy.shop.dtos.product.requests.ShowProductRequest;
import by.it.academy.shop.entities.product.Product;
import by.it.academy.shop.repositories.product.ProductRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Сервис поиска продуктов.
 */

public class ProductSearchApiService {

    private Product product;
    private List<Product> products;

    public ProductSearchApiService(ProductRepository repository, ShowDetailsRequest showDetailsRequest) {
        this.product = productSearchById(repository, showDetailsRequest);
    }

    public ProductSearchApiService(ProductRepository repository, ShowProductRequest showProductRequests) {
        this.products = productsSearch(repository, showProductRequests);
    }

    /**
     * Получить сохраненный продукт
     */
    public Product getProduct() {
        return product;
    }

    /**
     * Получить список сохраненных продуктов.
     */
    public List<Product> getProducts() {
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

        if (Objects.nonNull(showProductRequests.getProductCategories())) {

            showProductRequests.getProductCategories().stream()
                    .forEach(selectedParameter -> sortedProduct.addAll(repository.getByProductCategory(selectedParameter)));

            currentProducts.retainAll(sortedProduct);
        }

        if (Objects.nonNull(showProductRequests.getProductTypes())) {
            showProductRequests.getProductTypes().stream()
                    .forEach(selectedParameter -> sortedProduct.addAll(repository.getByProductType(selectedParameter)));

            currentProducts.retainAll(sortedProduct);
        }

        if (Objects.nonNull(showProductRequests.getProductColours())) {
            showProductRequests.getProductColours().stream()
                    .forEach(selectedParameter -> sortedProduct.addAll(repository.getByProductColour(selectedParameter)));

            currentProducts.retainAll(sortedProduct);
        }
        return currentProducts;
    }

    /**
     * Получить продукт по Id
     */
    private Product productSearchById(ProductRepository repository, ShowDetailsRequest showDetailsRequest) {
        return repository.getById(showDetailsRequest.getId());
    }
}
