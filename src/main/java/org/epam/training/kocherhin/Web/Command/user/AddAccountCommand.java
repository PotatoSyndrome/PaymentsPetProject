package org.epam.training.kocherhin.Web.Command.user;

import org.epam.training.kocherhin.DAO.AccountDAO;
import org.epam.training.kocherhin.DAO.UserDAO;
import org.epam.training.kocherhin.Entity.Account;
import org.epam.training.kocherhin.Entity.User;
import org.epam.training.kocherhin.Web.Command.Command;
import org.epam.training.kocherhin.Web.ValidationUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class AddAccountCommand extends Command {
    @Override
    public String processGet(HttpServletRequest request, HttpServletResponse response) {
        return "main.jsp";
    }

    @Override
    public String processPost(HttpServletRequest request, HttpServletResponse response) {

        User user = (User)request.getSession().getAttribute("user");
        Account account = new Account();
        account.setCardNumber(request.getParameter("cardNumber"));
        account.setName(request.getParameter("name"));
        if (ValidationUtil.validateDigits(request.getParameter("amount"))) {
            account.setAmount(Integer.parseInt(request.getParameter("amount")));
        }
        account.setCurrency(Account.Currency.valueOf(request.getParameter("currency")));

        account.setUserId(user.getId());

        if (user.getId() == 0 || /*validation*/
                !ValidationUtil.validateCardNumber(account.getCardNumber()) ||
                account.getCurrency() == null) {
            System.out.println(user.getId());
            System.out.println(account.getCurrency());
            request.getSession().setAttribute("message", "Oops! Something went wrong (validation of " +
                    "new account failed)");
            return "message.jsp";
        }

        try {
            new AccountDAO().insertAccount(account);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return "controller?command=usersAccounts";
    }
}
