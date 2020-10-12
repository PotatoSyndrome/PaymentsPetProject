package org.epam.training.kocherhin.Web.Command;

import org.epam.training.kocherhin.DAO.AccountDAO;
import org.epam.training.kocherhin.Entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class UserAccountsCommand extends Command {
    @Override
    public String processGet(HttpServletRequest request, HttpServletResponse response) {

        int page = 1;
        int accountsPerPage = 2;
        int numberOfRecords = 0;
        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }
        try {
            request.getSession().setAttribute("accountList", new AccountDAO().getByUserWithPagination(
                    (User)request.getSession().getAttribute("user"), page, accountsPerPage));
            numberOfRecords = new AccountDAO().getNumberOfAccounts((User)request.getSession().getAttribute("user"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        int numberOfPages = (numberOfRecords + accountsPerPage) / accountsPerPage;
        request.getSession().setAttribute("numberOfRecords", numberOfRecords);
        request.getSession().setAttribute("numberOfPages", numberOfPages);
        request.getSession().setAttribute("page", page);
        request.getSession().setAttribute("currentCommand", "usersAccounts");
        return "/WEB-INF/jsp/usersAccounts.jsp";
    }

    @Override
    public String processPost(HttpServletRequest request, HttpServletResponse response) {
        return processGet(request, response);
    }
}