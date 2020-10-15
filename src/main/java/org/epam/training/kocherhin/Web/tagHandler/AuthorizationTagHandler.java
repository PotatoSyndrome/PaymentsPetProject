package org.epam.training.kocherhin.Web.tagHandler;

import org.epam.training.kocherhin.DAO.UserDAO;
import org.epam.training.kocherhin.Entity.UnblockRequest;
import org.epam.training.kocherhin.Entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.sql.SQLException;

public class AuthorizationTagHandler extends TagSupport {

    @Override
    public int doStartTag() throws JspException {
        System.out.println("HANDLER WORKS");
        if (pageContext.getSession().getAttribute("user") == null) {
            try {
                pageContext.getSession().setAttribute("user", new UserDAO().
                        getByCookies(((HttpServletRequest)pageContext.getRequest()).getCookies()));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return SKIP_BODY;
    }
}
