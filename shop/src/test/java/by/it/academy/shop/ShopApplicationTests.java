package by.it.academy.shop;

import by.it.academy.shop.entities.product.Product;
import by.it.academy.shop.entities.product.ProductCategory;
import by.it.academy.shop.entities.product.ProductColour;
import by.it.academy.shop.entities.product.ProductType;
import by.it.academy.shop.entities.purchase.Purchase;
import by.it.academy.shop.entities.purchase.PurchaseStatus;
import by.it.academy.shop.entities.user.User;
import by.it.academy.shop.entities.user.UserType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@SpringBootTest
class ShopApplicationTests {

    @Test
    public void testProductCreation() {
        UUID uuid = UUID.randomUUID();

        Product p1 = new Product();
        Product p2 = new Product(uuid, "img/product9.jpg", "coat", ProductCategory.MEN, ProductType.COAT,
                ProductColour.GREEN, "Good denim coat 2022", "L, X, XL", 12, 1);

        Assertions.assertNull(p1.getId());
        Assertions.assertNull(p1.getImagePath());
        Assertions.assertNull(p1.getName());
        Assertions.assertNull(p1.getProductCategory());
        Assertions.assertNull(p1.getProductType());
        Assertions.assertNull(p1.getProductColour());
        Assertions.assertNull(p1.getProductDetails());
        Assertions.assertNull(p1.getSizeClothes());
        Assertions.assertEquals(0, p1.getPrice());
        Assertions.assertEquals(0, p1.getInStock());

        Assertions.assertEquals(uuid, p2.getId());
        Assertions.assertEquals("img/product9.jpg", p2.getImagePath());
        Assertions.assertEquals("coat", p2.getName());
        Assertions.assertEquals(ProductCategory.MEN, p2.getProductCategory());
        Assertions.assertEquals(ProductType.COAT, p2.getProductType());
        Assertions.assertEquals(ProductColour.GREEN, p2.getProductColour());
        Assertions.assertEquals("Good denim coat 2022", p2.getProductDetails());
        Assertions.assertEquals("L, X, XL", p2.getSizeClothes());
        Assertions.assertEquals(12, p2.getPrice());
        Assertions.assertEquals(1, p2.getInStock());
    }

    @Test
    public void testUserCreation() {
        UUID uuid = UUID.randomUUID();

        User u1 = new User();
        User u2 = new User(uuid, "max", "qwer@gmail.com", "1234asdf", UserType.USER);

        Assertions.assertNull(u1.getId());
        Assertions.assertNull(u1.getLogin());
        Assertions.assertNull(u1.getEmail());
        Assertions.assertNull(u1.getPassword());
        Assertions.assertNull(u1.getUserType());

        Assertions.assertEquals(uuid, u2.getId());
        Assertions.assertEquals("max", u2.getLogin());
        Assertions.assertEquals("qwer@gmail.com", u2.getEmail());
        Assertions.assertEquals("1234asdf", u2.getPassword());
        Assertions.assertEquals(UserType.USER, u2.getUserType());
    }

    @Test
    public void testPurchaseCreation() {
        UUID uuid = UUID.randomUUID();
        String localDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));

        Purchase pu1 = new Purchase();
        Purchase pu2 = new Purchase(uuid, new User(uuid, "sergei", "asd@gmail.com", "123234asdf", UserType.USER),
                new Product(uuid, "img/product9.jpg", "coat", ProductCategory.MEN, ProductType.COAT,
                        ProductColour.GREEN, "Good denim coat 20222", "X, XL", 15, 10),
                5, localDate, PurchaseStatus.BOUGHT);

        Assertions.assertNull(pu1.getId());
        Assertions.assertNull(pu1.getUser());
        Assertions.assertNull(pu1.getProduct());
        Assertions.assertEquals(0, pu1.getProductQuantity());
        Assertions.assertNull(pu1.getLocalDate());
        Assertions.assertNull(pu1.getPurchaseStatus());

        Assertions.assertEquals(uuid, pu2.getId());
        Assertions.assertEquals(new User(uuid, "sergei", "asd@gmail.com", "123234asdf", UserType.USER), pu2.getUser());
        Assertions.assertEquals(new Product(uuid, "img/product9.jpg", "coat", ProductCategory.MEN, ProductType.COAT,
                ProductColour.GREEN, "Good denim coat 20222", "X, XL", 15, 10), pu2.getProduct());
        Assertions.assertEquals(5, pu2.getProductQuantity());
        Assertions.assertEquals(localDate, pu2.getLocalDate());
        Assertions.assertEquals(PurchaseStatus.BOUGHT, pu2.getPurchaseStatus());
    }
}



