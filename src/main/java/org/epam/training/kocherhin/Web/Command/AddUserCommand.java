package org.epam.training.kocherhin.Web.Command;

import org.epam.training.kocherhin.DAO.UserDAO;
import org.epam.training.kocherhin.Entity.User;
import org.epam.training.kocherhin.Web.ValidationUtil;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class AddUserCommand extends Command {
    @Override
    public String processGet(HttpServletRequest request, HttpServletResponse response) {
        return "bt.jsp";
    } //todo

    @Override
    public String processPost(HttpServletRequest request, HttpServletResponse response) {

        User user = new User();

        user.setLogin(request.getParameter("email"));
        user.setPassword(request.getParameter("password"));
        user.setBlocked(Boolean.parseBoolean(request.getParameter("blocked")));

        if (!ValidationUtil.validateEmail(user.getLogin()) ||
                !ValidationUtil.validatePassword(user.getPassword())) {
            return "error.html"; //todo
        }

        try {
            new UserDAO().addUser(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        response.addCookie(new Cookie("lg", user.getLogin())); //todo change cookie value to smth safer
        response.addCookie(new Cookie("pw", user.getPassword()));
        return  "HWPut.html"; //todo add actual return
    }
}
