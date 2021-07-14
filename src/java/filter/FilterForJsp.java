/**
 *Copyright(C) 2021,  FPTU.
 * J3.L.P0001 :
 *  Online Quiz
 *
 * Record of change:
 * DATE                       Version             AUTHOR            DESCRIPTION
 * 2021-06-09                  1.0                PhatNT             Start implement
 * 2021-06-18                  1.0                PhatNT             Test, Comment
 */
package filter;

import entity.User;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * The class contain doFilter method to perform pre-processing (incoming
 * request) and post-processing (returning response) for a request to a JSP.
 *
 * Bugs: None
 *
 * @author PhatNT
 */
public class FilterForJsp implements Filter {

    /**
     * Perform pre-processing (incoming request) and post-processing (returning
     * response) for a request to a JSP. Throw the ServletException and
     * IOException class if there is any error occurring when finding data or
     * connect
     *
     * @param request is a request from client to server. It's a
     * <code>javax.servlet.ServletRequest</code>;
     * @param response is used to encapsulate the data to send back to the
     * client's web browser. It is a
     * <code>javax.servlet.ServletResponse</code>;
     * @param chain invoke the next filter in the chain, or if the calling
     * filter is the last filter in the chain, to invoke the resource at the end
     * of the chain. It's a <code>javax.servlet.FilterChain</code>;
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        if (req.getRequestURI().endsWith(".jsp")) {
            HttpSession session = req.getSession();
            User model = (User) session.getAttribute("user");
            if (req.getRequestURI().endsWith("register.jsp")) {
                res.sendRedirect(req.getContextPath() + "/Register");
            } else if (model != null) {
                if (req.getRequestURI().endsWith("makeQuiz.jsp")) {
                    res.sendRedirect(req.getContextPath() + "/MakeQuiz");
                } else if (req.getRequestURI().endsWith("manageQuiz.jsp")) {
                    res.sendRedirect(req.getContextPath() + "/ManageQuiz");
                } else if (req.getRequestURI().endsWith("takeQuiz.jsp")) {
                    res.sendRedirect(req.getContextPath() + "/TakeQuiz");
                }

            } else {
                res.sendRedirect(req.getContextPath() + "/Login");
            }
        } else {
            chain.doFilter(req, res);
        }
    }

    @Override
    public void destroy() {
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

}
