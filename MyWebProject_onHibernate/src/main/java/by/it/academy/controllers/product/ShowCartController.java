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
 * Сервлет обработки отображения продуктов в корзине.
 * Передаст добавленные в корзину продукты через атрибут HttpServletRequest для их отображения на странице.
 * По результатам обработки перенаправит на WEB-страницу корзины покупок.
 */

@Log4j
@WebServlet(urlPatterns = "/show-cart")
public class ShowCartController extends HttpServlet {

    private final List<Product> products = new ArrayList<>();

    private final ProductRepository<Product> repository = new ProductApiRepository(products);
    private final ProductService<Product> service = new ProductApiService(repository);
    private static final String PRODUCT_LIST_PATH = Path.CART_PATH;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        final String productId = req.getParameter("id");

        final Product product = service.showProductById(req, productId);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher(PRODUCT_LIST_PATH);

        if (Objects.nonNull(product)) {
            req.setAttribute("product", product);
        } else {
            requestDispatcher = req.getRequestDispatcher(Path.NO_PAGE_PATH);
        }

        log.info("Received final data at the end of \"ShowCartController\" (\"/show-cart\") : " + product);

        requestDispatcher.forward(req, resp);
    }
}