import dao.UserDAO;
import java.util.List;
import java.util.Set;
import model.User;

public class Main {

    public static void main(String[] args) {
        UserDAO userDAO = new UserDAO();

        User cat = new User();
        cat.setName("Coco");
        cat.setLogin("cat2019");
        cat.setPassword("dog");

        userDAO.saveUser(cat);

        User mouse = new User();
        mouse.setName("Stew");
        mouse.setLogin("mouse2019");
        mouse.setPassword("mom");

        userDAO.saveUser(mouse);

        User dog = new User();
        dog.setName("BoKo");
        dog.setLogin("dog2019");
        dog.setPassword("cat");

        userDAO.saveUser(dog);

        List<User> followedUsers = userDAO.getFollowedUsers(dog.getLogin());
        followedUsers.stream().forEach(user -> System.out.println(user));

        List<User> notFollowedUsers = userDAO.getNotFollowedUsers(dog.getLogin());
        notFollowedUsers.stream().forEach(user -> System.out.println(user));

        Set<User> followed = userDAO.getUserByLogin(cat.getLogin()).getFollowed();
        followed.stream().forEach(f -> System.out.println(f));
        User coco = userDAO.getUserByLogin("cat2019");
        coco.setName("Kokos");
        userDAO.saveUser(coco);

        userDAO.follow(coco.getLogin(), dog.getLogin());

        System.out.println("_------------------------------------------------");

        List<User> followedUsers1 = userDAO.getFollowedUsers(coco.getLogin());
        followedUsers1.stream().forEach(u -> System.out.println(u));


        userDAO.stopFollowing(coco.getLogin(), dog.getLogin());

    }

}