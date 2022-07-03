package by.it.academy.controllers.product;

import by.it.academy.constants.Path;
import by.it.academy.entities.product.Product;
import by.it.academy.entities.product.ProductCategories;
import by.it.academy.entities.product.ProductColours;
import by.it.academy.entities.product.ProductType;
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

/**
 * Сервлет добавления нового продукта, доступен только для администратора.
 * По результатам обработки перенаправляет на страницу успешно или не успешно добавленного продукта.
 */

@Log4j
@WebServlet(urlPatterns = "/add-product")
public class AddProductController extends HttpServlet {

    private final List<Product> products = new ArrayList<>();

    private final ProductRepository<Product> repository = new ProductApiRepository(products);
    private final ProductService<Product> service = new ProductApiService(repository);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String productListPath;

        final String imagePath = req.getParameter("imagePath");
        final String name = req.getParameter("name");
        final String categories = req.getParameter("categories");
        final String type = req.getParameter("type");
        final String colours = req.getParameter("colours");
        final String productDetails = req.getParameter("productDetails");
        final String sizeClothes = req.getParameter("sizeClothes");
        final int price = Integer.parseInt(req.getParameter("price"));
        final int inStock = Integer.parseInt(req.getParameter("inStock"));

        final Product product = new Product(imagePath, name, ProductCategories.valueOf(categories.toUpperCase()),
                ProductType.valueOf(type.toUpperCase()), ProductColours.valueOf(colours.toUpperCase()),
                productDetails, sizeClothes, price, inStock);

        if (service.addProduct(req, product)) {
            productListPath = Path.PRODUCT_ADDED_SUCCESSFULLY;
        } else {
            productListPath = Path.PRODUCT_NOT_ADDED;
        }

        final RequestDispatcher requestDispatcher = req.getRequestDispatcher(productListPath);
        req.setAttribute("product", product);

        log.info("Received final data at the end of \"AddProductController\" (\"/add-product\") : " + product);

        requestDispatcher.forward(req, resp);
    }
}
