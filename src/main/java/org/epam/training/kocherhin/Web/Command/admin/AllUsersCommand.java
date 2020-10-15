package org.epam.training.kocherhin.Web.Command.admin;

import org.epam.training.kocherhin.DAO.UnblockRequestDAO;
import org.epam.training.kocherhin.DAO.UserDAO;
import org.epam.training.kocherhin.Web.Command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class AllUsersCommand extends Command {
    @Override
    public String processGet(HttpServletRequest request, HttpServletResponse response) {

        int page = 1;
        int recordsPerPage = 8;
        int numberOfRecords = 0;
        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }
        try {
            request.getSession().setAttribute("usersList", new UserDAO().getAllWithPagination(page, recordsPerPage));//TODO
        } catch (SQLException e) {
            e.printStackTrace();
            request.getSession().setAttribute("message", "Oops! Something went wrong (can`t get users list)");
            return "message.jsp";
        }
        int numberOfPages = (numberOfRecords + recordsPerPage - 1) / recordsPerPage;
        request.getSession().setAttribute("numberOfRecords", numberOfRecords);
        request.getSession().setAttribute("numberOfPages", numberOfPages);
        request.getSession().setAttribute("page", page);
        request.getSession().setAttribute("currentCommand", "users");

        return "/WEB-INF/jsp/users.jsp";
    }

    @Override
    public String processPost(HttpServletRequest request, HttpServletResponse response) {
        return "controller?command=users";
    }
}
