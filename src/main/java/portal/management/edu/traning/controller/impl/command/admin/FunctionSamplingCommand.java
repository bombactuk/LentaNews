package portal.management.edu.traning.controller.impl.command.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import portal.management.edu.traning.controller.Command;

import java.io.IOException;

public class FunctionSamplingCommand implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.sendRedirect("urlToServlet?command=go_to_admin_page&functionCommand=" + request.getParameter("function"));

    }

}
