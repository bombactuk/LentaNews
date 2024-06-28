package portal.management.edu.traning.controller.impl.command.news;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import portal.management.edu.traning.controller.Command;
import portal.management.edu.traning.controller.ConstantCommand;
import portal.management.edu.traning.entity.News;
import portal.management.edu.traning.entity.User;
import portal.management.edu.traning.logic.LogicException;
import portal.management.edu.traning.logic.LogicProvider;
import portal.management.edu.traning.logic.NewsLogic;

import java.io.IOException;

public class NewsDeleteCommand implements Command {

    private final LogicProvider logicProvider = LogicProvider.getInstance();
    private final NewsLogic logicNews = logicProvider.getLogicNews();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {

            HttpSession session = request.getSession(false);

            User user = (User) session.getAttribute(ConstantCommand.CONSTANT_USER);

            if (user == null || !user.getRole().equals(ConstantCommand.CONSTANT_USER_ROLE_ADMIN) &&
                    !user.getRole().equals(ConstantCommand.CONSTANT_USER_ROLE_EDITOR)) {

                response.sendRedirect(ConstantCommand.CONSTANT_COMMAND_GO_TO_UPDATES_PAGE);

                return;

            }

            News news = new News();

            news.setIdNews(Integer.parseInt(request.getParameter(ConstantCommand.CONSTANT_COLUMN_NEWS_ID)));

            if (logicNews.deleteNews(news)) {

                response.sendRedirect(ConstantCommand.CONSTANT_COMMAND_GO_TO_NEWS_PAGE +
                        "&messageFunctions=117");

            } else {

                response.sendRedirect(ConstantCommand.CONSTANT_COMMAND_GO_TO_NEWS_PAGE +
                        "&messageFunctions=118");

            }

        } catch (LogicException e) {

            response.getWriter().print("<script type='text/javascript'>alert('" + "delete News Error" + "');" +
                    " window.history.back();</script>");

        }

    }

}
