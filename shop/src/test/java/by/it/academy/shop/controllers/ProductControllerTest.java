package by.it.academy.shop.controllers;

import by.it.academy.shop.controllers.product.ProductController;
import by.it.academy.shop.dtos.product.requests.ListProductRequest;
import by.it.academy.shop.dtos.product.requests.ProductRequest;
import by.it.academy.shop.entities.product.Product;
import by.it.academy.shop.entities.product.ProductCategory;
import by.it.academy.shop.entities.product.ProductColour;
import by.it.academy.shop.entities.product.ProductType;
import by.it.academy.shop.services.product.ProductService;
import com.google.gson.Gson;
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

import static org.mockito.Mockito.doReturn;
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
        List<Product> products2 = new ArrayList<>();

        ListProductRequest showProductRequests = new ListProductRequest();
        showProductRequests.setProductCategories(productCategories);
        showProductRequests.setProductTypes(productTypes);
        showProductRequests.setProductColours(productColours);


        when(productService.showProduct(showProductRequests)).thenReturn(products);

        mockMvc.perform(MockMvcRequestBuilders.post("/products/catalog")
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
        UUID uuid = UUID.randomUUID();

        Product product = new Product(uuid, "img/product9.jpg", "coat", ProductCategory.MEN, ProductType.COAT,
                ProductColour.GREEN, "Good denim coat 2022", "L, X, XL", 12, 1);
        ProductRequest idProductRequest = new ProductRequest(uuid);

        doReturn(product).when(productService).showProductById(idProductRequest);

        mockMvc.perform(MockMvcRequestBuilders.post("/products/catalog")
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .content(gson.toJson(idProductRequest)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(MockMvcResultHandlers.print());
    }
}
