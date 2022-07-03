package by.it.academy.services.product;

import by.it.academy.entities.product.Product;

import by.it.academy.repositories.product.ProductRepository;
import org.apache.commons.lang3.math.NumberUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Реализация сервиса обработки продукта.
 */

public class ProductApiService implements ProductService<Product> {
    private final ProductRepository<Product> repository;

    public ProductApiService(ProductRepository<Product> repository) {
        this.repository = repository;
    }

    @Override
    public void create(List<Product> products) {
        repository.create(products);
    }

    @Override
    public List<Product> getProducts() {
        return repository.getProducts();
    }

    @Override
    public List<Product> showProduct(HttpServletRequest req) {
        return repository.getProductsFromDatabase(req);
    }

    @Override
    public List<Product> showProduct(HttpServletRequest req, String[] userChoiceCategories, String[] userChoiceType, String[] userChoiceColours) {
        final ProductSearchApiService productSearch = new ProductSearchApiService(req, repository,
                userChoiceCategories, userChoiceType, userChoiceColours);

        return productSearch.getProducts();
    }

    @Override
    public Product showProductById(HttpServletRequest req, String productId) {
        final ProductSearchApiService productSearch = new ProductSearchApiService(req, repository, productId);

        return productSearch.getProduct();
    }

    @Override
    public String getValueBasket(Product product, String productQuantity) {
        ValueBasketApiService valueBasket = new ValueBasketApiService();

        return valueBasket.calculateTotalCost(product, productQuantity);
    }

    @Override
    public boolean buyProduct(HttpServletRequest req, Product product, String productQuantity) {
        return repository.updateStockBalance(req, product, productQuantity);
    }

    @Override
    public boolean addProduct(HttpServletRequest req, Product product) {
        return repository.addProduct(req, product);
    }

    @Override
    public List<Product> searchProducts(HttpServletRequest req, String userInput) {
        return repository.searchProducts(req, userInput);
    }

    @Override
    public void updateProducts(HttpServletRequest req, String[] productIds, String[] productQuantity) {
        int i = 0;
        int minimumPossibleNumber = 0;

        for (String productId : productIds) {
            if (NumberUtils.isNumber(productQuantity[i]) && Integer.parseInt(productQuantity[i]) > minimumPossibleNumber) {

                repository.newBalanceInStock(req, Integer.parseInt(productQuantity[i]), productId);

            } else if (NumberUtils.isNumber(productQuantity[i]) && Integer.parseInt(productQuantity[i]) == minimumPossibleNumber) {
                repository.deleteProduct(req, productId);
            }

            i++;
        }
    }
}
