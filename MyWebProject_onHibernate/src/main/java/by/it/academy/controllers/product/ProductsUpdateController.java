package by.it.academy.controllers.product;

import by.it.academy.constants.Path;
import by.it.academy.entities.product.Product;
import by.it.academy.repositories.product.ProductApiRepository;
import by.it.academy.repositories.product.ProductRepository;
import by.it.academy.services.product.ProductApiService;
import by.it.academy.services.product.ProductService;
import lombok.extern.log4j.Log4j;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Сервлет обработки имеющегося количества продуктов на складе.
 * Доступен только для администратора.
 * В случае наличия параметров "productQuantity" и "id" возвращаемых с WEB-страницы выполнит обновление продуктов в
 * базе данных, после чего в любом случае получит полный список имеющихся продуктов в базе данных.
 * Передаст имеющиеся в базе данных продукты через атрибут HttpServletRequest для их отображения на странице.
 * По результатам обработки перенаправит на WEB-страницу обновления продуктов.
 */

@Log4j
@WebServlet(urlPatterns = "/update-products")
public class ProductsUpdateController extends HttpServlet {

    private final List<Product> products = new ArrayList<>();

    private final ProductRepository<Product> repository = new ProductApiRepository(products);
    private final ProductService<Product> service = new ProductApiService(repository);
    private static final String PRODUCT_LIST_PATH = Path.UPDATE_PRODUCTS_PATH;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        final String[] productQuantity = req.getParameterValues("productQuantity");
        final String[] productIds = req.getParameterValues("id");

        if (Objects.nonNull(productQuantity) && Objects.nonNull(productIds)) {
            service.updateProducts(req, productIds, productQuantity);
        }

        service.create(service.showProduct(req));

        final RequestDispatcher requestDispatcher = req.getRequestDispatcher(PRODUCT_LIST_PATH);
        req.setAttribute("productsQuantity", service.getProducts().size());
        req.setAttribute("products", service.getProducts());

        log.info("Received final data at the end of \"ProductsUpdateController\" (\"/update-products\") : " + service.getProducts());

        requestDispatcher.forward(req, resp);
    }
}