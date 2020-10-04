package org.epam.training.kocherhin.Web.Command;

import org.epam.training.kocherhin.DAO.UserDAO;
import org.epam.training.kocherhin.Entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class AddUserCommand extends Command {
    @Override
    public String processGet(HttpServletRequest request, HttpServletResponse response) {
        return "bt.html";
    }

    @Override
    public String processPost(HttpServletRequest request, HttpServletResponse response) {

        User user = new User();

        user.setLogin(request.getParameter("email"));
        user.setPassword(request.getParameter("password"));
        user.setBlocked(Boolean.parseBoolean(request.getParameter("blocked")));

        try {
            new UserDAO().addUser(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return  "HWPut.html"; //todo add actual return
    }
}