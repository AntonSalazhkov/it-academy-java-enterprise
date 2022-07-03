package by.it.academy.entities.product;

import by.it.academy.util.annotation.SetUUID;
import by.it.academy.util.annotation.SetUUIDAnnotationAnalyzer;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Сущность Product, записываемая в базу данных как таблица PRODUCT.
 * Дополнительно имеется конструктор без поля "id".
 *
 * @SetUUID является собственной аннотацией с вызовом обработчика в конструкторе как SetUUIDAnnotationAnalyzer.parse(this);
 * Аннотация устанавливает полю "id" - UUID, если таковое имеется и оно равно null.
 */

@Entity
@Table(name = "PRODUCTS_2")
@Data
@NoArgsConstructor
@SetUUID           // Own annotation
public class Product {

    @Id
    private String id;

    @Column(name = "IMAGE_PATH")
    private String imagePath;

    @Column(name = "PRODUCTS_NAME")
    private String name;

    @Column(name = "CATEGORIES_ID")
    @Enumerated
    private ProductCategories productCategories;

    @Column(name = "TYPE_ID")
    @Enumerated
    private ProductType productType;

    @Column(name = "COLOURS_ID")
    @Enumerated
    private ProductColours productColours;

    @Column(name = "PRODUCT_DETAILS")
    private String productDetails;

    @Column(name = "SIZE_CLOTHES")
    private String sizeClothes;

    private int price;

    @Column(name = "IN_STOCK")
    private int inStock;

    // AddProductController, no id
    public Product(String imagePath, String name, ProductCategories productCategories, ProductType productType,
                   ProductColours productColours, String productDetails, String sizeClothes, int price, int inStock) {
        SetUUIDAnnotationAnalyzer.parse(this);
        this.imagePath = imagePath;
        this.name = name;
        this.productCategories = productCategories;
        this.productType = productType;
        this.productColours = productColours;
        this.productDetails = productDetails;
        this.sizeClothes = sizeClothes;
        this.price = price;
        this.inStock = inStock;
    }
}
