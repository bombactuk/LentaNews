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

public class NewsEditCommand implements Command {

    private final LogicProvider logicProvider = LogicProvider.getInstance();
    private final NewsLogic logicNews = logicProvider.getLogicNews();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {

            News news = new News();

            news.setIdNews(Integer.parseInt(request.getParameter("idNews")));
            news.setTitle(request.getParameter("title"));
            news.setShortDescription(request.getParameter("short_description"));
            news.setContent(request.getParameter("content"));

            if (logicNews.editNews(news)) {

                response.sendRedirect("urlToServlet?command=go_to_news_info_page&" +
                        "editAnswer=109&" +
                        "idNews=" + request.getParameter("idNews"));

            } else {

                response.sendRedirect("urlToServlet?command=go_to_news_info_page&" +
                        "editAnswer=110&" +
                        "idNews=" + request.getParameter("idNews"));

            }

        } catch (LogicException e) {

            response.getWriter().print("<script type='text/javascript'>alert('" + "edit News Error" + "');" +
                    " window.history.back();</script>");

        }


    }

}
