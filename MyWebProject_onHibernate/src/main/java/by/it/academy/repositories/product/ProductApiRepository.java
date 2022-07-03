package by.it.academy.repositories.product;

import by.it.academy.entities.product.Product;
import by.it.academy.repositories.connection.ProductTransactions;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Реализация методов соединения с базой данных.
 * Обработка сущности Product.
 */

public class ProductApiRepository implements ProductRepository<Product> {
    private List<Product> products;

    public ProductApiRepository(List<Product> products) {
        this.products = products;
    }

    @Override
    public void create(List<Product> products) {
        this.products = products;
    }

    @Override
    public List<Product> getProductsFromDatabase(HttpServletRequest req) {
        final ProductTransactions ProductTransactions = new ProductTransactions(req);
        return ProductTransactions.getProductsByJPA();
    }

    @Override
    public List<Product> getProductsFromDatabase(HttpServletRequest req, String categoryType, List userChoices) {
        final ProductTransactions ProductTransactions = new ProductTransactions(req);
        return ProductTransactions.getProductsByJPA(categoryType, userChoices);
    }

    @Override
    public Product getProductsFromDatabaseById(HttpServletRequest req, String productId) {
        final ProductTransactions ProductTransactions = new ProductTransactions(req);
        return ProductTransactions.getProductsByJPAThroughId(productId);
    }

    @Override
    public List<Product> getProducts() {
        return products;
    }

    @Override
    public boolean updateStockBalance(HttpServletRequest req, Product product, String productQuantity) {
        final ProductTransactions ProductTransactions = new ProductTransactions(req);
        return ProductTransactions.updateStockBalanceByJPA(product, productQuantity);
    }

    @Override
    public boolean addProduct(HttpServletRequest req, Product product) {
        final ProductTransactions ProductTransactions = new ProductTransactions(req);
        return ProductTransactions.addProductByJPA(product);
    }

    @Override
    public void newBalanceInStock(HttpServletRequest req, int newBalance, String productId) {
        final ProductTransactions ProductTransactions = new ProductTransactions(req);
        ProductTransactions.newBalanceInStockByJPA(newBalance, productId);
    }

    @Override
    public void deleteProduct(HttpServletRequest req, String currentProductId) {
        final ProductTransactions ProductTransactions = new ProductTransactions(req);
        ProductTransactions.deleteProductByJPA(currentProductId);
    }

    @Override
    public List<Product> searchProducts(HttpServletRequest req, String userInput) {
        final ProductTransactions ProductTransactions = new ProductTransactions(req);
        return ProductTransactions.searchProductsByJPA(userInput);
    }
}
