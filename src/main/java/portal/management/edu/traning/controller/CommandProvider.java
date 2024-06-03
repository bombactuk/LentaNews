package portal.management.edu.traning.controller;

import portal.management.edu.traning.controller.impl.command.NoSuchCommand;
import portal.management.edu.traning.controller.impl.command.admin.FunctionSamplingCommand;
import portal.management.edu.traning.controller.impl.command.update.UpdateAddCommand;
import portal.management.edu.traning.controller.impl.command.user.UserAuthorizationCommand;
import portal.management.edu.traning.controller.impl.command.user.UserLogoutCommand;
import portal.management.edu.traning.controller.impl.command.user.UserRegistrationCommand;
import portal.management.edu.traning.controller.impl.command.user.UserTokenResetCommand;
import portal.management.edu.traning.controller.impl.pagetransition.*;

import java.util.HashMap;
import java.util.Map;

public class CommandProvider {

    private final Map<CommandName, Command> repository = new HashMap<>();

    CommandProvider() {

        this.repository.put(CommandName.WRONG_REQUEST, new NoSuchCommand());

        this.repository.put(CommandName.GO_TO_UPDATES_PAGE, new GoToUpdatesPage());
        this.repository.put(CommandName.GO_TO_AUTHORIZATION_PAGE, new GoToAuthorizationPage());
        this.repository.put(CommandName.GO_TO_REGISTRATION_PAGE, new GoToRegistrationPage());
        this.repository.put(CommandName.GO_TO_INDEX_PAGE, new GoToIndexPage());
        this.repository.put(CommandName.GO_TO_ABOUT_PAGE, new GoToAboutPage());
        this.repository.put(CommandName.GO_TO_PROFILE_PAGE, new GoToProfilePage());
        this.repository.put(CommandName.GO_TO_ADMIN_PAGE, new GoToAdminPage());
        this.repository.put(CommandName.GO_TO_NEWS_PAGE, new GoToNewsPage());

        this.repository.put(CommandName.USER_AUTHORIZATION, new UserAuthorizationCommand());
        this.repository.put(CommandName.USER_REGISTRATION, new UserRegistrationCommand());
        this.repository.put(CommandName.USER_LOGOUT, new UserLogoutCommand());
        this.repository.put(CommandName.USER_TOKEN_RESET, new UserTokenResetCommand());

        this.repository.put(CommandName.ADMIN_FUNCTION_SAMPLING, new FunctionSamplingCommand());

        this.repository.put(CommandName.UPDATE_ADD, new UpdateAddCommand());

    }

    Command getCommand(String name) {

        CommandName commandName = null;
        Command command = null;

        try {
            commandName = CommandName.valueOf(name.toUpperCase());
            command = this.repository.get(commandName);
        } catch (NullPointerException | IllegalArgumentException var5) {
            command = this.repository.get(CommandName.WRONG_REQUEST);
        }

        return command;

    }

}
