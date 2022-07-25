package by.it.academy.shop.entities.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

/**
 * Сущность Product, записываемая в базу данных как таблица PRODUCTS.
 * Имеет enum поля productCategory, productType и productColour.
 */

@Entity
@Table(name = "product")
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

    @Column(name = "PRODUCT_NAME")
    private String name;

    @Column(name = "PRODUCT_CATEGORY")
    @Enumerated
    private ProductCategory productCategory;

    @Column(name = "PRODUCT_TYPE")
    @Enumerated
    private ProductType productType;

    @Column(name = "PRODUCT_COLOUR")
    @Enumerated
    private ProductColour productColour;

    @Column(name = "PRODUCT_DETAILS")
    private String productDetails;

    @Column(name = "SIZE_CLOTHES")
    private String sizeClothes;

    private int price;
    private int quantity;
}
