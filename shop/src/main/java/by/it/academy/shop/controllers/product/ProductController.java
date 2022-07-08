package by.it.academy.shop.controllers.product;

import by.it.academy.shop.dtos.product.requests.AddProductRequest;
import by.it.academy.shop.dtos.product.requests.ShowDetailsRequest;
import by.it.academy.shop.dtos.product.requests.ShowProductRequest;
import by.it.academy.shop.entities.product.Product;
import by.it.academy.shop.services.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Контроллер обработки продуктов.
 * Метод showProducts прослушивает адресс /catalog-search, в случае наличия списков параметров
 * "productCategories", "productTypes", "productColours", "userInputProductName" возвращаемых с WEB-страницы
 * выполнит поиск продуктов в базе данных с учетом соответствия всем данным параметрам,
 * в случае их отсутствия вернет все продукты имеющиеся в базе данных.
 * Передаст полученные в базе данных продукты в виде списка для их отображения на странице.
 * <p>
 * Метод showProductById прослушивает адресс /details-view, принимает поступающий "id" в виде UUID,
 * производит поиск совпадения в базе данных.
 * По результатам поиска вернет искомый продукт или вернет null в случае не успешного поиска.
 * <p>
 * Метод addProduct прослушивает адресс /add-product, в случае корректности введеных данных, добавит новый продукт в базу дынных.
 * По результатам добавления вернет новый продукт или вернет null в случае не успешного добавления.
 */

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @RequestMapping("/catalog-search")
    @GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public List<Product> showProducts(@RequestBody ShowProductRequest showProductRequests) {
        return productService.showProduct(showProductRequests);
    }

    @RequestMapping("/details-view")
    @GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Product showProductById(@RequestBody @Validated ShowDetailsRequest showDetailsRequest) {
        return productService.showProductById(showDetailsRequest);
    }

    @RequestMapping("/add-product")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Product addProduct(@RequestBody @Validated AddProductRequest addProductRequest) {
        return productService.addProduct(addProductRequest);
    }
}
