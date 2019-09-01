package controller;

import controller.utils.ServletsUtils;
import dao.TweetDAO;
import model.Tweet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet (name = "messageServlet", value = "/messages")
public class MessagesServlet extends HttpServlet {
    TweetDAO tweetDAO = new TweetDAO();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String currentUserLogin = ServletsUtils.getUserLoginFromSession(req);
        List<Tweet> followersTweet = tweetDAO.getFollowedTweets(currentUserLogin);
        req.setAttribute("tweets", followersTweet);
        req.getRequestDispatcher("/messages.jsp").forward(req, resp);
    }
}
