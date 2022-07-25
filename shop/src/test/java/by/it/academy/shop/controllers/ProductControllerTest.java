package by.it.academy.shop.controllers;

import by.it.academy.shop.controllers.product.ProductController;
import by.it.academy.shop.dtos.product.requests.CreateProductRequest;
import by.it.academy.shop.dtos.product.requests.ListProductRequest;
import by.it.academy.shop.dtos.product.requests.UpdateProductRequest;
import by.it.academy.shop.entities.product.Product;
import by.it.academy.shop.entities.product.ProductCategory;
import by.it.academy.shop.entities.product.ProductColour;
import by.it.academy.shop.entities.product.ProductType;
import by.it.academy.shop.services.product.ProductService;
import com.google.gson.Gson;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.when;

@DisplayName("Product controller test")
@WebMvcTest(ProductController.class)
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ProductService productService;
    @Autowired
    private Gson gson;

    @Test
    @DisplayName("Show products test")
    void showProductsTest() throws Exception {
        UUID uuid = UUID.randomUUID();
        List<ProductCategory> productCategories = new ArrayList<>(Arrays.asList(ProductCategory.MEN));
        List<ProductType> productTypes = new ArrayList<>(Arrays.asList(ProductType.COAT, ProductType.DRESSES));
        List<ProductColour> productColours = new ArrayList<>(Arrays.asList(ProductColour.RED, ProductColour.GREEN));

        Product product = new Product(uuid, "img/product9.jpg", "coat", ProductCategory.MEN, ProductType.COAT,
                ProductColour.GREEN, "Good denim coat 2022", "L, X, XL", 12, 1);
        List<Product> products = new ArrayList<>(Arrays.asList(product));

        ListProductRequest showProductRequests = new ListProductRequest();
        showProductRequests.setProductCategories(productCategories);
        showProductRequests.setProductTypes(productTypes);
        showProductRequests.setProductColours(productColours);

        when(productService.showProduct(showProductRequests)).thenReturn(products);

        mockMvc.perform(MockMvcRequestBuilders.get("/products/catalog")
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .content(gson.toJson(showProductRequests)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.content().json(gson.toJson(products)))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @DisplayName("Show product id test")
    void showProductByIdTest() throws Exception {
        UUID id = UUID.randomUUID();
        Product product = new Product(id, "img/product9.jpg", "coat", ProductCategory.MEN, ProductType.COAT,
                ProductColour.GREEN, "Good denim coat 2022", "L, X, XL", 12, 1);

        when(productService.showProductById(id)).thenReturn(product);

        mockMvc.perform(MockMvcRequestBuilders.get("/products/details/{id}", id)
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.content().json(gson.toJson(product)))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @DisplayName("Create product test")
    void createProductTest() throws Exception {

        Product product = Product.builder()
                .imagePath("img/product9.jpg")
                .name("coat")
                .productCategory(ProductCategory.MEN)
                .productType(ProductType.COAT)
                .productColour(ProductColour.GREEN)
                .productDetails("Good denim coat 2022")
                .sizeClothes("L, X, XL")
                .price(12)
                .quantity(1)
                .build();
        CreateProductRequest createProductRequest = new CreateProductRequest(product.getImagePath(), product.getName(),
                product.getProductCategory(), product.getProductType(), product.getProductColour(), product.getProductDetails(),
                product.getSizeClothes(), product.getPrice() + "", product.getQuantity() + "");

        when(productService.addProduct(createProductRequest)).thenReturn(product);

        mockMvc.perform(MockMvcRequestBuilders.post("/products/admin/new-product")
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .content(gson.toJson(createProductRequest)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.content().json(gson.toJson(product)))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @DisplayName("Update product test")
    void updateProductTest() throws Exception {
        UUID id = UUID.randomUUID();

        Product product = Product.builder()
                .id(id)
                .imagePath("img/product9.jpg")
                .name("coat")
                .productCategory(ProductCategory.MEN)
                .productType(ProductType.COAT)
                .productColour(ProductColour.GREEN)
                .productDetails("Good denim coat 2022")
                .sizeClothes("L, X, XL")
                .price(12)
                .quantity(1)
                .build();
        UpdateProductRequest updateProductRequest = new UpdateProductRequest(product.getId(), product.getImagePath(),
                product.getName(), product.getProductCategory(), product.getProductType(), product.getProductColour(),
                product.getProductDetails(), product.getSizeClothes(), product.getPrice() + "", product.getQuantity() + "");

        when(productService.updateProduct(updateProductRequest)).thenReturn(product);

        mockMvc.perform(MockMvcRequestBuilders.post("/products/admin")
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .content(gson.toJson(updateProductRequest)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.content().json(gson.toJson(product)))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @DisplayName("Update quantity product test")
    void updateQuantityProductTest() throws Exception {
        UUID id = UUID.randomUUID();

        when(productService.clearQuantityProduct(id)).thenReturn(true);

        mockMvc.perform(MockMvcRequestBuilders.post("/products/admin/no-product/{id}", id)
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.is(true)))
                .andDo(MockMvcResultHandlers.print());
    }
}
