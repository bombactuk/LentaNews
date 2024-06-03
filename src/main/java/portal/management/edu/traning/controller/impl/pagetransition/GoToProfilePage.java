package portal.management.edu.traning.controller.impl.pagetransition;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import portal.management.edu.traning.controller.Command;
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

            if (session.getAttribute("user") == null) {

                response.sendRedirect("urlToServlet?command=go_to_updates_page");

                return;

            }

            User user = new User(Integer.parseInt(request.getParameter("idUser")));

            request.setAttribute("infoUser", logicUser.infoUser(user));

            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/profile_page.jsp");
            dispatcher.forward(request, response);

        } catch (LogicException e) {

            response.getWriter().print("<script type='text/javascript'>alert('" + "Go Profile Error" + "');" +
                    " window.history.back();</script>");

        }

    }

}
