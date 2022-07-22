package by.it.academy.shop.controllers.product;

import by.it.academy.shop.dtos.product.requests.AddProductRequest;
import by.it.academy.shop.dtos.product.requests.IdProductRequest;
import by.it.academy.shop.dtos.product.requests.ShowProductRequest;
import by.it.academy.shop.dtos.product.requests.UpdateProductRequest;
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
 * В случае наличия параметров, но отсутствия совпадений в базе данный вернет сообщение об не нахождения продуктов по заданному запросу.
 * <p>
 * Метод showProductById прослушивает адресс /details-view, принимает поступающий "id" продукта в виде UUID,
 * производит поиск совпадения в базе данных.
 * По результатам поиска вернет искомый продукт или вернет сообщение об не найденном объекте в случае не успешного поиска.
 * <p>
 * Метод addProduct прослушивает адресс /add-product, в случае корректности введеных данных, добавит новый продукт в базу дынных.
 * По результатам добавления вернет новый продукт или вернет сообщение о не корректных введенных данных в случае не успешного добавления.
 * <p>
 * Метод updateProduct прослушивает адресс /update-product, принимает поступающий "id" продукта в виде UUID и новые значения продукта,
 * в случае корректности введеных данных, обновит продукт в базе дынных.
 * По результатам обновления вернет обновленный продукт или вернет сообщение об не найденном объекте в случае не корректного id.
 * <p>
 * Метод deleteProduct прослушивает адресс /delete-product, принимает поступающий "id" продукта в виде UUID,
 * в случае корректности введеных данных, обнулит количество продукта на складе в базе дынных.
 * По результатам обнуления вернет сообщение о успешности операции или вернет сообщение об не найденном объекте в случае не корректного id.
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
    public Product showProductById(@RequestBody @Validated IdProductRequest idProductRequest) {
        return productService.showProductById(idProductRequest);
    }

    @RequestMapping("/add-product")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Product addProduct(@RequestBody @Validated AddProductRequest addProductRequest) {
        return productService.addProduct(addProductRequest);
    }

    @RequestMapping("/update-product")
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Product updateProduct(@RequestBody @Validated UpdateProductRequest updateProductRequest) {
        return productService.updateProduct(updateProductRequest);
    }

    @RequestMapping("/delete-product")
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public boolean deleteProduct(@RequestBody @Validated IdProductRequest idProductRequest) {
        return productService.clearStockProduct(idProductRequest);
    }
}
