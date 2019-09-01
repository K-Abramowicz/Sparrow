package services;

import dao.UserDAO;
import javax.persistence.NoResultException;
import model.User;

public class UserService {
    public static final String EMAIL_ERROR = "emailError";
    public static final String LOGIN_ERROR = "loginError";
    public static final String SUCCESS = "success";
    public static final String EMAIL_AND_LOGIN_ERROR = "emailAndLoginError";

    UserDAO userDAO;

    public UserService() {
        userDAO = new UserDAO();
    }

    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public String registerUser(User user){
        if(isUserEmailAlreadyExist(user.getEmail())&&isUserLoginAlreadyExist(user.getLogin())){
            return EMAIL_AND_LOGIN_ERROR;
        }
        if(isUserEmailAlreadyExist(user.getEmail())) {
            return EMAIL_ERROR;
        } else if (isUserLoginAlreadyExist(user.getLogin())){
            return LOGIN_ERROR;
        }
        userDAO.saveUser(user);
        return SUCCESS;
    }

    private boolean isUserLoginAlreadyExist(String login) {
        try {
            userDAO.getUserByLogin(login);
            return true;
        } catch (NoResultException e) {
            return false;
        }
    }

    private boolean isUserEmailAlreadyExist(String email) {
        try {
            userDAO.getUserByEmail(email);
            return true;
        } catch (NoResultException e) {
            return false;
        }
    }
}