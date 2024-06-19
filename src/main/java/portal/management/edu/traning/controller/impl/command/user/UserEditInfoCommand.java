package portal.management.edu.traning.controller.impl.command.user;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import portal.management.edu.traning.controller.Command;
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

            UserInfo userInfo = new UserInfo();

            userInfo.setName(request.getParameter("name"));
            userInfo.setBirthday(LocalDate.parse(request.getParameter("birthday")));
            userInfo.setCountry(request.getParameter("country"));

            User user = new User();

            user.setIdUser(Integer.parseInt(request.getParameter("idUser")));

            if (logicUser.editInfoUser(userInfo, user)) {

                response.sendRedirect("urlToServlet?command=go_to_profile_page&" +
                        "editAnswer=117&" +
                        "idUser=" + request.getParameter("idUser"));

            } else {

                response.sendRedirect("urlToServlet?command=go_to_news_info_page&" +
                        "editAnswer=118&" +
                        "idUser=" + request.getParameter("idUser"));

            }

        } catch (
                LogicException e) {

            response.getWriter().print("<script type='text/javascript'>alert('" + "edit Profile Error" + "');" +
                    " window.history.back();</script>");

        }

    }

}
