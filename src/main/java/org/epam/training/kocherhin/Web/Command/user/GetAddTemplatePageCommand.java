package org.epam.training.kocherhin.Web.Command.user;

import org.epam.training.kocherhin.DAO.AccountDAO;
import org.epam.training.kocherhin.Entity.User;
import org.epam.training.kocherhin.Web.Command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class GetAddTemplatePageCommand extends Command {
    @Override
    public String processGet(HttpServletRequest request, HttpServletResponse response) {

        try {
            request.getSession().setAttribute("accounts", new AccountDAO().getByUser((User) request.getSession().getAttribute("user")));
        } catch (SQLException e) {
            request.getSession().setAttribute("message", "Oops! Something went wrong (can`t get user`s accounts)");
            return "message.jsp";
        }

        return "/WEB-INF/jsp/addTemplate.jsp";
    }

    @Override
    public String processPost(HttpServletRequest request, HttpServletResponse response) {
        return processGet(request, response);
    }
}
