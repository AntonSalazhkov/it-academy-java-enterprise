package by.it.academy.filters;

import by.it.academy.constants.Path;
import lombok.extern.log4j.Log4j;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;

@Log4j
@WebFilter(urlPatterns = "/catalog-search")
public class AuthorizationFilter implements Filter {

    private static final String USER_PATH = Path.REGISTER_PATH;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        final HttpSession session = httpServletRequest.getSession();

        if (Objects.nonNull(session) && Objects.isNull(session.getAttribute("userType"))) {
            RequestDispatcher requestDispatcher = httpServletRequest.getRequestDispatcher(USER_PATH);

            log.info("Received final data at the end of \"AuthorizationFilter\" (\"/catalog-search\") : user type = "
                    + session.getAttribute("userType"));

            requestDispatcher.forward(httpServletRequest, servletResponse);

        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }
}