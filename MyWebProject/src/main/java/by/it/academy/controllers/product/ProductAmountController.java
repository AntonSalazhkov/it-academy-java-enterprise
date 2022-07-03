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

@Log4j
@WebServlet(urlPatterns = "/show-total-cost")
public class ProductAmountController extends HttpServlet {

    private final List<Product> products = new ArrayList<>();

    private final ProductRepository<Product> repository = new ProductApiRepository(products);
    private final ProductService<Product> service = new ProductApiService(repository);
    private static final String PRODUCT_LIST_PATH = Path.CART_PATH;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        final String productQuantity = req.getParameter("productQuantity");
        final int productId = Integer.parseInt(req.getParameter("id"));

        final Product product = service.showProductById(productId);
        final String totalCost = service.getValueBasket(product, productQuantity);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher(PRODUCT_LIST_PATH);

        if (Objects.nonNull(product)) {
            req.setAttribute("product", product);
            req.setAttribute("productQuantity", productQuantity);
            req.setAttribute("totalCost", totalCost);
        } else {
            requestDispatcher = req.getRequestDispatcher(Path.NO_PAGE_PATH);
        }

        log.info("Received final data at the end of \"ProductAmountController\" (\"/show-total-cost\") : " + product);

        requestDispatcher.forward(req, resp);
    }
}