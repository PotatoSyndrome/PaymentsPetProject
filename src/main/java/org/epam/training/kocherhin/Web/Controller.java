package org.epam.training.kocherhin.Web;

import org.epam.training.kocherhin.Web.Command.Command;
import org.epam.training.kocherhin.Web.Command.CommandContainer;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Controller extends HttpServlet {

    private CommandContainer commands;

    @Override
    public void init() throws ServletException {
        commands = new CommandContainer();
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String commandName = request.getParameter("command");

        Command command = commands.getCommand(commandName);
        String forward;
        if (command != null) {
            forward = command.processGet(request, response);
        } else {
            forward = "error.html"; // TODO forward to error page
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher(forward);
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String commandName = request.getParameter("command");

        Command command = commands.getCommand(commandName);

        String forward;
        if (command != null) {
            forward = command.processPost(request, response);
        } else {
            forward = "error.html"; // TODO forward to error page
        }

        response.sendRedirect(forward);
    }
}
