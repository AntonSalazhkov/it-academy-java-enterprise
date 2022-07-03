package by.it.academy.controllers.user;

import by.it.academy.constants.Messages;
import by.it.academy.constants.Path;
import by.it.academy.entities.user.User;
import by.it.academy.repositories.user.UserApiRepository;
import by.it.academy.repositories.user.UserRepository;
import by.it.academy.services.user.UserApiService;
import by.it.academy.services.user.UserService;
import lombok.extern.log4j.Log4j;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

@Log4j
@WebServlet(urlPatterns = "/authorization")
public class AuthorizationController extends HttpServlet {

    private final UserRepository<User> repository = new UserApiRepository();
    private final UserService<User> service = new UserApiService(repository);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userPath;

        final String login = req.getParameter("login");
        final String password = req.getParameter("password");
        final String logOut = req.getParameter("logOut");

        if (Objects.nonNull(logOut) && logOut.equals("logOut")) {
            setSessionAttribute(req, null, null);
            userPath = Path.INDEX_PATH;
        } else {
            service.create(service.authorizationUser(login.toLowerCase(), password));

            if (Objects.nonNull(req.getSession()) && Objects.nonNull(service.getUser())) {
                setSessionAttribute(req, service.getUser().getUserType().name().toLowerCase(), service.getUser().getLogin());
                userPath = Path.INDEX_PATH;
            } else {
                req.setAttribute("incorrectAuthorization", Messages.INCORRECT_AUTHORIZATION);
                userPath = Path.REGISTER_PATH;
            }
        }

        final RequestDispatcher requestDispatcher = req.getRequestDispatcher(userPath);

        log.info("Received final data at the end of \"AuthorizationController\" (\"/authorization\") : " + service.getUser());

        requestDispatcher.forward(req, resp);
    }

    public static void setSessionAttribute(HttpServletRequest req, String userType, String login) {
        HttpSession session = req.getSession();
        session.setAttribute("userType", userType);
        session.setAttribute("login", login);
    }
}
