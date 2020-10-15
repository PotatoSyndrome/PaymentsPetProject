package org.epam.training.kocherhin.Web.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ChangeLocaleCommand extends Command {
    @Override
    public String processGet(HttpServletRequest request, HttpServletResponse response) {
        return processPost(request, response);
    }

    @Override
    public String processPost(HttpServletRequest request, HttpServletResponse response) {
        String locale = request.getParameter("newLocale");
        System.out.println(locale);
        request.getSession().setAttribute("javax.servlet.jsp.jstl.fmt.locale.session", locale);
        return "main.jsp";
    }
}
