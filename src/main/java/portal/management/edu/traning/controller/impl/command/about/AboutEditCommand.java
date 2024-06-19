package portal.management.edu.traning.controller.impl.command.about;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import portal.management.edu.traning.controller.Command;
import portal.management.edu.traning.entity.AboutInfo;
import portal.management.edu.traning.logic.InformationLogic;
import portal.management.edu.traning.logic.LogicException;
import portal.management.edu.traning.logic.LogicProvider;

import java.io.IOException;
import java.time.LocalDate;

public class AboutEditCommand implements Command {

    private final LogicProvider logicProvider = LogicProvider.getInstance();
    private final InformationLogic logicInfo = logicProvider.getLogicInfo();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {

            AboutInfo aboutInfo = new AboutInfo();

            aboutInfo.setIdAbout(Integer.parseInt(request.getParameter("idAbout")));
            aboutInfo.setContent(request.getParameter("content"));
            aboutInfo.setDate_post(LocalDate.now());
            aboutInfo.setStatus("active");
            aboutInfo.setIdAdmin(Integer.parseInt(request.getParameter("idAdmin")));

            if (logicInfo.editAbout(aboutInfo)) {

                response.sendRedirect("urlToServlet?command=go_to_about_page&" +
                        "functionError=101");

            } else {
                response.sendRedirect("urlToServlet?command=go_to_about_page&" +
                        "functionError=102");

            }

        } catch (LogicException e) {

            response.getWriter().print("<script type='text/javascript'>alert('" + "edit About Error" + "');" +
                    " window.history.back();</script>");

        }

    }

}
