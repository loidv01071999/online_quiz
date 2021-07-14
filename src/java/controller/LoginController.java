/**
 *Copyright(C) 2021,  FPTU.
 * J3.L.P0001 :
 *  Online Quiz
 *
 * Record of change:
 * DATE                       Version             AUTHOR            DESCRIPTION
 * 2021-06-09                  1.0                PhatNT             Start implement
 * 2021-06-12                  1.0                PhatNT             Test, Comment
 */
package controller;

import dao.UserDAO;
import dao.impl.UserDAOImpl;

import entity.User;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * The class contains method handles the HTTP GET method, handles the HTTP POST
 * method, handles the Request of login page. This class call UserDAO to check
 * login and forward data to "index.jsp" for display. If there is any error
 * occurs, send error message to errorPage.jsp page.
 *
 * <p>
 * Bugs: None
 *
 * @author PhatNT
 */
public class LoginController extends HttpServlet {

    /**
     * Processes requests for HTTP GET to check user is logged in or not. Get
     * the session then check if there are any users in the session. If exists,
     * go to userInfo.jsp page to display, if not, go to login.jsp to login. Use
     * index.jsp to forward results. Throw the ServletException and IOException
     * class if there is any error occurring when finding data or connect
     *
     *
     * @param request is a request from client to server. Stores attributes:
     * component to store the displayed page. It's a
     * <code>javax.servlet.http.HttpServletRequest</code>;
     * @param response is used to encapsulate the data to send back to the
     * client's web browser. It is a
     * <code>javax.servlet.http.HttpServletResponse</code>;
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        User model = (User) request.getSession().getAttribute("user");
        if (model != null) {
            request.setAttribute("component", "userInfo.jsp");
        } else {
            request.setAttribute("component", "login.jsp");

        }
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    /**
     * Processes requests for POST methods to check login information. Use login
     * method to check login information and "index.jsp" to forward results.
     * Throw the ServletException and IOException class if there is any error
     * occurring when finding data or connect
     *
     *
     * @param request is a request from client to server. Stores attributes:
     * component to store the displayed page. If the login information is
     * correct then store that user information into the session. Otherwise,
     * userName is the user name entered, status is the login status . It's a
     * <code>javax.servlet.http.HttpServletRequest</code>;
     * @param response is used to encapsulate the data to send back to the
     * client's web browser. It is a
     * <code>javax.servlet.http.HttpServletResponse</code>;
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userName = request.getParameter("txtUserame").trim();
        String password = request.getParameter("txtPassword").trim();

        try {
            UserDAO userDAO = new UserDAOImpl();
            boolean isBlank = false;
            if (userName.isEmpty()) {
                isBlank = true;
                request.setAttribute("status", "Can not input blank for User Name!");
            } else if (password.isEmpty()) {
                isBlank = true;
                request.setAttribute("status", "Can not input blank for Password!");
            } else {
                User user = userDAO.login(userName, password);
                //Check if the user exists or not
                if (user != null) {
                    HttpSession session = request.getSession();
                    session.setAttribute("user", user);
                    session.setMaxInactiveInterval(600);
                    request.setAttribute("component", "userInfo.jsp");
                } else {
                    request.setAttribute("userName", userName);
                    request.setAttribute("status", "Wrong username or password");
                    request.setAttribute("component", "login.jsp");
                }
            }
            if (isBlank) {
                request.setAttribute("userName", userName);
                request.setAttribute("component", "login.jsp");
            }

            request.getRequestDispatcher("index.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("exception", e.getMessage());
            request.setAttribute("component", "/error/errorPage.jsp");
            request.getRequestDispatcher("index.jsp").forward(request, response);

        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
