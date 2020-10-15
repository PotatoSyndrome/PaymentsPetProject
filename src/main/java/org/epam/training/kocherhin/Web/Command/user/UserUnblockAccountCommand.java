package org.epam.training.kocherhin.Web.Command.user;


import org.epam.training.kocherhin.DAO.UnblockRequestDAO;
import org.epam.training.kocherhin.Web.Command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;


public class UserUnblockAccountCommand extends Command {
    @Override
    public String processGet(HttpServletRequest request, HttpServletResponse response) {
        return "/WEB-INF/jsp/usersAccounts.jsp";
    }

    @Override
    public String processPost(HttpServletRequest request, HttpServletResponse response) {

        long accountId = Long.parseLong(request.getParameter("blockedAccountId"));

        try {
            new UnblockRequestDAO().addRequest(accountId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.getSession().setAttribute("message", "You successfully requested unblock of this account");
        return "message.jsp";
    }
}
