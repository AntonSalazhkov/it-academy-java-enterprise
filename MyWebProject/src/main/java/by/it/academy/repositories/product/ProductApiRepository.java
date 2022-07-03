package by.it.academy.repositories.product;

import by.it.academy.entities.product.Product;
import by.it.academy.repositories.databases.DatabaseProduct;

import java.util.List;

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
    public List<Product> getProductsFromDatabase() {
        final DatabaseProduct databaseProduct = new DatabaseProduct();
        return databaseProduct.getProducts();
    }

    @Override
    public List<Product> getProductsFromDatabase(String categoryType, String[] userChoices) {
        final DatabaseProduct databaseProduct = new DatabaseProduct(categoryType, userChoices);
        return databaseProduct.getProducts();
    }

    @Override
    public Product getProductsFromDatabaseById(int productId) {
        final DatabaseProduct databaseProduct = new DatabaseProduct(productId);
        return databaseProduct.getProduct();
    }

    @Override
    public List<Product> getProducts() {
        return products;
    }

    @Override
    public boolean updateStockBalance(Product product, String quantityProduct) {
        final DatabaseProduct databaseProduct = new DatabaseProduct();
        return databaseProduct.updateStockBalance(product, quantityProduct);
    }

    @Override
    public boolean addProduct(Product product) {
        final DatabaseProduct databaseProduct = new DatabaseProduct();
        return databaseProduct.addProductToDatabase(product);
    }

    @Override
    public void newBalanceInStock(int newBalance, int productId) {
        final DatabaseProduct databaseProduct = new DatabaseProduct();
        databaseProduct.newBalanceInStock(newBalance, productId);
    }


    @Override
    public void deleteProduct(int currentProductId) {
        final DatabaseProduct databaseProduct = new DatabaseProduct();
        databaseProduct.deleteProduct(currentProductId);
    }
}
