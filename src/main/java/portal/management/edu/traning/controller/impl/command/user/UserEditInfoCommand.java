package portal.management.edu.traning.controller.impl.command.user;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import portal.management.edu.traning.controller.Command;
import portal.management.edu.traning.controller.ConstantCommand;
import portal.management.edu.traning.entity.User;
import portal.management.edu.traning.entity.UserInfo;
import portal.management.edu.traning.logic.LogicException;
import portal.management.edu.traning.logic.LogicProvider;
import portal.management.edu.traning.logic.UserLogic;

import java.io.IOException;
import java.time.LocalDate;

public class UserEditInfoCommand implements Command {

    private final LogicProvider logicProvider = LogicProvider.getInstance();
    private final UserLogic logicUser = logicProvider.getLogicUser();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {

            HttpSession session = request.getSession(false);

            User user = (User) session.getAttribute(ConstantCommand.CONSTANT_USER);

            if (user == null) {

                response.sendRedirect(ConstantCommand.CONSTANT_COMMAND_GO_TO_UPDATES_PAGE);

                return;

            }

            UserInfo userInfo = new UserInfo();

            userInfo.setName(request.getParameter(ConstantCommand.CONSTANT_COLUMN_NAME));
            userInfo.setBirthday(LocalDate.parse(request.getParameter(ConstantCommand.CONSTANT_COLUMN_BIRTHDAY)));
            userInfo.setCountry(request.getParameter(ConstantCommand.CONSTANT_COLUMN_COUNTRY));

            if (logicUser.editInfoUser(userInfo, user)) {

                response.sendRedirect(ConstantCommand.CONSTANT_COMMAND_GO_TO_PROFILE_PAGE +
                        "&editAnswer=117&" +
                        ConstantCommand.CONSTANT_COLUMN_USER_ID + "=" + user.getIdUser());

            } else {

                response.sendRedirect(ConstantCommand.CONSTANT_COMMAND_GO_TO_NEWS_INFO_PAGE +
                        "&editAnswer=118&" +
                        ConstantCommand.CONSTANT_COLUMN_USER_ID + "=" + user.getIdUser());

            }

        } catch (
                LogicException e) {

            response.getWriter().print("<script type='text/javascript'>alert('" + "edit Profile Error" + "');" +
                    " window.history.back();</script>");

        }

    }

}
