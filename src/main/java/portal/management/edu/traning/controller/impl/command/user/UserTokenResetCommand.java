package portal.management.edu.traning.controller.impl.command.user;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import portal.management.edu.traning.controller.Command;
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

            if (logic.resetTokenUser()) {

                response.sendRedirect("urlToServlet?command=go_to_admin_page&" +
                        "functionError=Token reset completed successfully!");

            } else {

                response.sendRedirect("urlToServlet?command=go_to_admin_page&" +
                        "functionError=Token reset not completed!");

            }

        } catch (LogicException e) {

            response.getWriter().print("<script type='text/javascript'>alert('" + "Reset token Error" + "');" +
                    " window.history.back();</script>");


        }

    }

}
