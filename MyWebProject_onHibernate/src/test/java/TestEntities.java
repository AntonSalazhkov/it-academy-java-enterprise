import by.it.academy.entities.product.Product;
import by.it.academy.entities.product.ProductCategories;
import by.it.academy.entities.product.ProductColours;
import by.it.academy.entities.product.ProductType;
import by.it.academy.entities.user.User;
import by.it.academy.entities.user.UserType;
import org.junit.Assert;
import org.junit.Test;


public class TestEntities {

    @Test
    public void testProductCreation() {
        Product p1 = new Product();

        Product p3 = new Product("img/product9.jpg", "coat", ProductCategories.MEN, ProductType.COAT,
                ProductColours.GREEN, "Good denim coat 2022", "L, X, XL", 12, 1);

        Assert.assertNull(p1.getId());
        Assert.assertNull(p1.getImagePath());
        Assert.assertNull(p1.getName());
        Assert.assertNull(p1.getProductCategories());
        Assert.assertNull(p1.getProductType());
        Assert.assertNull(p1.getProductColours());
        Assert.assertNull(p1.getProductDetails());
        Assert.assertNull(p1.getSizeClothes());
        Assert.assertEquals(0, p1.getPrice());
        Assert.assertEquals(0, p1.getInStock());

        Assert.assertEquals("img/product9.jpg", p3.getImagePath());
        Assert.assertEquals("coat", p3.getName());
        Assert.assertEquals(ProductCategories.MEN, p3.getProductCategories());
        Assert.assertEquals(ProductType.COAT, p3.getProductType());
        Assert.assertEquals(ProductColours.GREEN, p3.getProductColours());
        Assert.assertEquals("Good denim coat 2022", p3.getProductDetails());
        Assert.assertEquals("L, X, XL", p3.getSizeClothes());
        Assert.assertEquals(12, p3.getPrice());
        Assert.assertEquals(1, p3.getInStock());
    }

    @Test
    public void testUserCreation() {
        User u1 = new User();

        Assert.assertNull(u1.getId());
        Assert.assertNull(u1.getLogin());
        Assert.assertNull(u1.getEmail());
        Assert.assertNull(u1.getPassword());
        Assert.assertNull(u1.getUserType());
    }
}