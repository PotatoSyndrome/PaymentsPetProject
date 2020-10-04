package org.epam.training.kocherhin.Web.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class Command {

    public abstract String processGet(HttpServletRequest request, HttpServletResponse response);

    public abstract String processPost(HttpServletRequest request, HttpServletResponse response);
}
