package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "logoutServlet", urlPatterns = {"/logout"})
public class LogoutServlet extends HttpServlet {
    private static final String COOKIE_LOGIN = "sparrow_login";
    private static final String COOKIE_PASSWORD = "sparrow_password";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().invalidate();
        for(Cookie cookie : req.getCookies()) {
            if(cookie.getName().equals(COOKIE_LOGIN) || cookie.getName().equals(COOKIE_PASSWORD)) {
                cookie.setMaxAge(0);
                resp.addCookie(cookie);
            }
        }
        req.getRequestDispatcher("/login.jsp").forward(req, resp);
    }
}