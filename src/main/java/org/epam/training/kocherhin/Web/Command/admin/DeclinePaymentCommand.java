package org.epam.training.kocherhin.Web.Command.admin;

import org.epam.training.kocherhin.DAO.PaymentDAO;
import org.epam.training.kocherhin.Web.Command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class DeclinePaymentCommand extends Command {
    @Override
    public String processGet(HttpServletRequest request, HttpServletResponse response) {
        return "/WEB-INF/jsp/adminPayments.jsp";
    }

    @Override
    public String processPost(HttpServletRequest request, HttpServletResponse response) {

        if (request.getParameter("paymentId") != null) {
            try {
                new PaymentDAO().declinePayment(Long.parseLong(request.getParameter("paymentId")));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return "controller?command=payments";
    }
}
