package org.epam.training.kocherhin.Web.Command;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ExitCommand extends Command {

    @Override
    public String processGet(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().setAttribute("user", null);
        for (Cookie cookie: request.getCookies()) {
            if (cookie.getName().equals("lg") || cookie.getName().equals("pw")) {
                cookie.setValue("");
                cookie.setPath("/");
                cookie.setMaxAge(0);
                response.addCookie(cookie);
            }
        }
        return "bt.jsp";
    }

    @Override
    public String processPost(HttpServletRequest request, HttpServletResponse response) {
        return processGet(request, response);
    }
}
