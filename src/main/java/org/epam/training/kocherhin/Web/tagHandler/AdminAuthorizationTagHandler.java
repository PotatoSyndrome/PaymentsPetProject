package org.epam.training.kocherhin.Web.tagHandler;

import org.epam.training.kocherhin.DAO.AdminDAO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.sql.SQLException;

public class AdminAuthorizationTagHandler extends TagSupport {

    @Override
    public int doStartTag() throws JspException {

        if (pageContext.getSession().getAttribute("admin") == null) {
            try {
                pageContext.getSession().setAttribute("admin", new AdminDAO().
                        getByCookies(((HttpServletRequest)pageContext.getRequest()).getCookies()));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return SKIP_BODY;
    }
}
