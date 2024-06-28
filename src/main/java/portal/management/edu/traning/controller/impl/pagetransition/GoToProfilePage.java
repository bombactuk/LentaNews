package portal.management.edu.traning.controller.impl.pagetransition;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import portal.management.edu.traning.controller.Command;
import portal.management.edu.traning.controller.ConstantCommand;
import portal.management.edu.traning.entity.User;
import portal.management.edu.traning.logic.LogicException;
import portal.management.edu.traning.logic.LogicProvider;
import portal.management.edu.traning.logic.UserLogic;

import java.io.IOException;

public class GoToProfilePage implements Command {

    private final LogicProvider logicProvider = LogicProvider.getInstance();
    private final UserLogic logicUser = logicProvider.getLogicUser();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {

            HttpSession session = request.getSession(false);

            if (session.getAttribute(ConstantCommand.CONSTANT_USER) == null) {

                response.sendRedirect(ConstantCommand.CONSTANT_COMMAND_GO_TO_UPDATES_PAGE);

                return;

            }

            User user = new User(Integer.parseInt(request.getParameter(ConstantCommand.CONSTANT_COLUMN_USER_ID)));

            request.setAttribute(ConstantCommand.CONSTANT_INFO_USER, logicUser.infoUser(user));

            RequestDispatcher dispatcher = request.getRequestDispatcher(ConstantCommand.CONSTANT_WEB_INF_PROFILE_PAGE);
            dispatcher.forward(request, response);

        } catch (LogicException e) {

            response.getWriter().print("<script type='text/javascript'>alert('" + "Go Profile Error" + "');" +
                    " window.history.back();</script>");

        }

    }

}
