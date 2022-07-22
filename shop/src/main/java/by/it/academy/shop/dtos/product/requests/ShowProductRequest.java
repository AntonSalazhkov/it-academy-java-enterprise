package by.it.academy.shop.dtos.product.requests;

import by.it.academy.shop.entities.product.ProductCategory;
import by.it.academy.shop.entities.product.ProductColour;
import by.it.academy.shop.entities.product.ProductType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Обработка запроса по отображению продуктов.
 * В поступающем запросе могут быть или не быть устанавливаемые пользователем параметры.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShowProductRequest {

    private List<ProductCategory> productCategories;
    private List<ProductType> productTypes;
    private List<ProductColour> productColours;
    private String userInputProductName;
}
