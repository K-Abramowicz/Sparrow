package controller;

import static controller.HomeServlet.LOGIN;

import java.io.IOException;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.User;
import services.UserService;

@WebServlet(name = "registerServlet", urlPatterns = {"/register"})
public class RegisterServlet extends HttpServlet {

    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private static final String PASSWORD_REPEATED = "passwordRepeated";
    private static final String EMAIL = "email";
    private static final String LAST_NAME = "lastName";

    UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("WEB-INF/view/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String login = req.getParameter(LOGIN);
        String username = req.getParameter(USERNAME);
        String lastName = req.getParameter(LAST_NAME);
        String password = req.getParameter(PASSWORD);
        String passwordRepeated = req.getParameter(PASSWORD_REPEATED);
        String email = req.getParameter(EMAIL);

        if (!password.equals(passwordRepeated)) {
            req.setAttribute("hasError", "true");
            req.setAttribute("error", "Passwords does no match!");
            req.getRequestDispatcher("WEB-INF/view/register.jsp").forward(req, resp);
        } else {
            User user = new User();
            user.setLogin(login);
            user.setName(username);
            user.setLastName(lastName);
            user.setPassword(password);
            user.setEmail(email);
            user.setDateOfRegistration(new Date());

            String registerStatus = userService.registerUser(user);


            if (!registerStatus.equals(UserService.SUCCESS)) {
                if (registerStatus.equals(UserService.EMAIL_AND_LOGIN_ERROR)) {
                    req.setAttribute("hasError", "true");
                    req.setAttribute("error", "Email  and Login are already used in our database!");
                    req.getRequestDispatcher("WEB-INF/view/register.jsp").forward(req, resp);
                } else if (registerStatus.equals(UserService.EMAIL_ERROR)) {
                    req.setAttribute("hasError", "true");
                    req.setAttribute("error", "Email is already used in our database!");
                    req.getRequestDispatcher("WEB-INF/view/register.jsp").forward(req, resp);
                } else if (registerStatus.equals(UserService.LOGIN_ERROR)) {
                    req.setAttribute("hasError", "true");
                    req.setAttribute("error", "Login is already used in our database!");
                    req.getRequestDispatcher("WEB-INF/view/register.jsp").forward(req, resp);
                }
            }
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        }
    }
}