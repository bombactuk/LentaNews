package portal.management.edu.traning.controller.impl.command.user;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import portal.management.edu.traning.controller.Command;
import portal.management.edu.traning.controller.ConstantCommand;

import java.io.IOException;

public class UserLogoutCommand implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Cookie[] cookies = request.getCookies();

        if (cookies != null) {

            for (Cookie c : cookies) {

                if (c.getName().equals(ConstantCommand.CONSTANT_REMEMBER)) {

                    Cookie cookie = new Cookie(ConstantCommand.CONSTANT_REMEMBER, c.getValue());
                    cookie.setMaxAge(0);
                    response.addCookie(cookie);

                }

            }

        }

        HttpSession session = request.getSession();

        if (session != null) {
            session.invalidate();
        }

        response.sendRedirect(ConstantCommand.CONSTANT_COMMAND_GO_TO_UPDATES_PAGE);

    }

}
