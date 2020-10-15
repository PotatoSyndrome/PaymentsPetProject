package org.epam.training.kocherhin.Web.Command.admin;

import org.epam.training.kocherhin.DAO.AccountDAO;
import org.epam.training.kocherhin.DAO.UserDAO;
import org.epam.training.kocherhin.Web.Command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class AdminAccountsCommand extends Command {
    @Override
    public String processGet(HttpServletRequest request, HttpServletResponse response) {

        int page = 1;
        int recordsPerPage = 8;
        int numberOfRecords = 0;
        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }
        try {
            long userId = 0;
            if (request.getSession().getAttribute("userAccountsId") != null) {
                userId = (Long) request.getSession().getAttribute("userAccountsId");
            }
            request.getSession().setAttribute("accountsList", new AccountDAO().getByUserIdWithPagination(userId, page, recordsPerPage));
        } catch (SQLException e) {
            e.printStackTrace();
            request.getSession().setAttribute("message", "Oops! Something went wrong (can`t get accounts list)");
            return "message.jsp";
        }
        int numberOfPages = (numberOfRecords + recordsPerPage - 1) / recordsPerPage;
        request.getSession().setAttribute("numberOfRecords", numberOfRecords);
        request.getSession().setAttribute("numberOfPages", numberOfPages);
        request.getSession().setAttribute("page", page);
        request.getSession().setAttribute("currentCommand", "adminAccounts");

        return "/WEB-INF/jsp/adminAccounts.jsp";
    }

    @Override
    public String processPost(HttpServletRequest request, HttpServletResponse response) {

        long userId = 0;
        if (request.getParameter("userAccountsId") != null) {
            userId = Long.parseLong(request.getParameter("userAccountsId"));
            request.getSession().setAttribute("userAccountsId", userId);
        }

        return "controller?command=adminAccounts";
    }
}
