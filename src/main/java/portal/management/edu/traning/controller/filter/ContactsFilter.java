package portal.management.edu.traning.controller.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.*;
import portal.management.edu.traning.entity.ContactCommunication;
import portal.management.edu.traning.logic.InformationLogic;
import portal.management.edu.traning.logic.LogicException;
import portal.management.edu.traning.logic.LogicProvider;

import java.io.IOException;
import java.util.List;

@WebFilter(urlPatterns = {"/urlToServlet"})

public class ContactsFilter extends HttpFilter implements Filter {

    private final LogicProvider logicProvider = LogicProvider.getInstance();
    private final InformationLogic infoLogic = logicProvider.getLogicInfo();

    public ContactsFilter() {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        try {
            HttpSession session = request.getSession(false);

            if (session == null || session.getAttribute("contacts") == null) {

                List<ContactCommunication> contacts = infoLogic.allConnectionsWithUs();

                if (contacts != null) {

                    session = request.getSession(true);

                    session.setAttribute("contacts", contacts);

                }

            }

        } catch (LogicException e) {

            response.getWriter().print("<script type='text/javascript'>alert('" + "Contacts Error" + "');" +
                    " window.history.back();</script>");

        }

        chain.doFilter(req, res);

    }

    @Override
    public void destroy() {
    }

    @Override
    public void init(FilterConfig config) throws ServletException {
    }
}
