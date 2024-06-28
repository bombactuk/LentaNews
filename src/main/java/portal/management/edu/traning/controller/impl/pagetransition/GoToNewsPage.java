package portal.management.edu.traning.controller.impl.pagetransition;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import portal.management.edu.traning.controller.Command;
import portal.management.edu.traning.controller.ConstantCommand;
import portal.management.edu.traning.logic.LogicException;
import portal.management.edu.traning.logic.LogicProvider;
import portal.management.edu.traning.logic.NewsLogic;

import java.io.IOException;

public class GoToNewsPage implements Command {

    private final LogicProvider logicProvider = LogicProvider.getInstance();
    private final NewsLogic logicNews = logicProvider.getLogicNews();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {

            HttpSession session = request.getSession(false);

            if (session.getAttribute(ConstantCommand.CONSTANT_USER) == null) {

                response.sendRedirect(ConstantCommand.CONSTANT_COMMAND_GO_TO_UPDATES_PAGE);

                return;

            }

            request.setAttribute(ConstantCommand.CONSTANT_NEWS, logicNews.displayAllNews());

            request.setAttribute(ConstantCommand.CONSTANT_CATEGORIES, logicNews.displayAllNewsCategories());

            RequestDispatcher dispatcher = request.getRequestDispatcher(ConstantCommand.CONSTANT_WEB_INF_NEWS_PAGE);
            dispatcher.forward(request, response);

        } catch (LogicException e) {

            response.getWriter().print("<script type='text/javascript'>alert('" + "Go news page Error" + "');" +
                    " window.history.back();</script>");

        }

    }

}
