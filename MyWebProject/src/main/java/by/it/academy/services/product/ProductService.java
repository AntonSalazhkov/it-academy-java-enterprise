package by.it.academy.services.product;

import java.util.List;

public interface ProductService<T> {

    void create(List<T> products);

    List<T> getProducts();

    List<T> showProduct();

    List<T> showProduct(String[] userChoiceCategories, String[] userChoiceType, String[] userChoiceColours);

    T showProductById(int productId);

    String getValueBasket(T product, String quantityProduct);

    boolean buyProduct(T product, String quantityProduct);

    boolean addProduct(T product);

    void updateProducts(String[] productIds, String[] quantityProduct);
}
