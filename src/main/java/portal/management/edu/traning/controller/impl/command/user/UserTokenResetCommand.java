package portal.management.edu.traning.controller.impl.command.user;

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

public class UserTokenResetCommand implements Command {

    private final LogicProvider logicProvider = LogicProvider.getInstance();
    private final UserLogic logic = logicProvider.getLogicUser();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {

            HttpSession session = request.getSession(false);

            User user = (User) session.getAttribute(ConstantCommand.CONSTANT_USER);

            if (user == null || !user.getRole().equals(ConstantCommand.CONSTANT_USER_ROLE_ADMIN)) {

                response.sendRedirect(ConstantCommand.CONSTANT_COMMAND_GO_TO_UPDATES_PAGE);

                return;

            }

            if (logic.resetTokenUser()) {

                response.sendRedirect(ConstantCommand.CONSTANT_COMMAND_GO_TO_ADMIN_PAGE +
                        "&functionError=105");

            } else {

                response.sendRedirect(ConstantCommand.CONSTANT_COMMAND_GO_TO_ADMIN_PAGE +
                        "&functionError=106");

            }

        } catch (LogicException e) {

            response.getWriter().print("<script type='text/javascript'>alert('" + "Reset token Error" + "');" +
                    " window.history.back();</script>");


        }

    }

}
