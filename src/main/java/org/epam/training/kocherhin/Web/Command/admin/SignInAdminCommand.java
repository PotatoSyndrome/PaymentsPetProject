package org.epam.training.kocherhin.Web.Command.admin;

import org.epam.training.kocherhin.DAO.AdminDAO;
import org.epam.training.kocherhin.Entity.Admin;
import org.epam.training.kocherhin.Web.Command.Command;
import org.epam.training.kocherhin.Web.ValidationUtil;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class SignInAdminCommand extends Command {
    @Override
    public String processGet(HttpServletRequest request, HttpServletResponse response) {
        return "controller?command=admin"; //todo to admin
    }

    @Override
    public String processPost(HttpServletRequest request, HttpServletResponse response) {

        Admin admin = new Admin();
        admin.setLogin(request.getParameter("login"));
        admin.setPassword(request.getParameter("password"));

        try {
            Admin adminDB = new AdminDAO().getAdminByLogin(admin.getLogin());
            if (adminDB == null) {
                return "admin.jsp";
            }
            if (!ValidationUtil.validateAdmin(adminDB, admin.getPassword())) {
                request.getSession().setAttribute("message", "Wrong password");
                return "message.jsp";
            } else admin = adminDB;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        response.addCookie(new Cookie("adlg", admin.getLogin()));
        response.addCookie(new Cookie("adpw", admin.getPassword()));

        request.getSession().setAttribute("admin", admin);

        return "controller?command=admin";
    }
}
