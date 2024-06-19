package portal.management.edu.traning.controller;

import portal.management.edu.traning.controller.impl.command.NoSuchCommand;
import portal.management.edu.traning.controller.impl.command.about.AboutEditCommand;
import portal.management.edu.traning.controller.impl.command.comment.CommentAddCommand;
import portal.management.edu.traning.controller.impl.command.comment.CommentDeleteCommand;
import portal.management.edu.traning.controller.impl.command.locale.LocaleSetCommand;
import portal.management.edu.traning.controller.impl.command.news.*;
import portal.management.edu.traning.controller.impl.command.update.UpdateAddCommand;
import portal.management.edu.traning.controller.impl.command.user.*;
import portal.management.edu.traning.controller.impl.command.contact.ContactCommunicationAddCommand;
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
        this.repository.put(CommandName.GO_TO_ABOUT_PAGE, new GoToAboutPage());
        this.repository.put(CommandName.GO_TO_PROFILE_PAGE, new GoToProfilePage());
        this.repository.put(CommandName.GO_TO_ADMIN_PAGE, new GoToAdminPage());
        this.repository.put(CommandName.GO_TO_NEWS_PAGE, new GoToNewsPage());
        this.repository.put(CommandName.GO_TO_NEWS_INFO_PAGE, new GoToNewsInfoPage());

        this.repository.put(CommandName.USER_AUTHORIZATION, new UserAuthorizationCommand());
        this.repository.put(CommandName.USER_REGISTRATION, new UserRegistrationCommand());
        this.repository.put(CommandName.USER_LOGOUT, new UserLogoutCommand());
        this.repository.put(CommandName.USER_TOKEN_RESET, new UserTokenResetCommand());
        this.repository.put(CommandName.USER_INFO_EDIT, new UserEditInfoCommand());

        this.repository.put(CommandName.ABOUT_US_EDIT, new AboutEditCommand());

        this.repository.put(CommandName.UPDATE_ADD, new UpdateAddCommand());

        this.repository.put(CommandName.NEWS_SEARCH, new NewsSearchCommand());
        this.repository.put(CommandName.NEWS_SEARCH_CATEGORIES, new NewsSearchCategoriesCommand());
        this.repository.put(CommandName.NEWS_ADD, new NewsAddCommand());
        this.repository.put(CommandName.NEWS_EDIT, new NewsEditCommand());
        this.repository.put(CommandName.NEWS_DELETE, new NewsDeleteCommand());

        this.repository.put(CommandName.LOCALE_SET, new LocaleSetCommand());

        this.repository.put(CommandName.COMMENT_ADD, new CommentAddCommand());
        this.repository.put(CommandName.COMMENT_DELETE, new CommentDeleteCommand());

        this.repository.put(CommandName.CONTACT_ADD, new ContactCommunicationAddCommand());

    }

    Command getCommand(String name) {

        CommandName commandName;
        Command command;

        try {
            commandName = CommandName.valueOf(name.toUpperCase());
            command = this.repository.get(commandName);
        } catch (NullPointerException | IllegalArgumentException var5) {
            command = this.repository.get(CommandName.WRONG_REQUEST);
        }

        return command;

    }

}
