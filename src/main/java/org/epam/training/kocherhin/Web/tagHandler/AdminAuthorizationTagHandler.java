package org.epam.training.kocherhin.Web.tagHandler;

import org.apache.log4j.Logger;
import org.epam.training.kocherhin.DAO.AdminDAO;
import org.epam.training.kocherhin.Web.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.sql.SQLException;

public class AdminAuthorizationTagHandler extends TagSupport {

    private static Logger logger = Logger.getLogger(AdminAuthorizationTagHandler.class);

    @Override
    public int doStartTag() throws JspException {
        logger.debug("Admin authorization");
        if (pageContext.getSession().getAttribute("admin") == null) {
            try {
                pageContext.getSession().setAttribute("admin", new AdminDAO().
                        getByCookies(((HttpServletRequest)pageContext.getRequest()).getCookies()));
            } catch (SQLException e) {
                logger.trace("SQLException in Admin Authorization", e);
            }
        }
        logger.trace("admin is " + pageContext.getSession().getAttribute("admin"));
        return SKIP_BODY;
    }
}
