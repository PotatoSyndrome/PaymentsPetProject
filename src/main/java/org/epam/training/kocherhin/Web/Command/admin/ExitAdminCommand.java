package org.epam.training.kocherhin.Web.Command.admin;

import org.epam.training.kocherhin.Web.Command.Command;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ExitAdminCommand extends Command {
    @Override
    public String processGet(HttpServletRequest request, HttpServletResponse response) {

        request.getSession().setAttribute("admin", null);
        for (Cookie cookie: request.getCookies()) {
            if (cookie.getName().equals("adlg") || cookie.getName().equals("adpw")) {
                cookie.setValue("");
                cookie.setPath("/");
                cookie.setMaxAge(0);
                response.addCookie(cookie);
            }
        }
        return "controller?command=admin";
    }

    @Override
    public String processPost(HttpServletRequest request, HttpServletResponse response) {



        return "controller?command=admin";
    }
}
