package portal.management.edu.traning.controller.impl.pagetransition;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import portal.management.edu.traning.controller.Command;
import portal.management.edu.traning.entity.News;
import portal.management.edu.traning.logic.LogicException;
import portal.management.edu.traning.logic.LogicProvider;
import portal.management.edu.traning.logic.NewsLogic;

import java.io.IOException;
import java.util.List;

public class GoToNewsPage implements Command {

    private final LogicProvider logicProvider = LogicProvider.getInstance();
    private final NewsLogic logicNews = logicProvider.getLogicNews();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {

            HttpSession session = request.getSession(false);

            if (session.getAttribute("user") == null) {

                response.sendRedirect("urlToServlet?command=go_to_updates_page");

                return;

            }

            request.setAttribute("news", logicNews.displayAllNews());

            request.setAttribute("categories", logicNews.displayAllNewsCategories());

            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/news_page.jsp");
            dispatcher.forward(request, response);

        } catch (LogicException e) {

            response.getWriter().print("<script type='text/javascript'>alert('" + "Go news page Error" + "');" +
                    " window.history.back();</script>");

        }

    }

}
