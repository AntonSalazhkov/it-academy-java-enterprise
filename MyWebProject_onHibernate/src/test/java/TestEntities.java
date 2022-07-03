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
        //Product p2 = new Product("22", "img/product10.jpg", "dresses", 1, 2, 4,
       //         "Good denim dresses 2022", "X, XL", 34, 43);



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

//        Assert.assertEquals("22", p2.getId());
//        Assert.assertEquals("img/product10.jpg", p2.getImagePath());
//        Assert.assertEquals("dresses", p2.getName());
//        Assert.assertEquals(ProductCategories.LADIES, p2.getProductCategories());
//        Assert.assertEquals(ProductType.DRESSES, p2.getProductType());
//        Assert.assertEquals(ProductColours.RED, p2.getProductColours());
//        Assert.assertEquals("Good denim dresses 2022", p2.getProductDetails());
//        Assert.assertEquals("X, XL", p2.getSizeClothes());
//        Assert.assertEquals(34, p2.getPrice());
//        Assert.assertEquals(43, p2.getInStock());

        Assert.assertNull(p3.getId());
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
//        User u2 = new User("max", "qwer@gmail.com", "1234asdf", 1);

        Assert.assertNull(u1.getId());
        Assert.assertNull(u1.getLogin());
        Assert.assertNull(u1.getEmail());
        Assert.assertNull(u1.getPassword());
        Assert.assertNull(u1.getUserType());

//        Assert.assertNull(u2.getId());
//        Assert.assertEquals("max", u2.getLogin());
//        Assert.assertEquals("qwer@gmail.com", u2.getEmail());
//        Assert.assertEquals("1234asdf", u2.getPassword());
//        Assert.assertEquals(UserType.USER, u2.getUserType());
    }


//    String login, String email, String password, int userType) {
//    String id;
//
//    String login;
//    String email;
//    String password;




}
//    @Test
//    public void testByn() {
//        Byn byn = new Byn();
//        Assert.assertEquals(byn, new Byn());
//
//        Byn byn2 = new Byn(566);
//        Assert.assertEquals(byn2, new Byn(566));
//
//        Byn byn3 = new Byn(2, 56);
//        Assert.assertEquals(byn3, new Byn(256));
//
//        Byn byn4 = new Byn(new Byn(345));
//        Byn byn5 = new Byn(byn4);
//        Assert.assertEquals(byn4, byn5);
//    }
//
//    @Test
//    public void testMathOperations() {
//        Byn byn = new Byn(10);
//        Assert.assertEquals(new Byn(15), byn.add(new Byn(5)));
//        Assert.assertEquals(new Byn(471), byn.add(new Byn(456)));
//        Assert.assertEquals(new Byn(466), byn.sub(new Byn(5)));
//        Assert.assertEquals(new Byn(360), byn.sub(new Byn(106)));
//        Assert.assertEquals(new Byn(1800), byn.mul(new Byn(5)));
//        Assert.assertEquals(new Byn(41400), byn.mul(new Byn(23)));
//        Assert.assertEquals(new Byn(82800), byn.mul(2));
//        Assert.assertEquals(new Byn(1159200), byn.mul(14));
//
//        Byn byn2 = new Byn(2345);
//        Assert.assertEquals(new Byn(5700), byn2.mul(2.46, FLOOR, 2));
//        Assert.assertEquals(new Byn(357162), byn2.mul(62.66, CEIL, 0));
//        Assert.assertEquals(new Byn(5000000), byn2.mul(12.7, ROUND, 6));
//
//        Byn byn3 = new Byn(26745);
//        Assert.assertEquals(new Byn(26700), byn3.round(FLOOR, 2));
//        Assert.assertEquals(new Byn(26700), byn3.round(CEIL, 0));
//        Assert.assertEquals(new Byn(27000), byn3.round(ROUND, 3));
//
//        Byn byn4 = new Byn(678);
//        Assert.assertEquals(0, byn4.compareTo(new Byn(678)));
//        Assert.assertEquals(-10, byn4.compareTo(new Byn(688)));
//        Assert.assertEquals(10, byn4.compareTo(new Byn(668)));
//    }
//
//    @Test
//    public void testEntityCreation() {
//        Purchase p1 = new Purchase("Milk", new Byn(1453), 8);
//        PriceDiscountPurchase p2 = new PriceDiscountPurchase("Cucumber", new Byn(567), 4, new Byn(130));
//        PercentDiscountPurchase p3 = new PercentDiscountPurchase("Chicken", new Byn(3457), 2, 5.35);
//
//        Assert.assertEquals("Milk", p1.getName());
//        Assert.assertEquals(new Byn(1453), p1.getPrice());
//        Assert.assertEquals(8, p1.getNumber());
//        Assert.assertEquals("Purchase", p1.getClass().getSimpleName());
//
//        Assert.assertEquals("Cucumber", p2.getName());
//        Assert.assertEquals(new Byn(567), p2.getPrice());
//        Assert.assertEquals(4, p2.getNumber());
//        Assert.assertEquals(new Byn(130), p2.getDiscountAmount());
//        Assert.assertEquals("PriceDiscountPurchase", p2.getClass().getSimpleName());
//
//        Assert.assertEquals("Chicken", p3.getName());
//        Assert.assertEquals(new Byn(3457), p3.getPrice());
//        Assert.assertEquals(2, p3.getNumber());
//        Assert.assertEquals(5.35, p3.getDiscountPercent(), 0.0);
//        Assert.assertEquals("PercentDiscountPurchase", p3.getClass().getSimpleName());
//    }
//
//    @Test
//    public void testGetCost() {
//        Purchase p1 = new Purchase("Milk", new Byn(1453), 8);
//        PriceDiscountPurchase p2 = new PriceDiscountPurchase("Milk", new Byn(567), 4, new Byn(130));
//        PercentDiscountPurchase p3 = new PercentDiscountPurchase("Milk", new Byn(3457), 2, 5.35);
//        PercentDiscountPurchase p4 = new PercentDiscountPurchase("Milk", new Byn(3457), 6, 5.35);
//
//        Assert.assertEquals(new Byn(1453), p1.getPrice());
//        Assert.assertEquals(new Byn(11624), p1.getCost());
//        Assert.assertEquals(new Byn(1453), p1.getPrice());
//        Assert.assertEquals(14, p1.getPrice().getRubs());
//        Assert.assertEquals(53, p1.getPrice().getCoins());
//
//        Assert.assertEquals(new Byn(567), p2.getPrice());
//        Assert.assertEquals(new Byn(1748), p2.getCost());
//        Assert.assertEquals(new Byn(567), p2.getPrice());
//        Assert.assertEquals(5, p2.getPrice().getRubs());
//        Assert.assertEquals(67, p2.getPrice().getCoins());
//
//        Assert.assertEquals(new Byn(3457), p3.getPrice());
//        Assert.assertEquals(new Byn(6914), p3.getCost());
//        Assert.assertEquals(new Byn(3457), p3.getPrice());
//        Assert.assertEquals(34, p3.getPrice().getRubs());
//        Assert.assertEquals(57, p3.getPrice().getCoins());
//
//        Assert.assertEquals(new Byn(3457), p4.getPrice());
//        Assert.assertEquals(new Byn(19632), p4.getCost());
//        Assert.assertEquals(new Byn(3457), p4.getPrice());
//        Assert.assertEquals(34, p4.getPrice().getRubs());
//        Assert.assertEquals(57, p4.getPrice().getCoins());
//    }
//
//    @Test
//    public void testEquals() {
//        Purchase p1 = new Purchase("Milk", new Byn(1453), 8);
//        PriceDiscountPurchase p2 = new PriceDiscountPurchase("Milk", new Byn(1453), 4, new Byn(130));
//        PercentDiscountPurchase p3 = new PercentDiscountPurchase("Milk", new Byn(1453), 2, 5.35);
//
//        Purchase p4 = new Purchase("Milk", new Byn(2500), 8);
//        PriceDiscountPurchase p5 = new PriceDiscountPurchase("Chicken", new Byn(1453), 4, new Byn(130));
//        PercentDiscountPurchase p6 = new PercentDiscountPurchase("Milk", new Byn(2500), 2, 5.35);
//
//        Assert.assertEquals(p1, p2);
//        Assert.assertEquals(p2, p3);
//        Assert.assertEquals(p3, p1);
//
//        Assert.assertNotEquals(p1, p4);
//        Assert.assertNotEquals(p3, p5);
//        Assert.assertNotEquals(p2, p6);
//    }
//
//    @Test
//    public void testFactory() {
//        Scanner sc1 = new Scanner("GENERAL_PURCHASE Milk 2155 3");
//        Scanner sc2 = new Scanner("PRICE_DISCOUNT_PURCHASE Cucumber 3457 4 125");
//        Scanner sc3 = new Scanner("PERCENT_DISCOUNT_PURCHASE Chicken 2356 6 4,22");
//
//        Purchase p1 = PurchasesFactory.getPurchaseFromFactory(sc1);
//        Purchase p2 = PurchasesFactory.getPurchaseFromFactory(sc2);
//        Purchase p3 = PurchasesFactory.getPurchaseFromFactory(sc3);
//
//        Assert.assertEquals(new Purchase("Milk", new Byn(2155), 3), p1);
//        Assert.assertEquals(new PriceDiscountPurchase("Cucumber", new Byn(3457), 4, new Byn(125)), p2);
//        Assert.assertEquals(new PercentDiscountPurchase("Chicken", new Byn(2356), 6, 4.22), p3);
//    }