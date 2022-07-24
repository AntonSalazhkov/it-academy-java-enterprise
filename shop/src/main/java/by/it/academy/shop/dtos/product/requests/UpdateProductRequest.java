package by.it.academy.shop.dtos.product.requests;

import by.it.academy.shop.entities.product.ProductCategory;
import by.it.academy.shop.entities.product.ProductColour;
import by.it.academy.shop.entities.product.ProductType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.UUID;

/**
 * Запрос по обнавлению продукта.
 * Присутствует валидация по полям.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateProductRequest {

    @NotNull
    private UUID id;

    @NotBlank
    private String imagePath;

    @NotBlank
    private String name;

    @NotNull
    private ProductCategory productCategory;

    @NotNull
    private ProductType productType;

    @NotNull
    private ProductColour productColour;

    @NotBlank
    private String productDetails;

    @NotBlank
    private String sizeClothes;

    @Pattern(regexp = "\\d+")
    private String price;

    @Pattern(regexp = "\\d+")
    private String quantity;
}
