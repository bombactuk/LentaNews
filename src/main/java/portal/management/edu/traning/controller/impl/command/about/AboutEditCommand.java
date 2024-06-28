package portal.management.edu.traning.controller.impl.command.about;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import portal.management.edu.traning.controller.Command;
import portal.management.edu.traning.controller.ConstantCommand;
import portal.management.edu.traning.entity.AboutInfo;
import portal.management.edu.traning.entity.User;
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

            HttpSession session = request.getSession(false);

            User user = (User) session.getAttribute(ConstantCommand.CONSTANT_USER);

            if (user == null || !user.getRole().equals(ConstantCommand.CONSTANT_USER_ROLE_ADMIN)) {

                response.sendRedirect(ConstantCommand.CONSTANT_COMMAND_GO_TO_UPDATES_PAGE);

                return;

            }

            AboutInfo aboutInfo = new AboutInfo();

            aboutInfo.setIdAbout(Integer.parseInt(request.getParameter(ConstantCommand.CONSTANT_COLUMN_ABOUT_ID)));
            aboutInfo.setContent(request.getParameter(ConstantCommand.CONSTANT_COLUMN_CONTENT));
            aboutInfo.setDate_post(LocalDate.now());
            aboutInfo.setStatus(ConstantCommand.CONSTANT_COLUMN_STATUS_ACTIVE);
            aboutInfo.setIdAdmin(user.getIdUser());

            if (logicInfo.editAbout(aboutInfo)) {

                response.sendRedirect(ConstantCommand.CONSTANT_COMMAND_GO_TO_ABOUT_PAGE +
                        "&functionError=101");

            } else {
                response.sendRedirect(ConstantCommand.CONSTANT_COMMAND_GO_TO_ABOUT_PAGE +
                        "&functionError=102");

            }

        } catch (LogicException e) {

            response.getWriter().print("<script type='text/javascript'>alert('" + "edit About Error" + "');" +
                    " window.history.back();</script>");

        }

    }

}
