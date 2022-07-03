package by.it.academy.filters;

import by.it.academy.constants.Path;
import by.it.academy.entities.user.User;
import by.it.academy.repositories.user.UserApiRepository;
import by.it.academy.repositories.user.UserRepository;
import by.it.academy.services.user.UserApiService;
import by.it.academy.services.user.UserService;
import lombok.extern.log4j.Log4j;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;

/**
 * Фильтр добавления продуктов в корзину.
 * При переходе на страницу каталога проверяется наличие атрибута HttpSession "productAddToCart". При его наличии
 * происходит создание сущности Purchase с добавлением туда выбранного продукта и текущего пользователя.
 * Если пользователь не авторизирован, то перед созданием сущности Purchase происходит перенаправление
 * для последующей авторизации пользователя.
 */

@Log4j
@WebFilter(urlPatterns = "/catalog-search")
public class AddProductToCartFilter implements Filter {

    private final UserRepository<User> repository = new UserApiRepository();
    private final UserService<User> service = new UserApiService(repository);
    private static final String USER_PATH = Path.REGISTER_PATH;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        final HttpSession session = httpServletRequest.getSession();

        if (Objects.nonNull(session.getAttribute("productAddToCart"))) {

            if (Objects.nonNull(session.getAttribute("user"))) {

                User user = (User) session.getAttribute("user");

                service.updateUser(httpServletRequest, user);

                log.info("Received final data at the end of \"AddProductToCartFilter\" (\"/catalog-search\") : user = "
                        + session.getAttribute("user") + ", " + "product = " + session.getAttribute("productAddToCart"));

                filterChain.doFilter(servletRequest, servletResponse);
            } else {
                RequestDispatcher requestDispatcher = httpServletRequest.getRequestDispatcher(USER_PATH);

                log.info("Received final data at the end of \"AddProductToCartFilter\" (\"/catalog-search\") : user = "
                        + session.getAttribute("user"));

                requestDispatcher.forward(httpServletRequest, servletResponse);
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
