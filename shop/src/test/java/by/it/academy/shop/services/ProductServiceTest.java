package by.it.academy.shop.services;

import by.it.academy.shop.dtos.product.requests.AddProductRequest;
import by.it.academy.shop.dtos.product.requests.IdProductRequest;
import by.it.academy.shop.dtos.product.requests.ShowProductRequest;
import by.it.academy.shop.dtos.product.requests.UpdateProductRequest;
import by.it.academy.shop.entities.product.Product;
import by.it.academy.shop.entities.product.ProductCategory;
import by.it.academy.shop.entities.product.ProductColour;
import by.it.academy.shop.entities.product.ProductType;
import by.it.academy.shop.repositories.product.ProductRepository;
import by.it.academy.shop.services.product.ProductApiService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@DisplayName("User service test")
@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    private ProductApiService productApiService;
    private Product product1;
    private Product product2;
    private Product product3;
    private UUID uuid;
    List<Product> products;

    @BeforeEach
    void dataInitialization() {
        uuid = UUID.randomUUID();

        productApiService = new ProductApiService(productRepository);
        product1 = new Product(null, "img/product9.jpg", "coat", ProductCategory.MEN, ProductType.COAT,
                ProductColour.GREEN, "Good denim coat 2022", "L, X, XL", 12, 1);
        product2 = new Product(uuid, "img/product9.jpg", "coat", ProductCategory.MEN, ProductType.COAT,
                ProductColour.GREEN, "Good denim coat 2022", "L, X, XL", 12, 1);
        product3 = new Product(uuid, "img/product10.jpg", "t-short", ProductCategory.LADIES, ProductType.T_SHIRT,
                ProductColour.GREEN, "Good denim coat 2020", "L, X", 13, 2);

        products = new ArrayList<>();
        products.add(product1);
        products.add(product2);
        products.add(product3);
    }

    @RepeatedTest(3)
    @DisplayName("Add product test")
    void addProductTest() {

        AddProductRequest addProductRequest = new AddProductRequest("img/product9.jpg", "coat", ProductCategory.MEN,
                ProductType.COAT, ProductColour.GREEN, "Good denim coat 2022", "L, X, XL", "12", "1");

        when(productRepository.save(product1)).thenReturn(product1);

        assertEquals(product1, productApiService.addProduct(addProductRequest));
    }

    @RepeatedTest(3)
    @DisplayName("Show product by id test")
    void showProductByIdTest() {
        IdProductRequest idProductRequest = new IdProductRequest(uuid);

        doReturn(Optional.ofNullable(product1)).when(productRepository).findById(idProductRequest.getId());

        assertEquals(product1, productApiService.showProductById(idProductRequest));
    }

    @RepeatedTest(3)
    @DisplayName("Show product test")
    void showProductTest() {
        List<ProductCategory> productCategories = new ArrayList<>();
        List<ProductType> productTypes = new ArrayList<>();
        List<ProductColour> productColours = new ArrayList<>();
        String userInputProductName = "";
        ProductCategory productCategory = ProductCategory.MEN;

        productCategories.add(ProductCategory.MEN);

        ShowProductRequest showProductRequest = new ShowProductRequest(productCategories, productTypes, productColours,
                userInputProductName);

        doReturn(products).when(productRepository).findAll();
        doReturn(products).when(productRepository).getByProductCategory(productCategory);

        assertEquals(products, productApiService.showProduct(showProductRequest));
    }

    @RepeatedTest(3)
    @DisplayName("Update product test")
    void updateProductTest() {
        UpdateProductRequest updateProductRequest = new UpdateProductRequest(uuid, "img/product10.jpg",
                "t-short", ProductCategory.LADIES, ProductType.T_SHIRT, ProductColour.GREEN,
                "Good denim coat 2020", "L, X", "13", "2");
        UpdateProductRequest updateProductRequest2 = new UpdateProductRequest(null, "img/product10.jpg",
                "", ProductCategory.LADIES, ProductType.T_SHIRT, ProductColour.GREEN,
                "Good denim coat 2020", "L, X", "13", "2");

        doReturn(Optional.of(product2)).when(productRepository).findById(updateProductRequest.getId());
        doReturn(product3).when(productRepository).save(product3);


        assertEquals(product3, productApiService.updateProduct(updateProductRequest));
        assertThrows(EntityNotFoundException.class, () -> productApiService.updateProduct(updateProductRequest2));
    }

    @RepeatedTest(3)
    @DisplayName("Clear stock product test")
    void clearStockProductTest() {
        IdProductRequest idProductRequest = new IdProductRequest(uuid);
        IdProductRequest idProductRequest2 = new IdProductRequest();

        doReturn(Optional.of(product2)).when(productRepository).findById(idProductRequest.getId());
        product2.setInStock(0);
        doReturn(product2).when(productRepository).save(product2);

        assertTrue(productApiService.clearStockProduct(idProductRequest));
        assertThrows(EntityNotFoundException.class, () -> productApiService.clearStockProduct(idProductRequest2));
    }
}
