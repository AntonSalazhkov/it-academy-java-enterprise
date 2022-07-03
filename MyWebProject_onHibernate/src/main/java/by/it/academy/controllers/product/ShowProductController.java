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
 * Сервлет обработки отображения продуктов на странице каталога.
 * В случае наличия параметров "userChoiceCategories", "userChoiceTypes", "userChoiceColours" и "searchQuery"
 * возвращаемых с WEB-страницы выполнит поиск продуктов в базе данных с учетом соответствия всем данным параметрам,
 * в случае их отсутствия вернет все продукты имеющиеся в базе данных.
 * Передаст полученные в базе данных продукты через атрибут HttpServletRequest для их отображения на странице.
 * По результатам обработки перенаправит на WEB-страницу каталога продуктов.
 */

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
        final String searchQuery = req.getParameter("searchQuery");

        if (Objects.nonNull(req.getParameter("productAddToCartId"))) {
            final String addProductId = req.getParameter("productAddToCartId");
            final Product productAddToCart = service.showProductById(req, addProductId);

            req.getSession().setAttribute("productAddToCart", productAddToCart);
        }

        service.create(service.showProduct(req, userChoiceCategories, userChoiceTypes, userChoiceColours));

        if (Objects.nonNull(searchQuery)) {
            service.create(service.searchProducts(req, searchQuery));
        }

        final RequestDispatcher requestDispatcher = req.getRequestDispatcher(PRODUCT_LIST_PATH);
        req.setAttribute("productsQuantity", service.getProducts().size());
        req.setAttribute("products", service.getProducts());

        log.info("Received final data at the end of \"ShowProductController\" (\"/catalog-search\") : " + service.getProducts());

        requestDispatcher.forward(req, resp);
    }
}
