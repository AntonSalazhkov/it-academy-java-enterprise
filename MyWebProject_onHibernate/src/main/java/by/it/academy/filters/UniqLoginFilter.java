package by.it.academy.filters;

import by.it.academy.constants.Messages;
import by.it.academy.constants.Path;
import by.it.academy.entities.user.User;
import by.it.academy.repositories.user.UserApiRepository;
import by.it.academy.repositories.user.UserRepository;
import by.it.academy.services.user.UserApiService;
import by.it.academy.services.user.UserService;
import lombok.extern.log4j.Log4j;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Optional;

/**
 * Фильтр проверки уникальности логина.
 * Производит проверку уникальности логина и в случае нахождения возвращает назад на страницу регистрации
 * с соответствующим текстовым сообщением.
 */

@Log4j
@WebFilter(urlPatterns = "/registration")
public class UniqLoginFilter implements Filter {

    private final UserRepository<User> repository = new UserApiRepository();
    private final UserService<User> service = new UserApiService(repository);
    private static final String USER_PATH = Path.REGISTER_PATH;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;

        Optional<User> userOptional = service.getUsersFromDatabase(httpServletRequest)
                .stream()
                .filter(user -> user.getLogin().equals(servletRequest.getParameter("login").toLowerCase()))
                .findFirst();

        if (userOptional.isPresent()) {
            RequestDispatcher requestDispatcher = servletRequest.getRequestDispatcher(USER_PATH);
            servletRequest.setAttribute("incorrectRegistration", Messages.INCORRECT_UNIQUE_LOGIN);

            log.info("Received final data at the end of \"UniqLoginFilter\" (\"/registration\") : inadmissible login = "
                    + servletRequest.getParameter("login").toLowerCase());
            requestDispatcher.forward(servletRequest, servletResponse);
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }
}
