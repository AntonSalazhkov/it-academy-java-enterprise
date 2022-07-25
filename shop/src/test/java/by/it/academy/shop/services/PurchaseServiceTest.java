package by.it.academy.shop.services;

import by.it.academy.shop.dtos.purchase.requests.CreatePurchaseRequest;
import by.it.academy.shop.entities.product.Product;
import by.it.academy.shop.entities.product.ProductCategory;
import by.it.academy.shop.entities.product.ProductColour;
import by.it.academy.shop.entities.product.ProductType;
import by.it.academy.shop.entities.purchase.Purchase;
import by.it.academy.shop.entities.purchase.PurchaseStatus;
import by.it.academy.shop.entities.user.User;
import by.it.academy.shop.entities.user.UserType;
import by.it.academy.shop.repositories.product.ProductRepository;
import by.it.academy.shop.repositories.purchase.PurchaseRepository;
import by.it.academy.shop.repositories.user.UserRepository;
import by.it.academy.shop.services.purchase.PurchaseApiService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@DisplayName("Purchase service test")
@ExtendWith(MockitoExtension.class)
public class PurchaseServiceTest {

    @Mock
    private PurchaseRepository purchaseRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private ProductRepository productRepository;

    private PurchaseApiService purchaseApiService;
    private UUID id1;
    private UUID id2;
    private UUID userId;
    private UUID productId;
    private Product product;
    private User user;
    private Purchase purchase;
    private List<Purchase> purchases;

    @BeforeEach
    void dataInitialization() {
        id1 = UUID.randomUUID();
        id2 = UUID.randomUUID();
        userId = UUID.randomUUID();
        productId = UUID.randomUUID();
        purchaseApiService = new PurchaseApiService(purchaseRepository, userRepository, productRepository);

        product = new Product(productId, "img/product9.jpg", "coat", ProductCategory.MEN, ProductType.COAT,
                ProductColour.GREEN, "Good denim coat 2022", "L, X, XL", 12, 1);
        user = new User(userId, "admin", "1234@gmail.com", "1234zxcv", UserType.ADMIN);

        purchase = Purchase.builder()
                .user(user)
                .product(product)
                .productQuantity(4)
                .localDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")))
                .purchaseStatus(PurchaseStatus.PROCESSING)
                .build();

        purchases = new ArrayList<>(Arrays.asList(purchase));
    }

    @Test
    @DisplayName("Add purchase test")
    void addPurchaseTest() {
        CreatePurchaseRequest createPurchaseRequest1 = new CreatePurchaseRequest(userId, productId, "4");
        CreatePurchaseRequest createPurchaseRequest2 = new CreatePurchaseRequest(null, productId, "4");
        CreatePurchaseRequest createPurchaseRequest3 = new CreatePurchaseRequest(userId, null, "4");

        when(purchaseRepository.save(purchase)).thenReturn(purchase);
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(productRepository.findById(productId)).thenReturn(Optional.of(product));

        assertEquals(purchase, purchaseApiService.addPurchase(createPurchaseRequest1));
        assertThrows(EntityNotFoundException.class, () -> purchaseApiService.addPurchase(createPurchaseRequest2));
        assertThrows(EntityNotFoundException.class, () -> purchaseApiService.addPurchase(createPurchaseRequest3));
    }

    @Test
    @DisplayName("Show user purchase test")
    void showUserPurchaseTest() {
        when(purchaseRepository.getByUserIdAndPurchaseStatus(id1, PurchaseStatus.PROCESSING)).thenReturn(purchases);

        assertEquals(purchases, purchaseApiService.showUserPurchase(id1));
        assertThrows(EntityNotFoundException.class, () -> purchaseApiService.showUserPurchase(id2));
    }

    @Test
    @DisplayName("Make purchase test")
    void makePurchaseTest() {
        when(purchaseRepository.findById(id1)).thenReturn(Optional.of(purchase));
        when(productRepository.findById(productId)).thenReturn(Optional.of(product));

        assertFalse(purchaseApiService.makePurchase(id1));
        assertThrows(EntityNotFoundException.class, () -> purchaseApiService.makePurchase(id2));

        purchase.getProduct().setQuantity(10);
        assertTrue(purchaseApiService.makePurchase(id1));
        assertEquals(purchase.getProduct().getQuantity(), 6);
        assertEquals(purchase.getPurchaseStatus(), PurchaseStatus.BOUGHT);

        assertFalse(purchaseApiService.makePurchase(id1));

        purchase.getProduct().setId(id2);
        assertThrows(EntityNotFoundException.class, () -> purchaseApiService.makePurchase(id1));
    }
}
