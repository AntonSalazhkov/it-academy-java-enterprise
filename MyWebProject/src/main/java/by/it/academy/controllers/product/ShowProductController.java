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

@Log4j
@WebServlet(urlPatterns = "/catalog-search")
public class ShowProductController extends HttpServlet {

    private final List<Product> products = new ArrayList<>();

    private final ProductRepository<Product> repository = new ProductApiRepository(products);
    private final ProductService<Product> service = new ProductApiService(repository);
    private static final String PRODUCT_LIST_PATH = Path.CATALOG_PATH;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        final String[] userChoiceCategories = req.getParameterValues("userChoiceCategories");
        final String[] userChoiceTypes = req.getParameterValues("userChoiceTypes");
        final String[] userChoiceColours = req.getParameterValues("userChoiceColours");

        service.create(service.showProduct(userChoiceCategories, userChoiceTypes, userChoiceColours));

        final RequestDispatcher requestDispatcher = req.getRequestDispatcher(PRODUCT_LIST_PATH);
        req.setAttribute("productsQuantity", service.getProducts().size());
        req.setAttribute("products", service.getProducts());

        log.info("Received final data at the end of \"ShowProductController\" (\"/catalog-search\") : " + service.getProducts());

        requestDispatcher.forward(req, resp);
    }
}
