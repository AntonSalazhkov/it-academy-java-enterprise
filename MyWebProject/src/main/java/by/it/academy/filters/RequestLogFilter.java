package by.it.academy.filters;

import lombok.extern.log4j.Log4j;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Log4j
@WebFilter(urlPatterns = {"/catalog-search", "/view-details", "/show-cart", "/authorization", "/registration", "/update-products"})
public class RequestLogFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        final HttpServletRequest request = (HttpServletRequest) servletRequest;
        final HttpServletResponse response = (HttpServletResponse) servletResponse;

        Map<String, ArrayList<String>> headers = getHeader(request);

        log.info(request.getRequestURI() + ": " + headers);

        filterChain.doFilter(request, response);
    }

    private Map<String, ArrayList<String>> getHeader(HttpServletRequest httpServletRequest) {
        return Collections.list(httpServletRequest.getHeaderNames())
                .stream()
                .collect(Collectors.toMap(
                        Function.identity(),
                        h -> Collections.list(httpServletRequest.getHeaders(h))));
    }
}