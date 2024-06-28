package portal.management.edu.traning.controller.impl.command.contact;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import portal.management.edu.traning.controller.Command;
import portal.management.edu.traning.controller.ConstantCommand;
import portal.management.edu.traning.entity.ContactCommunication;
import portal.management.edu.traning.entity.User;
import portal.management.edu.traning.logic.InformationLogic;
import portal.management.edu.traning.logic.LogicException;
import portal.management.edu.traning.logic.LogicProvider;

import java.io.IOException;

public class ContactCommunicationAddCommand implements Command {

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

            ServletContext context = request.getServletContext();

            ContactCommunication contact = new ContactCommunication();

            contact.setImg(request.getParameter(ConstantCommand.CONSTANT_COLUMN_IMG));
            contact.setLink(request.getParameter(ConstantCommand.CONSTANT_COLUMN_LINK));
            contact.setStatus(ConstantCommand.CONSTANT_COLUMN_STATUS_ACTIVE);
            contact.setIdAdmin(user.getIdUser());

            if (logicInfo.addContact(contact)) {

                context.setAttribute(ConstantCommand.CONSTANT_CONTACT, logicInfo.allConnectionsWithUs());

                response.sendRedirect(ConstantCommand.CONSTANT_COMMAND_GO_TO_ADMIN_PAGE +
                        "&functionError=103");

            } else {

                response.sendRedirect(ConstantCommand.CONSTANT_COMMAND_GO_TO_ADMIN_PAGE +
                        "&functionError=104");

            }

        } catch (LogicException e) {

            response.getWriter().print("<script type='text/javascript'>alert('" + "add Contact Error" + "');" +
                    " window.history.back();</script>");

        }

    }

}
