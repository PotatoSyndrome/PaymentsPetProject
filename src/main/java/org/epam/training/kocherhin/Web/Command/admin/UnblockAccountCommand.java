package org.epam.training.kocherhin.Web.Command.admin;

import org.epam.training.kocherhin.DAO.AccountDAO;
import org.epam.training.kocherhin.Web.Command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class UnblockAccountCommand extends Command {
    @Override
    public String processGet(HttpServletRequest request, HttpServletResponse response) {
        return "controller?command=adminAccounts";
    }

    @Override
    public String processPost(HttpServletRequest request, HttpServletResponse response) {

        long accountId = 0;

        if (request.getParameter("blockedAccountId") != null) {
            accountId = Long.parseLong(request.getParameter("blockedAccountId"));
        }

        try {
            new AccountDAO().unblockAccount(accountId);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return "controller?command=adminAccounts";
    }
}
