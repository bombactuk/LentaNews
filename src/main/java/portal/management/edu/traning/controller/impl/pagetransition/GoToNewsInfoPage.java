package portal.management.edu.traning.controller.impl.pagetransition;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import portal.management.edu.traning.controller.Command;
import portal.management.edu.traning.controller.ConstantCommand;
import portal.management.edu.traning.entity.News;
import portal.management.edu.traning.logic.InformationLogic;
import portal.management.edu.traning.logic.LogicException;
import portal.management.edu.traning.logic.LogicProvider;
import portal.management.edu.traning.logic.NewsLogic;

import java.io.IOException;

public class GoToNewsInfoPage implements Command {

    private final LogicProvider logicProvider = LogicProvider.getInstance();
    private final NewsLogic logicNews = logicProvider.getLogicNews();
    private final InformationLogic logicInfo = logicProvider.getLogicInfo();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {

            HttpSession session = request.getSession(false);

            if (session.getAttribute(ConstantCommand.CONSTANT_USER) == null) {

                response.sendRedirect(ConstantCommand.CONSTANT_COMMAND_GO_TO_UPDATES_PAGE);

                return;

            }

            request.setAttribute(ConstantCommand.CONSTANT_INFO_NEWS, logicNews.infoNews(new News(Integer.parseInt(request.getParameter("idNews")))));

            request.setAttribute(ConstantCommand.CONSTANT_LIST_COMMENT, logicInfo.allCommentWithUs(new News(Integer.parseInt(request.getParameter("idNews")))));

            RequestDispatcher dispatcher = request.getRequestDispatcher(ConstantCommand.CONSTANT_WEB_INF_NEWS_INFO_PAGE);
            dispatcher.forward(request, response);

        } catch (LogicException e) {

            response.getWriter().print("<script type='text/javascript'>alert('" + "Go news page Error" + "');" +
                    " window.history.back();</script>");

        }

    }

}
