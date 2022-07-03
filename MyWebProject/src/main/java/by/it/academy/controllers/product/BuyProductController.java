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
@WebServlet(urlPatterns = "/buy-product")
public class BuyProductController extends HttpServlet {

    private final List<Product> products = new ArrayList<>();

    private final ProductRepository<Product> repository = new ProductApiRepository(products);
    private final ProductService<Product> service = new ProductApiService(repository);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String productListPath;

        final String productQuantity = req.getParameter("productQuantity");
        final int productId = Integer.parseInt(req.getParameter("id"));

        final Product product = service.showProductById(productId);

        if (service.buyProduct(product, productQuantity)) {
            productListPath = Path.SUCCESSFUL_PURCHASE;
        } else {
            productListPath = Path.NOT_SUCCESSFUL_PURCHASE;
        }

        RequestDispatcher requestDispatcher = req.getRequestDispatcher(productListPath);

        if (Objects.nonNull(product)) {
            req.setAttribute("product", product);
        } else {
            requestDispatcher = req.getRequestDispatcher(Path.NO_PAGE_PATH);
        }

        log.info("Received final data at the end of \"BuyProductController\" (\"/buy-product\") : " + product);

        requestDispatcher.forward(req, resp);
    }
}
