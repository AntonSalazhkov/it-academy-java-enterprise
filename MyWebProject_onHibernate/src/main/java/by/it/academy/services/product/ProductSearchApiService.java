package by.it.academy.services.product;

import by.it.academy.entities.product.Product;
import by.it.academy.entities.product.ProductCategories;
import by.it.academy.entities.product.ProductColours;
import by.it.academy.entities.product.ProductType;
import by.it.academy.repositories.product.ProductRepository;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Сервис поиска продуктов.
 */

public class ProductSearchApiService {

    private Product product;
    private List<Product> products = new ArrayList<>();

    public ProductSearchApiService(HttpServletRequest req, ProductRepository<Product> repository, String productId) {
        this.product = productSearchById(req, repository, productId);
    }

    public ProductSearchApiService(HttpServletRequest req, ProductRepository<Product> repository, String[] userChoiceCategories,
                                   String[] userChoiceType, String[] userChoiceColours) {

        this.products = productsSearch(req, repository, userChoiceCategories, userChoiceType, userChoiceColours);
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
     * Получить список продуктов соответствующий всем выборам пользователя.
     */
    private List<Product> productsSearch(HttpServletRequest req, ProductRepository<Product> repository, String[] userChoiceCategories,
                                         String[] userChoiceType, String[] userChoiceColours) {

        List<Product> currentProducts = repository.getProductsFromDatabase(req);
        List<Product> sortedProduct;

        if (Objects.nonNull(userChoiceCategories)) {
            //ProductCategories enum
            List<ProductCategories> collectEnum = Arrays.stream(userChoiceCategories)
                    .map(line -> ProductCategories.valueOf(line.toUpperCase()))
                    .collect(Collectors.toList());

            sortedProduct = repository.getProductsFromDatabase(req, "productCategories", collectEnum);
            currentProducts.retainAll(sortedProduct);
        }

        if (Objects.nonNull(userChoiceType)) {
            //ProductType enum
            List<ProductType> collectEnum = Arrays.stream(userChoiceType)
                    .map(line -> ProductType.valueOf(line.toUpperCase()))
                    .collect(Collectors.toList());

            sortedProduct = repository.getProductsFromDatabase(req, "productType", collectEnum);
            currentProducts.retainAll(sortedProduct);
        }

        if (Objects.nonNull(userChoiceColours)) {
            //ProductColours enum
            List<ProductColours> collectEnum = Arrays.stream(userChoiceColours)
                    .map(line -> ProductColours.valueOf(line.toUpperCase()))
                    .collect(Collectors.toList());

            sortedProduct = repository.getProductsFromDatabase(req, "productColours", collectEnum);
            currentProducts.retainAll(sortedProduct);
        }
        return currentProducts;
    }

    /**
     * Получить продукт по Id
     */
    private Product productSearchById(HttpServletRequest req, ProductRepository<Product> repository, String productId) {
        return repository.getProductsFromDatabaseById(req, productId);
    }
}
