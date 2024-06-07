package portal.management.edu.traning.controller.impl.command.user;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import portal.management.edu.traning.controller.Command;
import portal.management.edu.traning.entity.UserRegistrationInfo;
import portal.management.edu.traning.logic.LogicException;
import portal.management.edu.traning.logic.LogicProvider;
import portal.management.edu.traning.logic.UserLogic;
import portal.management.edu.traning.logic.validation.ValidationResultValues;

import java.io.IOException;
import java.time.LocalDate;

public class UserRegistrationCommand implements Command {

    private final LogicProvider logicProvider = LogicProvider.getInstance();
    private final UserLogic logic = logicProvider.getLogicUser();
    private final ValidationResultValues.Validator validBuild = new ValidationResultValues.Validator();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {

            UserRegistrationInfo user = new UserRegistrationInfo();

            user.setLogin(request.getParameter("username"));
            user.setPassword(request.getParameter("password"));
            user.setName(request.getParameter("name"));
            user.setBirthday(LocalDate.parse(request.getParameter("dob")));
            user.setCountry(request.getParameter("country"));
            user.setRole("user");

            ValidationResultValues validator = validBuild.validLoginPasswordNameCountry(user.getLogin(), user.getPassword(),
                            user.getName(), user.getCountry()).validLogin(user.getLogin()).
                    validPassword(user.getPassword()).validName(user.getName()).build();

            if (validator.isResult()) {

                response.sendRedirect("urlToServlet?command=go_to_registration_page&" +
                        "regError=" + validator.getException());

                return;

            }

            if (logic.registrationUser(user)) {

                response.sendRedirect("urlToServlet?command=go_to_authorization_page&" +
                        "authMessage=Registration completed successfully!");

            } else {

                response.sendRedirect("urlToServlet?command=go_to_registration_page&" +
                        "regError=The login already exists!");

            }


        } catch (LogicException e) {

            response.getWriter().print("<script type='text/javascript'>alert('" + "Registration Error" + "');" +
                    " window.history.back();</script>");


        }

    }

}
