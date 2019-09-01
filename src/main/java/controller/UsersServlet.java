package controller;



import static controller.utils.ServletsUtils.LOGIN;

import controller.utils.ServletsUtils;
import dao.UserDAO;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.User;

@WebServlet(name = "usersServlet", urlPatterns = {"/users"})
public class UsersServlet extends HttpServlet {


    private UserDAO userDao = new UserDAO();


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String currentUserLogin = ServletsUtils.getUserLoginFromSession(request);
        List<User> followedUsers = userDao.getFollowedUsers(currentUserLogin);
        List<User> notFollowedUsers = userDao.getNotFollowedUsers(currentUserLogin);
        request.setAttribute("followedUsers", followedUsers);
        request.setAttribute("notFollowedUsers", notFollowedUsers);

        request.getRequestDispatcher("/users.jsp").forward(request, response);
    }
}