package org.epam.training.kocherhin.Web.Command.user;

import org.epam.training.kocherhin.DAO.AccountDAO;
import org.epam.training.kocherhin.DAO.PaymentDAO;
import org.epam.training.kocherhin.DAO.TemplateDAO;
import org.epam.training.kocherhin.Entity.Template;
import org.epam.training.kocherhin.Web.Command.Command;
import org.epam.training.kocherhin.Web.ValidationUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class ConfirmTemplateCommand extends Command {
    @Override
    public String processGet(HttpServletRequest request, HttpServletResponse response) {
        return "/WEB-INF/jsp/usersTemplates.jsp";
    }

    @Override
    public String processPost(HttpServletRequest request, HttpServletResponse response) {

        Template template = new Template();
        template.setFrom(Long.parseLong(request.getParameter("fromAccount")));

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

        try {
            template.setTo(new AccountDAO().getByCardNumber(cardNumber).getId());
            template.setAmount(amount);
            new TemplateDAO().insertTemplate(template);
        } catch (SQLException e) {
            request.getSession().setAttribute("message", "Oops! Something went wrong (can`t insert " +
                    "template)");
            return "message.jsp";
        }

        return "controller?command=usersTemplates";
    }
}
