package by.it.academy.shop.controllers;

import by.it.academy.shop.controllers.purchase.PurchaseController;
import by.it.academy.shop.dtos.purchase.requests.CreatePurchaseRequest;
import by.it.academy.shop.entities.product.Product;
import by.it.academy.shop.entities.product.ProductCategory;
import by.it.academy.shop.entities.product.ProductColour;
import by.it.academy.shop.entities.product.ProductType;
import by.it.academy.shop.entities.purchase.Purchase;
import by.it.academy.shop.entities.purchase.PurchaseStatus;
import by.it.academy.shop.entities.user.User;
import by.it.academy.shop.entities.user.UserType;
import by.it.academy.shop.services.purchase.PurchaseService;
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

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.when;

@DisplayName("Purchase controller test")
@WebMvcTest(PurchaseController.class)
public class PurchaseControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private PurchaseService purchaseService;
    @Autowired
    private Gson gson;

    @Test
    @DisplayName("Add purchase test")
    void addPurchaseTest() throws Exception {
        UUID userId = UUID.randomUUID();
        UUID productId = UUID.randomUUID();

        CreatePurchaseRequest createPurchaseRequest = new CreatePurchaseRequest(userId, productId, "5");
        Product product = new Product(productId, "img/product9.jpg", "coat", ProductCategory.MEN, ProductType.COAT,
                ProductColour.GREEN, "Good denim coat 2022", "L, X, XL", 12, 1);
        User user = new User(userId, "admin", "1234@gmail.com", "1234zxcv", UserType.ADMIN);

        Purchase purchase = Purchase.builder()
                .user(user)
                .product(product)
                .productQuantity(5)
                .localDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")))
                .purchaseStatus(PurchaseStatus.PROCESSING)
                .build();

        when(purchaseService.addPurchase(createPurchaseRequest)).thenReturn(purchase);

        mockMvc.perform(MockMvcRequestBuilders.get("/purchases/create")
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .content(gson.toJson(createPurchaseRequest)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.content().json(gson.toJson(purchase)))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @DisplayName("View user purchase test")
    void viewUserPurchaseTest() throws Exception {
        UUID id = UUID.randomUUID();
        UUID userId = UUID.randomUUID();
        UUID productId = UUID.randomUUID();

        CreatePurchaseRequest createPurchaseRequest = new CreatePurchaseRequest(userId, productId, "5");
        Product product = new Product(productId, "img/product9.jpg", "coat", ProductCategory.MEN, ProductType.COAT,
                ProductColour.GREEN, "Good denim coat 2022", "L, X, XL", 12, 1);
        User user = new User(userId, "admin", "1234@gmail.com", "1234zxcv", UserType.ADMIN);

        Purchase purchase = Purchase.builder()
                .user(user)
                .product(product)
                .productQuantity(5)
                .localDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")))
                .purchaseStatus(PurchaseStatus.PROCESSING)
                .build();

        List<Purchase> purchases = new ArrayList<>(Arrays.asList(purchase));

        when(purchaseService.showUserPurchase(id)).thenReturn(purchases);

        mockMvc.perform(MockMvcRequestBuilders.get("/purchases/basket/{id}", id)
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .content(gson.toJson(createPurchaseRequest)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.content().json(gson.toJson(purchases)))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @DisplayName("Make purchase test")
    void makePurchaseTest() throws Exception {
        UUID id = UUID.randomUUID();

        when(purchaseService.makePurchase(id)).thenReturn(true);

        mockMvc.perform(MockMvcRequestBuilders.get("/purchases/{id}", id)
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.is(true)))
                .andDo(MockMvcResultHandlers.print());
    }
}
