package org.epam.training.kocherhin.Web.tagHandler;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeFormatTagHandler extends TagSupport {
    private SimpleDateFormat format;
    private Date date;

    @Override
    public int doStartTag() throws JspException {

        try {
            pageContext.getOut().print(format.format(date));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return SKIP_BODY;
    }

    public void setFormat(String format) {
        this.format = new SimpleDateFormat(format);
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
