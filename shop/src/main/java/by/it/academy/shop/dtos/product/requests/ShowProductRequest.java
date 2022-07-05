package by.it.academy.shop.dtos.product.requests;

import by.it.academy.shop.entities.product.ProductCategory;
import by.it.academy.shop.entities.product.ProductColour;
import by.it.academy.shop.entities.product.ProductType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShowProductRequest {

    @NotBlank
    private List<ProductCategory> productCategories;

    @NotBlank
    private List<ProductType> productTypes;

    @NotBlank
    private List<ProductColour> productColours;
}
