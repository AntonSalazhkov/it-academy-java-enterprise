package by.it.academy.shop.controllers.product;

import by.it.academy.shop.dtos.product.requests.ShowProductRequest;
import by.it.academy.shop.entities.product.Product;
import by.it.academy.shop.services.product.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Контроллер обработки отображения продуктов на странице каталога.
 * В случае наличия списков параметров "productCategories", "productTypes", "productColours"
 * возвращаемых с WEB-страницы выполнит поиск продуктов в базе данных с учетом соответствия всем данным параметрам,
 * в случае их отсутствия вернет все продукты имеющиеся в базе данных.
 * Передаст полученные в базе данных продукты в виде списка для их отображения на странице.
 */

@Slf4j
@RestController
@RequestMapping("/catalog-search")
@RequiredArgsConstructor
public class ShowProductController {

    private final ProductService productService;

    @GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public List<Product> showProduct(@RequestBody @Validated ShowProductRequest showProductRequests) {
        return productService.showProduct(showProductRequests);
    }
}
