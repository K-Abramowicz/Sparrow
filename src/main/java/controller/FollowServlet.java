package controller;

import controller.utils.ServletsUtils;
import dao.UserDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;



@WebServlet(name = "followServlet", value = "/follow")
public class FollowServlet extends HttpServlet {
    private UserDAO userDAO = new UserDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String currentUserLogin = ServletsUtils.getUserLoginFromSession(req);
        String userLoginToFollow = req.getParameter("userLoginToFollow");
        userDAO.follow(currentUserLogin, userLoginToFollow);
        req.getRequestDispatcher("users").forward(req,resp);
    }
}
