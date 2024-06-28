package portal.management.edu.traning.controller.impl.command.news;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import portal.management.edu.traning.controller.Command;
import portal.management.edu.traning.controller.ConstantCommand;
import portal.management.edu.traning.entity.News;
import portal.management.edu.traning.logic.LogicException;
import portal.management.edu.traning.logic.LogicProvider;
import portal.management.edu.traning.logic.NewsLogic;

import java.io.IOException;
import java.util.List;

public class NewsSearchCommand implements Command {

    private final LogicProvider logicProvider = LogicProvider.getInstance();
    private final NewsLogic logicNews = logicProvider.getLogicNews();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {

            StringBuilder meaning = new StringBuilder();

            meaning.append(request.getParameter(ConstantCommand.CONSTANT_QUERY));

            List<News> news;

            if (meaning.toString().isEmpty()) {

                news = logicNews.displayAllNews();

            } else {

                news = logicNews.searchNews(meaning.toString());

            }

            request.setAttribute(ConstantCommand.CONSTANT_NEWS, news);

            request.setAttribute(ConstantCommand.CONSTANT_CATEGORIES, logicNews.displayAllNewsCategories());

            RequestDispatcher dispatcher = request.getRequestDispatcher(ConstantCommand.CONSTANT_WEB_INF_NEWS_PAGE);
            dispatcher.forward(request, response);

        } catch (LogicException e) {

            response.getWriter().print("<script type='text/javascript'>alert('" + "search news Error" + "');" +
                    " window.history.back();</script>");

        }

    }

}
