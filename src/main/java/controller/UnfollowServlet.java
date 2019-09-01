package controller;

import dao.UserDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static controller.HomeServlet.LOGIN;

@WebServlet(name = "unfollowServlet", value = "/unfollow")
public class UnfollowServlet extends HttpServlet {
    UserDAO userDAO = new UserDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String currentLogin = (String)req.getSession().getAttribute(LOGIN);
        String userLoginToUnfollow = req.getParameter("userLoginToUnfollow");
        userDAO.stopFollowing(currentLogin, userLoginToUnfollow);
        req.getRequestDispatcher("users").forward(req,resp);
    }
}
