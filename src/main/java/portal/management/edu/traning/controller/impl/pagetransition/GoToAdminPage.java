package portal.management.edu.traning.controller.impl.pagetransition;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import portal.management.edu.traning.controller.Command;
import portal.management.edu.traning.controller.ConstantCommand;

import java.io.IOException;

public class GoToAdminPage implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (session.getAttribute(ConstantCommand.CONSTANT_USER) == null) {

            response.sendRedirect(ConstantCommand.CONSTANT_COMMAND_GO_TO_UPDATES_PAGE);

            return;

        }

        RequestDispatcher dispatcher = request.getRequestDispatcher(ConstantCommand.CONSTANT_WEB_INF_ADMIN_PAGE);
        dispatcher.forward(request, response);

    }

}
