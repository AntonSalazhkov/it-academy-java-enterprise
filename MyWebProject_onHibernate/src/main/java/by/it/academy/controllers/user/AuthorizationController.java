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

    /**
     * Сервлет обработки авторизации пользователя.

     * В случае наличия параметров "login" и "password" производит их проверку на соответствие имеющимся в базе данных.
     * При совпадении данных параметров задаст полученного пользователя как атрибут "user" HttpSession и вернет на
     * главную страницу, при не совпадении каких-либо параметров вернет на страницу регистрации с соответствующим текстовым сообщением.

     * В случае наличия параметра "logOut" обнулит атрибут "user" HttpSession.
    */

@Log4j
@WebServlet(urlPatterns = "/authorization")
public class AuthorizationController extends HttpServlet {

    private final UserRepository<User> repository = new UserApiRepository();
    private final UserService<User> service = new UserApiService(repository);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userPath = Path.INDEX_PATH;

        final String login = req.getParameter("login");
        final String password = req.getParameter("password");
        final String logOut = req.getParameter("logOut");

        if (Objects.nonNull(logOut) && logOut.equals("logOut")) {

            req.getSession().removeAttribute("user");
            req.getSession().removeAttribute("userType");     //todo del
            req.getSession().removeAttribute("login");        //todo del

        } else {
            service.create(service.authorizationUser(req, login.toLowerCase(), password));

            if (Objects.nonNull(req.getSession()) && Objects.nonNull(service.getUser())) {

                setSessionAttribute(req, service.getUser());

            } else {
                req.setAttribute("incorrectAuthorization", Messages.INCORRECT_AUTHORIZATION);
                userPath = Path.REGISTER_PATH;
            }
        }

        final RequestDispatcher requestDispatcher = req.getRequestDispatcher(userPath);

        log.info("Received final data at the end of \"AuthorizationController\" (\"/authorization\") : " + service.getUser());

        requestDispatcher.forward(req, resp);
    }

    public static void setSessionAttribute(HttpServletRequest req, User user) {
        HttpSession session = req.getSession();
        session.setAttribute("userType", user.getUserType().name().toLowerCase());
        session.setAttribute("login", user.getLogin());

        session.setAttribute("user", user);              // todo только это
    }
}
