package by.it.academy.shop.controllers.product;

import by.it.academy.shop.dtos.product.requests.ShowDetailsRequest;
import by.it.academy.shop.entities.product.Product;
import by.it.academy.shop.services.product.ProductService;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * Контроллер обработки отображения деталей продукта.
 * Принимает поступающий "id" в виде UUID, производит поиск совпадения в базе данных.
 * По результатам поиска вернет искомый продукт или вернет null в случае не успешного поиска.
 */

@Slf4j
@RestController
@RequestMapping("/view-details")
@RequiredArgsConstructor
public class ShowDetailsController {

    private final ProductService productService;

    @GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Product showProductById(@RequestBody @Validated ShowDetailsRequest showDetailsRequest) {
        return productService.showProductById(showDetailsRequest);
    }
}
