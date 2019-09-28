package dao;

import model.User;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

public class UserDAO extends AbstractDAO {

    public List<User> list() {
        return entityManager.createQuery("select u from User u").getResultList();
    }

    public void saveUser(User user) {
        hibernateUtil.save(user);
    }

    public void deleteUser(long userId) {
        hibernateUtil.delete(User.class, userId);
    }

    public List<User> getUserByName(String name) {
        Query query = entityManager.createQuery("select u from User u where name = :name");
        return query.setParameter("name", name).getResultList();
    }

    public User getUserByEmail(String email) {
        TypedQuery<User> query = entityManager.createQuery("select u from User u where email = :email", User.class);
        return query.setParameter("email", email).getSingleResult();
    }

    public User getUserByLogin(String login) {
        TypedQuery<User> query = entityManager.createQuery("select u from User u where login = :login", User.class);
        return query.setParameter("login", login).getSingleResult();
    }

    public boolean isUserExist(String login, String password) {
        Query query = entityManager.createQuery("select count(*) as cnt from User u where u.login = :login and u.password = :password");
        query.setParameter("login", login);
        query.setParameter("password", password);
        Object singleResult = query.getSingleResult();
        return ((Long) singleResult > 0) ? true : false;
    }

    public List<User> getFollowedUsers(String followerLogin) {
        User user = getUserByLogin(followerLogin);
        Long userId = user.getId();
        Query query = entityManager.createQuery("select distinct follows from User u where u.id = :userId");
        return query.setParameter("userId", userId).getResultList();
    }

    public List<User> getNotFollowedUsers(String followerLogin) {
        Query query = entityManager.createQuery("select u from User u where u.login != :followerLogin");
        List users = query.setParameter("followerLogin", followerLogin).getResultList();
        List<User> followed = getFollowedUsers(followerLogin);
        users.removeAll(followed);
        return users;
    }

    public void follow(String userLogin, String userLoginToFollow) {
        User currentUser = getUserByLogin(userLogin);
        User userToFollow = getUserByLogin(userLoginToFollow);
        currentUser.getFollows().add(userToFollow);
        saveUser(currentUser);
    }

    public void stopFollowing(String userLogin, String userLoginToStopFollow) {
        User currentUser = getUserByLogin(userLogin);
        User userToStopFollow = getUserByLogin(userLoginToStopFollow);
        currentUser.getFollows().remove(userToStopFollow);
        saveUser(currentUser);
    }






}