package by.it.academy.shop.entities.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

/**
 * Сущность Product, записываемая в базу данных как таблица PRODUCT.
 * Имеет enum поля productCategory, productType и productColour.
 */

@Entity
@Table(name = "PRODUCTS_2")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "IMAGE_PATH")
    private String imagePath;

    @Column(name = "PRODUCTS_NAME")
    private String name;

    @Column(name = "CATEGORY_ID")
    @Enumerated
    private ProductCategory productCategory;

    @Column(name = "TYPE_ID")
    @Enumerated
    private ProductType productType;

    @Column(name = "COLOUR_ID")
    @Enumerated
    private ProductColour productColour;

    @Column(name = "PRODUCT_DETAILS")
    private String productDetails;

    @Column(name = "SIZE_CLOTHES")
    private String sizeClothes;

    private int price;

    @Column(name = "IN_STOCK")
    private int inStock;

    // AddProductController, no id
    public Product(String imagePath, String name, ProductCategory productCategory, ProductType productType,
                   ProductColour productColour, String productDetails, String sizeClothes, int price, int inStock) {
        this.imagePath = imagePath;
        this.name = name;
        this.productCategory = productCategory;
        this.productType = productType;
        this.productColour = productColour;
        this.productDetails = productDetails;
        this.sizeClothes = sizeClothes;
        this.price = price;
        this.inStock = inStock;
    }
}
