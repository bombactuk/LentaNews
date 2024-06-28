package portal.management.edu.traning.controller.impl.command.locale;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import portal.management.edu.traning.controller.Command;
import portal.management.edu.traning.controller.ConstantCommand;

import java.io.IOException;
import java.util.Locale;

public class LocaleSetCommand implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Locale locale = new Locale(request.getParameter(ConstantCommand.CONSTANT_LANGUAGE));
        request.getSession().setAttribute(ConstantCommand.CONSTANT_LOCALE, locale);

        response.sendRedirect(request.getHeader(ConstantCommand.CONSTANT_REFERER));

    }

}
