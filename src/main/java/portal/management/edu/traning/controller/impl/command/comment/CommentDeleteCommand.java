package portal.management.edu.traning.controller.impl.command.comment;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import portal.management.edu.traning.controller.Command;
import portal.management.edu.traning.controller.ConstantCommand;
import portal.management.edu.traning.entity.Comment;
import portal.management.edu.traning.entity.User;
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

            HttpSession session = request.getSession(false);

            User user = (User) session.getAttribute(ConstantCommand.CONSTANT_USER);

            if (user == null) {

                response.sendRedirect(ConstantCommand.CONSTANT_COMMAND_GO_TO_UPDATES_PAGE);

                return;

            }

            Comment comment = new Comment();

            comment.setIdComment(Integer.parseInt(request.getParameter(ConstantCommand.CONSTANT_COLUMN_COMMENT_ID)));

            if (logicInfo.deleteComment(comment)) {

                response.sendRedirect(ConstantCommand.CONSTANT_COMMAND_GO_TO_NEWS_INFO_PAGE +
                        "&editAnswer=113&" +
                        ConstantCommand.CONSTANT_COLUMN_NEWS_ID +
                        "=" + request.getParameter(ConstantCommand.CONSTANT_COLUMN_NEWS_ID));

            } else {

                response.sendRedirect(ConstantCommand.CONSTANT_COMMAND_GO_TO_NEWS_INFO_PAGE +
                        "&editAnswer=114&" +
                        ConstantCommand.CONSTANT_COLUMN_NEWS_ID +
                        "=" + request.getParameter(ConstantCommand.CONSTANT_COLUMN_NEWS_ID));

            }

        } catch (LogicException e) {

            response.getWriter().print("<script type='text/javascript'>alert('" + "delete Comment Error" + "');" +
                    " window.history.back();</script>");

        }

    }

}
