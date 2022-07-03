package by.it.academy.services.product;

import by.it.academy.entities.product.Product;
import by.it.academy.repositories.product.ProductRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ProductSearchApiService {

    private Product product;
    private List<Product> products = new ArrayList<>();

    public ProductSearchApiService(ProductRepository<Product> repository, int productId) {
        this.product = productSearchById(repository, productId);
    }

    public ProductSearchApiService(ProductRepository<Product> repository, String[] userChoiceCategories,
                                   String[] userChoiceType, String[] userChoiceColours) {

        this.products = productsSearch(repository, userChoiceCategories, userChoiceType, userChoiceColours);
    }

    public Product getProduct() {
        return product;
    }

    public List<Product> getProducts() {
        return products;
    }

    private List<Product> productsSearch(ProductRepository<Product> repository, String[] userChoiceCategories,
                                         String[] userChoiceType, String[] userChoiceColours) {

        List<Product> currentProducts = repository.getProductsFromDatabase();
        List<Product> sortedProduct;

        if (Objects.nonNull(userChoiceCategories)) {
            sortedProduct = repository.getProductsFromDatabase("categories", userChoiceCategories);
            currentProducts.retainAll(sortedProduct);
        }

        if (Objects.nonNull(userChoiceType)) {
            sortedProduct = repository.getProductsFromDatabase("type", userChoiceType);
            currentProducts.retainAll(sortedProduct);
        }

        if (Objects.nonNull(userChoiceColours)) {
            sortedProduct = repository.getProductsFromDatabase("colours", userChoiceColours);
            currentProducts.retainAll(sortedProduct);
        }
        return currentProducts;
    }

    private Product productSearchById(ProductRepository<Product> repository, int productId) {
        return repository.getProductsFromDatabaseById(productId);
    }
}
