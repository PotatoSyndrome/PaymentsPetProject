package org.epam.training.kocherhin.Web.Command;

import org.epam.training.kocherhin.Web.Command.admin.*;
import org.epam.training.kocherhin.Web.Command.user.*;

import java.util.HashMap;
import java.util.Map;

public class CommandContainer {

    Map<String, Command> commands;

    public CommandContainer() {
        commands = new HashMap<>();

        commands.put("add-user", new AddUserCommand());
        commands.put("sign-in", new SignInCommand());
        commands.put("exit", new ExitCommand());
        commands.put("usersAccounts", new UserAccountsCommand());
        commands.put("usersPayments", new UsersPaymentsCommand());
        commands.put("usersTemplates", new UsersTemplatesCommand());
        commands.put("insert-account", new AddAccountCommand());
        commands.put("get-insert-account", new GetAddAccountPageCommand());
        commands.put("make-payment", new MakePaymentCommand());
        commands.put("confirm-payment", new ConfirmPaymentCommand());
        commands.put("get-insert-template", new GetAddTemplatePageCommand());
        commands.put("confirm-template", new ConfirmTemplateCommand());
        commands.put("change-locale", new ChangeLocaleCommand());
        commands.put("user-block-account", new UserBlockAccountCommand());
        commands.put("user-unblock-account", new UserUnblockAccountCommand());


        commands.put("sign-admin", new SignInAdminCommand());
        commands.put("admin", new AdminMainPageCommand());
        commands.put("exit-admin", new ExitAdminCommand());
        commands.put("payments", new DecidePaymentCommand());
        commands.put("decline-payment", new DeclinePaymentCommand());
        commands.put("perform-payment", new PerformPaymentCommand());
        commands.put("requests", new RequestsCommand());
        commands.put("stay-blocked", new BlockRequestCommand());
        commands.put("unblock", new UnlockRequestCommand());
        commands.put("users", new AllUsersCommand());
        commands.put("block-user", new BlockUserCommand());
        commands.put("unblock-user", new UnblockUserCommand());
        commands.put("adminAccounts", new AdminAccountsCommand());
        commands.put("block-account", new BlockAccountCommand());
        commands.put("unblock-account", new UnblockAccountCommand());
        // TODO put commands
    }

    public Command getCommand(String name) {
        return commands.get(name);
    }
}
