package by.it.academy.shop.controllers.product;

import by.it.academy.shop.dtos.product.requests.AddProductRequest;
import by.it.academy.shop.entities.product.Product;
import by.it.academy.shop.services.product.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * Контроллер добавления нового продукта.
 * По результатам добавления вернет новый продукт или вернет null в случае не успешного добавления.
 */

@Slf4j
@RestController
@RequestMapping("/add-product")
@RequiredArgsConstructor
public class AddProductController {

    private final ProductService productService;

    @GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Product showProduct(@RequestBody @Validated AddProductRequest addProductRequest) {
        return productService.addProduct(addProductRequest);
    }
}
