package portal.management.edu.traning.controller.impl.pagetransition;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import portal.management.edu.traning.controller.Command;
import portal.management.edu.traning.controller.ConstantCommand;

import java.io.IOException;

public class GoToAuthorizationPage implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        RequestDispatcher dispatcher = request.getRequestDispatcher(ConstantCommand.CONSTANT_WEB_INF_AUTHORIZATION_PAGE);
        dispatcher.forward(request, response);

    }

}
