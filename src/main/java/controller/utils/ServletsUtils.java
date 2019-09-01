package controller.utils;

import javax.servlet.http.HttpServletRequest;

public class ServletsUtils {
    public static final String LOGIN = "login";

    public static String getUserLoginFromSession (HttpServletRequest req) {
        return (String)req.getSession().getAttribute(LOGIN);
    }



}
