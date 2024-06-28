package portal.management.edu.traning.controller.impl.command.user;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import portal.management.edu.traning.controller.Command;
import portal.management.edu.traning.controller.ConstantCommand;
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

            user.setLogin(request.getParameter(ConstantCommand.CONSTANT_COLUMN_USERNAME));
            user.setPassword(request.getParameter(ConstantCommand.CONSTANT_COLUMN_PASSWORD));
            user.setName(request.getParameter(ConstantCommand.CONSTANT_COLUMN_NAME));
            user.setBirthday(LocalDate.parse(request.getParameter(ConstantCommand.CONSTANT_COLUMN_DOB)));
            user.setCountry(request.getParameter(ConstantCommand.CONSTANT_COLUMN_COUNTRY));
            user.setRole(ConstantCommand.CONSTANT_USER);

            ValidationResultValues validator = validBuild.validLoginPasswordNameCountry(user.getLogin(), user.getPassword(),
                            user.getName(), user.getCountry()).validLogin(user.getLogin()).
                    validPassword(user.getPassword()).validName(user.getName()).build();

            if (validator.isResult()) {

                response.sendRedirect(ConstantCommand.CONSTANT_COMMAND_GO_TO_REGISTRATION_PAGE +
                        "&regError=" + validator.getException());

                return;

            }

            if (logic.registrationUser(user)) {

                response.sendRedirect(ConstantCommand.CONSTANT_COMMAND_GO_TO_AUTHORIZATION_PAGE +
                        "&authMessage=110");

            } else {

                response.sendRedirect(ConstantCommand.CONSTANT_COMMAND_GO_TO_REGISTRATION_PAGE +
                        "&regError=123");

            }


        } catch (LogicException e) {

            response.getWriter().print("<script type='text/javascript'>alert('" + "Registration Error" + "');" +
                    " window.history.back();</script>");


        }

    }

}
