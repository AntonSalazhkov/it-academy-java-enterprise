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
import java.io.IOException;
import java.util.Objects;

    /**
     * Сервлет обработки регистрации пользователя.

     * В случае наличия параметров "login", "email" и "password" производит их проверку на соответствие корректности
     * ввода (по паттерну), а также уникальность "login" к имеющимся в базе данных.
     * При совпадении данных параметров выполнит авторизацию пользователя (через контроллер) и вернет на главную страцницу.
     * При не совпадении, нарушении уникальности или ошибках пользователя при вводе - вернет на страницу регистрации с
     * соответствующим текстовым сообщением.
    */

@Log4j
@WebServlet(urlPatterns = "/registration")
public class RegistrationController extends HttpServlet {

    private final UserRepository<User> repository = new UserApiRepository();
    private final UserService<User> service = new UserApiService(repository);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userPath;

        final String login = req.getParameter("login").toLowerCase();
        final String email = req.getParameter("email");
        final String password = req.getParameter("password");

        service.addUser(req, service.registrationUser(login, email, password));

        if (Objects.nonNull(req.getSession()) && Objects.nonNull(service.getUser())) {

            AuthorizationController.setSessionAttribute(req, service.getUser());
            userPath = Path.INDEX_PATH;
        } else {
            req.setAttribute("incorrectRegistration", Messages.INCORRECT_REGISTRATION);
            userPath = Path.REGISTER_PATH;
        }

        final RequestDispatcher requestDispatcher = req.getRequestDispatcher(userPath);

        log.info("Received final data at the end of \"RegistrationController\" (\"/registration\") : " + service.getUser());

        requestDispatcher.forward(req, resp);
    }
}
