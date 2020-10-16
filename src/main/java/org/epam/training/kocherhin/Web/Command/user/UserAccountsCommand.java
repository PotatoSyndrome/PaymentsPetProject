package org.epam.training.kocherhin.Web.Command.user;

import org.epam.training.kocherhin.DAO.AccountDAO;
import org.epam.training.kocherhin.Entity.User;
import org.epam.training.kocherhin.Web.Command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class UserAccountsCommand extends Command {
    @Override
    public String processGet(HttpServletRequest request, HttpServletResponse response) {

        if(request.getParameter("accountsSortBy") != null) {
            request.getSession().setAttribute("accountsSortBy", request.getParameter("accountsSortBy"));
        }
        if (request.getParameter("ascAcc") != null) {
            request.getSession().setAttribute("ascAcc", request.getParameter("ascAcc"));
        }
        if(request.getSession().getAttribute("accountsSortBy") == null) {
            request.getSession().setAttribute("accountsSortBy", "id");
        }
        if(request.getSession().getAttribute("ascAcc") == null) {
            request.getSession().setAttribute("ascAcc", "true");
        }

        int page = 1;
        int accountsPerPage = 5;
        int numberOfRecords = 0;
        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }
        try {
            request.getSession().setAttribute("accountList", new AccountDAO().getByUserWithSort(
                    (User)request.getSession().getAttribute("user"),
                    request.getSession().getAttribute("accountsSortBy").toString(),
                    Boolean.parseBoolean((String) request.getSession().getAttribute("ascAcc")),
                    page, accountsPerPage));
            numberOfRecords = new AccountDAO().getNumberOfAccounts((User)request.getSession().getAttribute("user"));
        } catch (SQLException e) {
            e.printStackTrace();
            request.getSession().setAttribute("message", "Oops! Something went wrong (can`t get user`s accounts)");
            return "message.jsp";
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
        if(request.getParameter("accountsSortBy") != null) {
            request.getSession().setAttribute("accountsSortBy", request.getParameter("accountsSortBy"));
        }
        if (request.getParameter("ascAcc") != null) {
            request.getSession().setAttribute("ascAcc", request.getParameter("ascAcc"));
        }

        return "controller?command=usersAccounts";
    }
}
