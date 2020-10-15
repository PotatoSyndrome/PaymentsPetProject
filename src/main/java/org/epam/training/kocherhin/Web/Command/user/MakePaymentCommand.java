package org.epam.training.kocherhin.Web.Command.user;

import org.epam.training.kocherhin.DAO.AccountDAO;
import org.epam.training.kocherhin.Web.Command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class MakePaymentCommand extends Command {
    @Override
    public String processGet(HttpServletRequest request, HttpServletResponse response) {

        return "/WEB-INF/jsp/makePayment.jsp";
    }

    @Override
    public String processPost(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.getSession().setAttribute("paymentAccount", new AccountDAO().getById(
                    Long.parseLong(request.getParameter("accountId"))));
            request.getSession().setAttribute("toCardValue", request.getParameter("toCardValue"));
            request.getSession().setAttribute("amountValue", request.getParameter("amountValue"));
        } catch (SQLException e) {
            e.printStackTrace();
            request.getSession().setAttribute("message", "Oops! Something went wrong (can`t get account)");
            return "message.jsp";
        }



        return "controller?command=make-payment";
    }
}
