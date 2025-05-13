package filter;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.*;


public class InputFilter implements Filter {
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
        throws IOException, ServletException {

        String username = request.getParameter("username");

        if (username == null || username.trim().isEmpty()) {
            ((HttpServletResponse) response).sendRedirect("login.jsp?error=1");
            return;
        }

        chain.doFilter(request, response);
    }

    public void init(FilterConfig filterConfig) {}
    public void destroy() {}
}
