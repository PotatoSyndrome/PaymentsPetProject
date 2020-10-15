package org.epam.training.kocherhin.Web.Command.admin;

import org.epam.training.kocherhin.DAO.AccountDAO;
import org.epam.training.kocherhin.DAO.UnblockRequestDAO;
import org.epam.training.kocherhin.Entity.Admin;
import org.epam.training.kocherhin.Web.Command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class UnlockRequestCommand extends Command {
    @Override
    public String processGet(HttpServletRequest request, HttpServletResponse response) {
        return "controller?command=requests";
    }

    @Override
    public String processPost(HttpServletRequest request, HttpServletResponse response) {

        Long id = Long.parseLong(request.getParameter("accountId"));

        Admin admin = (Admin) request.getSession().getAttribute("admin");
        if (admin != null) {
            try {
                new UnblockRequestDAO().considerRequest(admin.getId(), id);
                new AccountDAO().unblockAccount(id);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return "controller?command=requests";
    }
}
