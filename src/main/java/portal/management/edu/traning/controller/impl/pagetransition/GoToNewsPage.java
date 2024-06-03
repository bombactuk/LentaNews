package portal.management.edu.traning.controller.impl.pagetransition;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import portal.management.edu.traning.controller.Command;

import java.io.IOException;

public class GoToNewsPage implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (session.getAttribute("user") == null) {

            response.sendRedirect("urlToServlet?command=go_to_updates_page");

            return;

        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/news_page.jsp");
        dispatcher.forward(request, response);

    }

}
