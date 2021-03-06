package org.epam.training.kocherhin.Web.Command.admin;

import org.epam.training.kocherhin.DAO.UserDAO;
import org.epam.training.kocherhin.Web.Command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class UnblockUserCommand extends Command {
    @Override
    public String processGet(HttpServletRequest request, HttpServletResponse response) {
        return "controller?command=users";
    }

    @Override
    public String processPost(HttpServletRequest request, HttpServletResponse response) {

        long id = 0L;
        if (request.getParameter("blockedUserId") != null) {
            id = Long.parseLong(request.getParameter("blockedUserId"));
        }
        try {
            new UserDAO().unblockUser(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return "controller?command=users";
    }
}
