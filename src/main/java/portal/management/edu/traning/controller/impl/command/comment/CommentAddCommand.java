package portal.management.edu.traning.controller.impl.command.comment;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import portal.management.edu.traning.controller.Command;
import portal.management.edu.traning.entity.Comment;
import portal.management.edu.traning.logic.InformationLogic;
import portal.management.edu.traning.logic.LogicException;
import portal.management.edu.traning.logic.LogicProvider;

import java.io.IOException;
import java.time.LocalDate;

public class CommentAddCommand implements Command {

    private final LogicProvider logicProvider = LogicProvider.getInstance();
    private final InformationLogic logicInfo = logicProvider.getLogicInfo();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {

            Comment comment = new Comment();

            comment.setContent(request.getParameter("content"));
            comment.setDatePost(LocalDate.now());
            comment.setIdNews(Integer.parseInt(request.getParameter("idNews")));
            comment.setIdUser(Integer.parseInt(request.getParameter("idUser")));

            if (logicInfo.addComment(comment)) {

                response.sendRedirect("urlToServlet?command=go_to_news_info_page&" +
                        "editAnswer=111&" +
                        "idNews=" + request.getParameter("idNews"));

            } else {

                response.sendRedirect("urlToServlet?command=go_to_news_info_page&" +
                        "editAnswer=112&" +
                        "idNews=" + request.getParameter("idNews"));

            }

        } catch (LogicException e) {

            response.getWriter().print("<script type='text/javascript'>alert('" + "add Comment Error" + "');" +
                    " window.history.back();</script>");

        }

    }

}
