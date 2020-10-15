package org.epam.training.kocherhin.Web.Command.user;

import org.epam.training.kocherhin.DAO.AccountDAO;
import org.epam.training.kocherhin.DAO.PaymentDAO;
import org.epam.training.kocherhin.Entity.Account;
import org.epam.training.kocherhin.Entity.Payment;
import org.epam.training.kocherhin.Web.Command.Command;
import org.epam.training.kocherhin.Web.ValidationUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class ConfirmPaymentCommand extends Command {
    @Override
    public String processGet(HttpServletRequest request, HttpServletResponse response) {
        return "main.jsp";
    }

    @Override
    public String processPost(HttpServletRequest request, HttpServletResponse response) {

        Account paymentAccount = (Account) request.getSession().getAttribute("paymentAccount");

        Payment payment = new Payment();
        payment.setFrom(paymentAccount.getId());

        String cardNumber;
        int amount;

        if (ValidationUtil.validateCardNumber(request.getParameter("toCard"))) {
            cardNumber = request.getParameter("toCard");
        } else {
            request.getSession().setAttribute("message", "Oops! Something went wrong (Illegal card number " +
                    "value)");
            return "message.jsp";
        }

        if (ValidationUtil.validateDigits(request.getParameter("amount"))) {
            amount = Integer.parseInt(request.getParameter("amount"));
        } else {
            request.getSession().setAttribute("message", "Oops! Something went wrong (Illegal amount " +
                    "value)");
            return "message.jsp";
        }

        if (ValidationUtil.hasEnoughMoney(paymentAccount, amount)) {
            try {
                payment.setTo(new AccountDAO().getByCardNumber(cardNumber).getId());
                payment.setAmount(amount);
                new PaymentDAO().insertPayment(payment);
            } catch (SQLException e) {
                request.getSession().setAttribute("message", "Oops! Something went wrong (can`t connect " +
                        "to database)");
                return "message.jsp";
            }
        } else {
            request.getSession().setAttribute("message", "Oops! Something went wrong (you don`t have " +
                    "enough money)");
            return "message.jsp";
        }



        return "controller?command=usersPayments";
    }
}
