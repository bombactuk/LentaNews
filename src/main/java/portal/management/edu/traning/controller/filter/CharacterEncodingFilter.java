package portal.management.edu.traning.controller.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import portal.management.edu.traning.controller.ConstantCommand;

import java.io.IOException;

@WebFilter(urlPatterns = {"/urlToServlet"})

public class CharacterEncodingFilter extends HttpFilter implements Filter {

    public CharacterEncodingFilter() {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {

        req.setCharacterEncoding(ConstantCommand.CONSTANT_UTF_8);
        res.setCharacterEncoding(ConstantCommand.CONSTANT_UTF_8);

        chain.doFilter(req, res);

    }

    @Override
    public void init(FilterConfig config) throws ServletException {
    }

    @Override
    public void destroy() {
    }

}
