package by.it.academy.shop.services;

import by.it.academy.shop.dtos.product.requests.CreateProductRequest;
import by.it.academy.shop.dtos.product.requests.ListProductRequest;
import by.it.academy.shop.dtos.product.requests.UpdateProductRequest;
import by.it.academy.shop.entities.product.Product;
import by.it.academy.shop.entities.product.ProductCategory;
import by.it.academy.shop.entities.product.ProductColour;
import by.it.academy.shop.entities.product.ProductType;
import by.it.academy.shop.exception.businessExceptions.ProductsNotFoundException;
import by.it.academy.shop.repositories.product.ProductRepository;
import by.it.academy.shop.services.product.ProductApiService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityNotFoundException;
import java.util.*;

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
    private List<Product> products;

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

    @Test
    @DisplayName("Add product test")
    void addProductTest() {
        CreateProductRequest addProductRequest = new CreateProductRequest("img/product9.jpg", "coat",
                ProductCategory.MEN, ProductType.COAT, ProductColour.GREEN, "Good denim coat 2022",
                "L, X, XL", "12", "1");

        when(productRepository.save(product1)).thenReturn(product1);

        assertEquals(product1, productApiService.addProduct(addProductRequest));
    }

    @Test
    @DisplayName("Show product by id test")
    void showProductByIdTest() {
        doReturn(Optional.ofNullable(product1)).when(productRepository).findById(uuid);

        assertEquals(product1, productApiService.showProductById(uuid));
    }

    @Test
    @DisplayName("Show product test")
    void showProductTest() {
        List<ProductCategory> productCategories = new ArrayList<>(Arrays.asList(ProductCategory.MEN));
        List<ProductType> productTypes = new ArrayList<>();
        List<ProductColour> productColours = new ArrayList<>();
        String userInputProductName = "";

        ListProductRequest showProductRequest = new ListProductRequest(productCategories, productTypes, productColours,
                userInputProductName);
        ListProductRequest showProductRequest2 = new ListProductRequest(new ArrayList<>(), new ArrayList<>(),
                new ArrayList<>(), "123");

        doReturn(products).when(productRepository).findAll();
        doReturn(products).when(productRepository).getByProductCategory(ProductCategory.MEN);

        assertEquals(products, productApiService.showProduct(showProductRequest));

        assertThrows(ProductsNotFoundException.class, () -> productApiService.showProduct(showProductRequest2));
    }

    @Test
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

    @Test
    @DisplayName("Clear stock product test")
    void clearStockProductTest() {
        UUID uuid2 = UUID.randomUUID();

        doReturn(Optional.of(product2)).when(productRepository).findById(uuid);
        product2.setQuantity(0);
        doReturn(product2).when(productRepository).save(product2);

        assertTrue(productApiService.clearQuantityProduct(uuid));
        assertThrows(EntityNotFoundException.class, () -> productApiService.clearQuantityProduct(uuid2));
    }
}
