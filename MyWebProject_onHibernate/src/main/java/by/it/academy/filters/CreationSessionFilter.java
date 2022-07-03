package by.it.academy.filters;

import by.it.academy.util.JPAUtil;
import lombok.extern.log4j.Log4j;

import javax.persistence.EntityManager;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Фильтр создания сессии.
 * При первичном переходе на страницу происходит создания сессии подключения к базе данных через присвоение
 * атрибута "sessionJPA" к HttpSession.
 * При закрытии вкладки браузера пользователем происходит обнуление атрибута HttpSession.
 */

@Log4j
@WebFilter(urlPatterns = "/index.jsp")
public class CreationSessionFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        final HttpSession httpSession = httpServletRequest.getSession();

        if (httpSession.isNew()) {
            EntityManager entityManagerJPA = JPAUtil.getEntityManagerFactory().createEntityManager();

            httpSession.setAttribute("sessionJPA", entityManagerJPA);

            log.info("Session creation");
        }
        httpSession.setMaxInactiveInterval(-1);

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
