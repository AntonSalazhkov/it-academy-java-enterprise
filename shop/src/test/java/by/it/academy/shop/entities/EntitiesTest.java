package by.it.academy.shop.entities;

import by.it.academy.shop.entities.product.Product;
import by.it.academy.shop.entities.product.ProductCategory;
import by.it.academy.shop.entities.product.ProductColour;
import by.it.academy.shop.entities.product.ProductType;
import by.it.academy.shop.entities.purchase.Purchase;
import by.it.academy.shop.entities.purchase.PurchaseStatus;
import by.it.academy.shop.entities.user.User;
import by.it.academy.shop.entities.user.UserType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Entities Test")
public class EntitiesTest {

    @Test
    @DisplayName("Test creation product entities")
    void productCreationTest() {
        UUID uuid = UUID.randomUUID();

        Product p1 = new Product();
        Product p2 = new Product(uuid, "img/product9.jpg", "coat", ProductCategory.MEN, ProductType.COAT,
                ProductColour.GREEN, "Good denim coat 2022", "L, X, XL", 12, 1);

        assertNull(p1.getId());
        assertNull(p1.getImagePath());
        assertNull(p1.getName());
        assertNull(p1.getProductCategory());
        assertNull(p1.getProductType());
        assertNull(p1.getProductColour());
        assertNull(p1.getProductDetails());
        assertNull(p1.getSizeClothes());
        assertEquals(0, p1.getPrice());
        assertEquals(0, p1.getInStock());

        assertEquals(uuid, p2.getId());
        assertEquals("img/product9.jpg", p2.getImagePath());
        assertEquals("coat", p2.getName());
        assertEquals(ProductCategory.MEN, p2.getProductCategory());
        assertEquals(ProductType.COAT, p2.getProductType());
        assertEquals(ProductColour.GREEN, p2.getProductColour());
        assertEquals("Good denim coat 2022", p2.getProductDetails());
        assertEquals("L, X, XL", p2.getSizeClothes());
        assertEquals(12, p2.getPrice());
        assertEquals(1, p2.getInStock());
    }

    @Test
    @DisplayName("Test creation user entities")
    void userCreationTest() {
        UUID uuid = UUID.randomUUID();

        User u1 = new User();
        User u2 = new User(uuid, "max", "qwer@gmail.com", "1234asdf", UserType.USER);

        assertNull(u1.getId());
        assertNull(u1.getLogin());
        assertNull(u1.getEmail());
        assertNull(u1.getPassword());
        assertNull(u1.getUserType());

        assertEquals(uuid, u2.getId());
        assertEquals("max", u2.getLogin());
        assertEquals("qwer@gmail.com", u2.getEmail());
        assertEquals("1234asdf", u2.getPassword());
        assertEquals(UserType.USER, u2.getUserType());
    }

    @Test
    @DisplayName("Test creation purchase entities")
    void purchaseCreationTest() {
        UUID uuid = UUID.randomUUID();
        String localDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));

        Purchase pu1 = new Purchase();
        Purchase pu2 = new Purchase(uuid, new User(uuid, "sergei", "asd@gmail.com", "123234asdf", UserType.USER),
                new Product(uuid, "img/product9.jpg", "coat", ProductCategory.MEN, ProductType.COAT,
                        ProductColour.GREEN, "Good denim coat 20222", "X, XL", 15, 10),
                5, localDate, PurchaseStatus.BOUGHT);

        assertNull(pu1.getId());
        assertNull(pu1.getUser());
        assertNull(pu1.getProduct());
        assertEquals(0, pu1.getProductQuantity());
        assertNull(pu1.getLocalDate());
        assertNull(pu1.getPurchaseStatus());

        assertEquals(uuid, pu2.getId());
        assertEquals(new User(uuid, "sergei", "asd@gmail.com", "123234asdf", UserType.USER), pu2.getUser());
        assertEquals(new Product(uuid, "img/product9.jpg", "coat", ProductCategory.MEN, ProductType.COAT,
                ProductColour.GREEN, "Good denim coat 20222", "X, XL", 15, 10), pu2.getProduct());
        assertEquals(5, pu2.getProductQuantity());
        assertEquals(localDate, pu2.getLocalDate());
        assertEquals(PurchaseStatus.BOUGHT, pu2.getPurchaseStatus());
    }
}
