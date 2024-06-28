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
import java.time.LocalDate;

public class NewsAddCommand implements Command {

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

            news.setTitle(request.getParameter(ConstantCommand.CONSTANT_COLUMN_TITLE));
            news.setShortDescription(request.getParameter(ConstantCommand.CONSTANT_COLUMN_SHORT_DESCRIPTION));
            news.setContent(request.getParameter(ConstantCommand.CONSTANT_COLUMN_CONTENT));
            news.setPostDate(LocalDate.now());
            news.setIdAdmin(user.getIdUser());
            news.setIdCategories(Integer.parseInt(request.getParameter(ConstantCommand.CONSTANT_COLUMN_CATEGORIES_ID)));

            if (logicNews.addNews(news)) {

                response.sendRedirect(ConstantCommand.CONSTANT_COMMAND_GO_TO_NEWS_PAGE +
                        "&messageFunctions=115");

            } else {

                response.sendRedirect(ConstantCommand.CONSTANT_COMMAND_GO_TO_NEWS_PAGE +
                        "&messageFunctions=116");

            }

        } catch (LogicException e) {

            response.getWriter().print("<script type='text/javascript'>alert('" + "add News Error" + "');" +
                    " window.history.back();</script>");

        }

    }

}
