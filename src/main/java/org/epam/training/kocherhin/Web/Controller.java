package org.epam.training.kocherhin.Web;

import org.epam.training.kocherhin.Web.Command.Command;
import org.epam.training.kocherhin.Web.Command.CommandContainer;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

public class Controller extends HttpServlet {

    private CommandContainer commands;

    private static Logger logger = Logger.getLogger(Controller.class.getName());

    @Override
    public void init() throws ServletException {
        commands = new CommandContainer();
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String commandName = request.getParameter("command");
        System.out.println(commandName);
        Command command = commands.getCommand(commandName);
        String forward;
        if (command != null) {
            forward = command.processGet(request, response);
        } else {
            request.getSession().setAttribute("message", "Oops! Can`t find such command!"
                    + request.getParameter("command"));
            forward = "message.jsp";
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher(forward);
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String commandName = request.getParameter("command");

        System.out.println(commandName);
        Command command = commands.getCommand(commandName);

        String forward;
        if (command != null) {
            forward = command.processPost(request, response);
        } else {
            request.getSession().setAttribute("message", "Oops! Can`t find such command!"
                    + request.getParameter("command"));
            forward = "message.jsp";
        }
        response.sendRedirect(forward);
    }
}
