package dao;

import java.util.Date;
import java.util.List;
import java.util.Set;
import javax.persistence.Query;
import model.Tweet;
import model.User;

public class TweetDAO extends AbstractDAO {
    UserDAO userDAO = new UserDAO();

    public void addTweet(String userLogin, String message) {
        User user = userDAO.getUserByLogin(userLogin);
        Tweet tweet = new Tweet();
        tweet.setAuthor(user);
        tweet.setMessage(message);
        tweet.setPublishedAt(new Date());
        hibernateUtil.save(tweet);
    }

    public void deleteTweet(long tweetId) {
        hibernateUtil.delete(Tweet.class, tweetId);
    }

    public List<Tweet> getFollowedTweets(String userLogin) {
        User userByLogin = userDAO.getUserByLogin(userLogin);
        Long userId = userByLogin.getId();
        Set<User> follows = userByLogin.getFollows();
        follows.add(userByLogin);

        Query query = entityManager.createQuery("select t from Tweet t where t.author in :follows");
        return query.setParameter("follows", follows).getResultList();
    }

}