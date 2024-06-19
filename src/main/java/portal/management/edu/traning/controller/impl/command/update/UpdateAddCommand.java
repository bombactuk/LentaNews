package portal.management.edu.traning.controller.impl.command.update;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import portal.management.edu.traning.controller.Command;
import portal.management.edu.traning.entity.Update;
import portal.management.edu.traning.logic.InformationLogic;
import portal.management.edu.traning.logic.LogicException;
import portal.management.edu.traning.logic.LogicProvider;

import java.io.IOException;
import java.time.LocalDate;

public class UpdateAddCommand implements Command {

    private final LogicProvider logicProvider = LogicProvider.getInstance();
    private final InformationLogic logicInfo = logicProvider.getLogicInfo();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {

            Update update = new Update();

            update.setTitle(request.getParameter("title"));
            update.setContent(request.getParameter("content"));
            update.setDate(LocalDate.now());
            update.setIdAdmin(Integer.parseInt(request.getParameter("idAdmin")));

            if (logicInfo.addUpdate(update)) {

                response.sendRedirect("urlToServlet?command=go_to_updates_page&" +
                        "functionError=123");

            } else {

                response.sendRedirect("urlToServlet?command=go_to_updates_page&" +
                        "functionError=124");

            }

        } catch (LogicException e) {

            response.getWriter().print("<script type='text/javascript'>alert('" + "addUpdate Error" + "');" +
                    " window.history.back();</script>");

        }

    }

}
