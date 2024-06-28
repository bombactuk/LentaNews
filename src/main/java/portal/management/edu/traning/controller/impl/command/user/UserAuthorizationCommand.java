package portal.management.edu.traning.controller.impl.command.user;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import portal.management.edu.traning.controller.Command;
import portal.management.edu.traning.controller.ConstantCommand;
import portal.management.edu.traning.entity.User;
import portal.management.edu.traning.entity.UserAuthorizationInfo;
import portal.management.edu.traning.logic.LogicException;
import portal.management.edu.traning.logic.LogicProvider;
import portal.management.edu.traning.logic.UserLogic;
import portal.management.edu.traning.logic.validation.ValidationResultValues;

import java.io.IOException;
import java.util.UUID;

public class UserAuthorizationCommand implements Command {

    private final LogicProvider logicProvider = LogicProvider.getInstance();
    private final UserLogic logicUser = logicProvider.getLogicUser();
    private final ValidationResultValues.Validator validBuild = new ValidationResultValues.Validator();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {

            StringBuilder login = new StringBuilder();
            StringBuilder password = new StringBuilder();
            StringBuilder rememberMe = new StringBuilder();
            StringBuilder token = new StringBuilder();

            login.append(request.getParameter(ConstantCommand.CONSTANT_COLUMN_LOGIN));
            password.append(request.getParameter(ConstantCommand.CONSTANT_COLUMN_PASSWORD));
            rememberMe.append(request.getParameter(ConstantCommand.CONSTANT_REMEMBER));

            ValidationResultValues validator = validBuild.validLoginPassword(login.toString(), password.toString()).build();

            if (validator.isResult()) {

                response.sendRedirect(ConstantCommand.CONSTANT_COMMAND_GO_TO_AUTHORIZATION_PAGE +
                        "&authMessage=" + validator.getException());

                return;

            }

            User user = logicUser.authorisationUser(new UserAuthorizationInfo(login.toString(), password.toString()));

            if (user != null) {

                HttpSession session = request.getSession(true);

                session.setAttribute(ConstantCommand.CONSTANT_USER, user);

                if (rememberMe.toString().equals(ConstantCommand.CONSTANT_REMEMBER)) {

                    token.append(UUID.randomUUID());

                    Cookie cookie = new Cookie(ConstantCommand.CONSTANT_REMEMBER, token.toString());

                    cookie.setHttpOnly(true);
                    cookie.setSecure(true);

                    user.setToken(token.toString());

                    if (logicUser.addTokenUser(user)) {

                        response.addCookie(cookie);

                    }

                }

                response.sendRedirect(ConstantCommand.CONSTANT_COMMAND_GO_TO_NEWS_PAGE);

            } else {

                response.sendRedirect(ConstantCommand.CONSTANT_COMMAND_GO_TO_AUTHORIZATION_PAGE +
                        "&authMessage=109");

            }

        } catch (LogicException e) {

            response.getWriter().print("<script type='text/javascript'>alert('" + "Authorisation Error" + "');" +
                    " window.history.back();</script>");

        }

    }

}
