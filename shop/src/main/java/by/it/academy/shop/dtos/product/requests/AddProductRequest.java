package by.it.academy.shop.dtos.product.requests;

import by.it.academy.shop.entities.product.ProductCategory;
import by.it.academy.shop.entities.product.ProductColour;
import by.it.academy.shop.entities.product.ProductType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddProductRequest {

    @NotBlank
    private String imagePath;

    @NotBlank
    private String name;

    @NotBlank
    private ProductCategory productCategory;

    @NotBlank
    private ProductType productType;

    @NotBlank
    private ProductColour productColour;

    @NotBlank
    private String productDetails;

    @NotBlank
    private String sizeClothes;

    @NotBlank
    private int price;

    @NotBlank
    private int inStock;
}
