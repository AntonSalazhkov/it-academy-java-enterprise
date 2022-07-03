package by.it.academy.services.product;

import by.it.academy.entities.product.Product;
import by.it.academy.repositories.product.ProductRepository;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.*;

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
    public List<Product> showProduct() {
        return repository.getProductsFromDatabase();
    }

    @Override
    public List<Product> showProduct(String[] userChoiceCategories, String[] userChoiceType, String[] userChoiceColours) {
        final ProductSearchApiService productSearch = new ProductSearchApiService(repository,
                userChoiceCategories, userChoiceType, userChoiceColours);

        return productSearch.getProducts();
    }

    @Override
    public Product showProductById(int productId) {
        final ProductSearchApiService productSearch = new ProductSearchApiService(repository, productId);

        return productSearch.getProduct();
    }

    @Override
    public String getValueBasket(Product product, String productQuantity) {
        ValueBasketApiService valueBasket = new ValueBasketApiService();

        return valueBasket.calculateTotalCost(product, productQuantity);
    }

    @Override
    public boolean buyProduct(Product product, String productQuantity) {
        return repository.updateStockBalance(product, productQuantity);
    }

    @Override
    public boolean addProduct(Product product) {
        return repository.addProduct(product);
    }

    @Override
    public void updateProducts(String[] productIds, String[] productQuantity) {
        int i = 0;
        int minimumPossibleNumber = 0;

        for (String productId : productIds) {
            if (NumberUtils.isNumber(productQuantity[i]) && Integer.parseInt(productQuantity[i]) > minimumPossibleNumber) {

                repository.newBalanceInStock(Integer.parseInt(productQuantity[i]), Integer.parseInt(productId));

            } else if (NumberUtils.isNumber(productQuantity[i]) && Integer.parseInt(productQuantity[i]) == minimumPossibleNumber) {
                repository.deleteProduct(Integer.parseInt(productId));
            }

            i++;
        }
    }
}
