package org.epam.training.kocherhin.Web.Command.user;

import org.epam.training.kocherhin.Entity.Account;
import org.epam.training.kocherhin.Web.Command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetAddAccountPageCommand extends Command {
    @Override
    public String processGet(HttpServletRequest request, HttpServletResponse response) {

        request.getSession().setAttribute("currencies", Account.Currency.values());

        return "/WEB-INF/jsp/addAccount.jsp";
    }

    @Override
    public String processPost(HttpServletRequest request, HttpServletResponse response) {
        return processGet(request, response);
    }
}
