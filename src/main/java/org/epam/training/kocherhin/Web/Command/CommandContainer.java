package org.epam.training.kocherhin.Web.Command;

import java.util.HashMap;
import java.util.Map;

public class CommandContainer {

    Map<String, Command> commands;

    public CommandContainer() {
        commands = new HashMap<>();

        commands.put("add-user", new AddUserCommand());
        commands.put("sign-in", new SignInCommand());
        commands.put("exit", new ExitCommand());
        // TODO put commands
    }

    public Command getCommand(String name) {
        return commands.get(name);
    }
}
