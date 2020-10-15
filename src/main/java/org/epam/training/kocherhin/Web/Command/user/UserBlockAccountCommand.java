package org.epam.training.kocherhin.Web.Command.user;

import org.epam.training.kocherhin.DAO.AccountDAO;
import org.epam.training.kocherhin.Web.Command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class UserBlockAccountCommand extends Command {
    @Override
    public String processGet(HttpServletRequest request, HttpServletResponse response) {
        return null;
    }

    @Override
    public String processPost(HttpServletRequest request, HttpServletResponse response) {
        Long id = Long.parseLong(request.getParameter("blockedAccountId"));

        try {
            new AccountDAO().blockAccount(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return "controller?command=usersAccounts";
    }
}
