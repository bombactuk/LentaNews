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

public class CommentDeleteCommand implements Command {

    private final LogicProvider logicProvider = LogicProvider.getInstance();
    private final InformationLogic logicInfo = logicProvider.getLogicInfo();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {

            Comment comment = new Comment();

            comment.setIdComment(Integer.parseInt(request.getParameter("idComment")));

            if (logicInfo.deleteComment(comment)) {

                response.sendRedirect("urlToServlet?command=go_to_news_info_page&" +
                        "editAnswer=113&" +
                        "idNews=" + request.getParameter("idNews"));

            } else {

                response.sendRedirect("urlToServlet?command=go_to_news_info_page&" +
                        "editAnswer=114&" +
                        "idNews=" + request.getParameter("idNews"));

            }

        } catch (LogicException e) {

            response.getWriter().print("<script type='text/javascript'>alert('" + "delete Comment Error" + "');" +
                    " window.history.back();</script>");

        }

    }

}
