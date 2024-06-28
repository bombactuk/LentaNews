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

public class NewsEditCommand implements Command {

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
            news.setTitle(request.getParameter(ConstantCommand.CONSTANT_COLUMN_TITLE));
            news.setShortDescription(request.getParameter(ConstantCommand.CONSTANT_COLUMN_SHORT_DESCRIPTION));
            news.setContent(request.getParameter(ConstantCommand.CONSTANT_COLUMN_CONTENT));

            if (logicNews.editNews(news)) {

                response.sendRedirect(ConstantCommand.CONSTANT_COMMAND_GO_TO_NEWS_INFO_PAGE +
                        "&editAnswer=109&" +
                        ConstantCommand.CONSTANT_COLUMN_NEWS_ID +
                        "=" + request.getParameter(ConstantCommand.CONSTANT_COLUMN_NEWS_ID));

            } else {

                response.sendRedirect(ConstantCommand.CONSTANT_COMMAND_GO_TO_NEWS_INFO_PAGE +
                        "&editAnswer=110&" +
                        ConstantCommand.CONSTANT_COLUMN_NEWS_ID +
                        "=" + request.getParameter(ConstantCommand.CONSTANT_COLUMN_NEWS_ID));

            }

        } catch (LogicException e) {

            response.getWriter().print("<script type='text/javascript'>alert('" + "edit News Error" + "');" +
                    " window.history.back();</script>");

        }


    }

}
