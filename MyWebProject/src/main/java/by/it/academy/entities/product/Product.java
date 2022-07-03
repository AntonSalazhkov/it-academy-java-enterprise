package by.it.academy.entities.product;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Product {

    int id;
    String imagePath;
    String name;
    ProductCategories productCategories;
    ProductType productType;
    ProductColours productColours;
    String productDetails;
    String sizeClothes;
    int price;
    int inStock;

    // AddProductController, no id
    public Product(String imagePath, String name, ProductCategories productCategories, ProductType productType,
                   ProductColours productColours, String productDetails, String sizeClothes, int price, int inStock) {
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

    public Product(int id, String imagePath, String name, int productCategories, int productType,
                   int productColours, String productDetails, String sizeClothes, int price, int inStock) {
        this.id = id;
        this.imagePath = imagePath;
        this.name = name;
        this.productCategories = ProductCategories.values()[productCategories];
        this.productType = ProductType.values()[productType];
        this.productColours = ProductColours.values()[productColours];
        this.productDetails = productDetails;
        this.sizeClothes = sizeClothes;
        this.price = price;
        this.inStock = inStock;
    }
}
