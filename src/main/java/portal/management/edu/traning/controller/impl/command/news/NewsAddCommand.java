package portal.management.edu.traning.controller.impl.command.news;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import portal.management.edu.traning.controller.Command;
import portal.management.edu.traning.entity.News;
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

            News news = new News();

            news.setTitle(request.getParameter("title"));
            news.setShortDescription(request.getParameter("short_description"));
            news.setContent(request.getParameter("content"));
            news.setPostDate(LocalDate.now());
            news.setIdAdmin(Integer.parseInt(request.getParameter("idAdmin")));
            news.setIdCategories(Integer.parseInt(request.getParameter("idCategories")));

            if (logicNews.addNews(news)) {

                response.sendRedirect("urlToServlet?command=go_to_news_page&" +
                        "messageFunctions=Addition news was successful!");

            } else {

                response.sendRedirect("urlToServlet?command=go_to_news_page&" +
                        "messageFunctions=Adding news was not successful!");

            }

        } catch (LogicException e) {

            response.getWriter().print("<script type='text/javascript'>alert('" + "add News Error" + "');" +
                    " window.history.back();</script>");

        }

    }

}
