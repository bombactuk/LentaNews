package portal.management.edu.traning.controller.impl.command.user;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import portal.management.edu.traning.controller.Command;
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

            login.append(request.getParameter("login"));
            password.append(request.getParameter("password"));
            rememberMe.append(request.getParameter("remember-me"));

            ValidationResultValues validator = validBuild.validLoginPassword(login.toString(), password.toString()).build();

            if (validator.isResult()) {

                response.sendRedirect("urlToServlet?command=go_to_authorization_page&authMessage=" + validator.getException());

                return;

            }

            User user = logicUser.authorisationUser(new UserAuthorizationInfo(login.toString(), password.toString()));

            if (user != null) {

                HttpSession session = request.getSession(true);

                session.setAttribute("user", user);

                if (rememberMe.toString().equals("remember-me")) {

                    token.append(UUID.randomUUID());

                    Cookie cookie = new Cookie("remember-me", token.toString());

                    cookie.setHttpOnly(true);
                    cookie.setSecure(true);

                    user.setToken(token.toString());

                    if (logicUser.addTokenUser(user)) {

                        response.addCookie(cookie);

                    }

                }

                response.sendRedirect("urlToServlet?command=go_to_news_page");

            } else {

                response.sendRedirect("urlToServlet?command=go_to_authorization&authMessage=Wrong login or password!");

            }

        } catch (LogicException e) {

            response.getWriter().print("<script type='text/javascript'>alert('" + "Authorisation Error" + "');" +
                    " window.history.back();</script>");

        }

    }

}
