package portal.management.edu.traning.controller.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.*;
import portal.management.edu.traning.controller.ConstantCommand;
import portal.management.edu.traning.entity.User;
import portal.management.edu.traning.logic.LogicException;
import portal.management.edu.traning.logic.LogicProvider;
import portal.management.edu.traning.logic.UserLogic;

import java.io.IOException;

@WebFilter(urlPatterns = {"/urlToServlet"})

public class RememberMeFilter extends HttpFilter implements Filter {

    private final LogicProvider logicProvider = LogicProvider.getInstance();
    private final UserLogic logicUser = logicProvider.getLogicUser();

    public RememberMeFilter() {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        try {
            HttpSession session = request.getSession(false);

            if (session == null || session.getAttribute(ConstantCommand.CONSTANT_USER) == null) {

                Cookie[] cookies = request.getCookies();

                if (cookies != null) {

                    for (Cookie c : cookies) {

                        if (c.getName().equals(ConstantCommand.CONSTANT_REMEMBER)) {

                            User user = logicUser.informationViaTokenUser(new User(c.getValue()));

                            if (user != null) {

                                session = request.getSession(true);

                                session.setAttribute(ConstantCommand.CONSTANT_USER, user);

                            }

                        }

                    }

                }

            }

            chain.doFilter(req, res);

        } catch (LogicException e) {

            response.getWriter().print("<script type='text/javascript'>alert('" + "Cookie errors" + "');" +
                    " window.history.back();</script>");

        }

    }

    @Override
    public void destroy() {
    }

    @Override
    public void init(FilterConfig config) throws ServletException {
    }

}
