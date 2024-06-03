package portal.management.edu.traning.controller.impl.command;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import portal.management.edu.traning.controller.Command;

import java.io.IOException;

public class NoSuchCommand implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.getWriter().print("<script type='text/javascript'>alert('" + "Command Error" + "');" +
                " window.history.back();</script>");

    }

}
