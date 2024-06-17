package portal.management.edu.traning.controller.impl.command.contact;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import portal.management.edu.traning.controller.Command;
import portal.management.edu.traning.entity.ContactCommunication;
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

            ServletContext context = request.getServletContext();

            ContactCommunication contact = new ContactCommunication();

            contact.setImg(request.getParameter("img"));
            contact.setLink(request.getParameter("link"));
            contact.setStatus("active");
            contact.setIdAdmin(Integer.parseInt(request.getParameter("idAdmin")));

            if (logicInfo.addContact(contact)) {

                context.setAttribute("contacts", logicInfo.allConnectionsWithUs());

                response.sendRedirect("urlToServlet?command=go_to_admin_page&" +
                        "functionError=Addition contact was successful!");

            } else {

                response.sendRedirect("urlToServlet?command=go_to_admin_page&" +
                        "functionError=Adding contact was not successful!");

            }

        } catch (LogicException e) {

            response.getWriter().print("<script type='text/javascript'>alert('" + "add Contact Error" + "');" +
                    " window.history.back();</script>");

        }

    }

}
