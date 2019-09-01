package filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;


import static controller.utils.ServletsUtils.LOGIN;


@WebFilter(filterName = "loggedInFilter", servletNames = {"usersServlet", "followServlet","unfollowServlet"})
public class LoggedInFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpSession session = request.getSession(false);
        if (null != session){
            String  login = (String)request.getSession().getAttribute(LOGIN);
            if(null != login && !login.isEmpty()){
                filterChain.doFilter(request, servletResponse);
            }
        }


    }

    @Override
    public void destroy() {

    }
}
