package org.epam.training.kocherhin.Web.Command.user;

import org.epam.training.kocherhin.DAO.PaymentDAO;
import org.epam.training.kocherhin.Entity.User;
import org.epam.training.kocherhin.Web.Command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class UsersPaymentsCommand extends Command {
    @Override
    public String processGet(HttpServletRequest request, HttpServletResponse response) {
        int page = 1;
        int accountsPerPage = 2;
        int numberOfRecords = 0;
        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }
        try {
            request.getSession().setAttribute("paymentsList", new PaymentDAO().getByFromUserWithPagination(
                    (User)request.getSession().getAttribute("user"),  page, accountsPerPage));
            numberOfRecords = new PaymentDAO().getNumberOfPayments((User)request.getSession().getAttribute("user"));
        } catch (SQLException e) {
            e.printStackTrace();
            request.getSession().setAttribute("message", "Oops! Something went wrong (can`t get payments list)");
            return "message.jsp";
        }
        int numberOfPages = (numberOfRecords + accountsPerPage) / accountsPerPage;
        request.getSession().setAttribute("numberOfRecords", numberOfRecords);
        request.getSession().setAttribute("numberOfPages", numberOfPages);
        request.getSession().setAttribute("page", page);
        request.getSession().setAttribute("currentCommand", "usersPayments");
        return "/WEB-INF/jsp/usersPayments.jsp"; //todo
    }

    @Override
    public String processPost(HttpServletRequest request, HttpServletResponse response) {
        return processGet(request, response);
    }
}
