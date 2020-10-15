package org.epam.training.kocherhin.Web.Command.user;

import org.epam.training.kocherhin.DAO.UserDAO;
import org.epam.training.kocherhin.Entity.User;
import org.epam.training.kocherhin.Web.Command.Command;
import org.epam.training.kocherhin.Web.ValidationUtil;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class SignInCommand extends Command {
    @Override
    public String processGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setAttribute("user", new UserDAO().getByCookies(request.getCookies()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "main.jsp"; //todo
    }

    @Override
    public String processPost(HttpServletRequest request, HttpServletResponse response) {

        User user = new User();
        user.setLogin(request.getParameter("email"));
        user.setPassword(request.getParameter("password"));

        try {
            User dbUser = new UserDAO().getByName(user.getLogin());
            if (dbUser == null) {
                return "main.jsp"; //todo
            }
            if (!ValidationUtil.validateUser(dbUser, user.getPassword())) {
                System.out.println("NOT VALIDATED");
                System.out.println("EXPECTED" + dbUser.getPassword());
                System.out.println("BUT WAS" + user.getPassword());
                request.getSession().setAttribute("message", "Oops! Something went wrong (validation of " +
                        "user failed)");
                return "message.jsp"; //todo
            } else {
                user = dbUser;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        response.addCookie(new Cookie("lg", user.getLogin())); //todo safe
        response.addCookie(new Cookie("pw", user.getPassword()));

        request.getSession().setAttribute("user", user);
        return "main.jsp"; //todo
    }
}
