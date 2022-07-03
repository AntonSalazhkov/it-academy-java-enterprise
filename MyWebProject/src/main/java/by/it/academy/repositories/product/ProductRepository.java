package by.it.academy.repositories.product;

import java.util.List;

public interface ProductRepository<T> {

    void create(List<T> newProduct);

    List<T> getProducts();

    List<T> getProductsFromDatabase();

    List<T> getProductsFromDatabase(String categoryType, String[] userChoices);

    T getProductsFromDatabaseById(int productId);

    boolean updateStockBalance(T product, String quantityProduct);

    boolean addProduct(T product);

    void newBalanceInStock(int newBalance, int productId);

    void deleteProduct(int currentProductId);
}
