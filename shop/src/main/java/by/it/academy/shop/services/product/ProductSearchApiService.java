package by.it.academy.shop.services.product;

import by.it.academy.shop.dtos.product.requests.ListProductRequest;
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

    public ProductSearchApiService(ProductRepository repository, ListProductRequest showProductRequests) {
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
    private List<Product> productsSearch(ProductRepository repository, ListProductRequest listProductRequest) {

        List<Product> currentProducts = repository.findAll();

        currentProducts.retainAll(productCategoriesFilter(repository, currentProducts, listProductRequest));
        currentProducts.retainAll(productTypesFilter(repository, currentProducts, listProductRequest));
        currentProducts.retainAll(productColoursFilter(repository, currentProducts, listProductRequest));
        currentProducts.retainAll(productUserInputFilter(repository, currentProducts, listProductRequest));

        return currentProducts;
    }

    private List<Product> productCategoriesFilter(ProductRepository repository, List<Product> currentProducts, ListProductRequest listProductRequest) {
        List<Product> filterProduct = new ArrayList<>();
        if (!listProductRequest.getProductCategories().isEmpty()) {
            listProductRequest.getProductCategories()
                    .forEach(selectedParameter -> filterProduct.addAll(repository.getByProductCategory(selectedParameter)));

            return filterProduct;
        } else {
            return currentProducts;
        }
    }

    private List<Product> productTypesFilter(ProductRepository repository, List<Product> currentProducts, ListProductRequest listProductRequest) {
        List<Product> filterProduct = new ArrayList<>();
        if (!listProductRequest.getProductTypes().isEmpty()) {
            listProductRequest.getProductTypes()
                    .forEach(selectedParameter -> filterProduct.addAll(repository.getByProductType(selectedParameter)));

            return filterProduct;
        } else {
            return currentProducts;
        }
    }

    private List<Product> productColoursFilter(ProductRepository repository, List<Product> currentProducts, ListProductRequest listProductRequest) {
        List<Product> filterProduct = new ArrayList<>();
        if (!listProductRequest.getProductColours().isEmpty()) {
            listProductRequest.getProductColours()
                    .forEach(selectedParameter -> filterProduct.addAll(repository.getByProductColour(selectedParameter)));

            return filterProduct;
        } else {
            return currentProducts;
        }
    }

    private List<Product> productUserInputFilter(ProductRepository repository, List<Product> currentProducts, ListProductRequest listProductRequest) {
        if (!listProductRequest.getUserInputProductName().isEmpty()) {
            return new ArrayList<>(repository.getByNameContainingIgnoreCase(listProductRequest.getUserInputProductName()));
        } else {
            return currentProducts;
        }
    }
}
