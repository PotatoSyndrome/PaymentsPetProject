package org.epam.training.kocherhin.Web.Command.user;

import org.epam.training.kocherhin.DAO.TemplateDAO;
import org.epam.training.kocherhin.Entity.User;
import org.epam.training.kocherhin.Web.Command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class UsersTemplatesCommand extends Command {
    @Override
    public String processGet(HttpServletRequest request, HttpServletResponse response) {
        int page = 1;
        int accountsPerPage = 2;
        int numberOfRecords = 0;
        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }
        try {
            request.getSession().setAttribute("templateList", new TemplateDAO().getTemplatesForUserWithPagination(
                    (User)request.getSession().getAttribute("user"),  page, accountsPerPage));
            numberOfRecords = new TemplateDAO().getNumberOfTemplates((User)request.getSession().getAttribute("user"));
        } catch (SQLException e) {
            e.printStackTrace();
            request.getSession().setAttribute("message", "Oops! Something went wrong (can`t get user`s templates)");
            return "message.jsp";
        }
        int numberOfPages = (numberOfRecords + accountsPerPage) / accountsPerPage;
        request.getSession().setAttribute("numberOfRecords", numberOfRecords);
        request.getSession().setAttribute("numberOfPages", numberOfPages);
        request.getSession().setAttribute("page", page);
        request.getSession().setAttribute("currentCommand", "usersTemplates");
        return "/WEB-INF/jsp/usersTemplates.jsp";
    }

    @Override
    public String processPost(HttpServletRequest request, HttpServletResponse response) {
        return processGet(request, response);
    }
}
