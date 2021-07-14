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

/**
 * The class contains method handles the HTTP GET method, handles the HTTP POST
 * method, handles the Request of register page. This class call UserDAO to
 * check user already exists and register new user and then forward data to
 * "index.jsp" for display. If there is any error occurs, send error message to
 * errorPage.jsp page.
 *
 * <p>
 * Bugs: None
 *
 * @author PhatNT
 */
public class RegisterController extends HttpServlet {

    /**
     * Processes requests for GET methods. Throw the ServletException and
     * IOException class if there is any error occurring when finding data or
     * connect
     *
     *
     * @param request is a request from client to server. It's a
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
        request.setAttribute("component", "register.jsp");
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    /**
     * Processes requests for POST methods to register user. Use checkExitUser
     * method to check user already exists, registerUser to store user into
     * database and then forward data to "index.jsp" for display. Throw the
     * ServletException and IOException class if there is any error occurring
     * when finding data or connect
     *
     *
     * @param request is a request from client to server. Stores attributes:
     * component to store the displayed page, status store notification message,
     * userName store user name, email store email. It's a
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
        response.setContentType("text/html;charset=UTF-8");
        try {
            String userName = request.getParameter("txtName").trim();
            String password = request.getParameter("txtPassword").trim();
            String userType = request.getParameter("user-type");
            String email = request.getParameter("txtEmail").trim();
            boolean isBlank = false;
            boolean isExisted = false;
            boolean isTeacher = false;
            //Check if the user has selected the teacher or not
            if (userType.equals("Teacher")) {
                isTeacher = true;
            }
            UserDAO userDAO = new UserDAOImpl();
            if (userName.isEmpty()) {
                isBlank = true;
                request.setAttribute("status", "Can not input blank for User Name!");
            } else if (password.isEmpty()) {
                isBlank = true;
                request.setAttribute("status", "Can not input blank for Password!");
            } else if (email.isEmpty()) {
                isBlank = true;
                request.setAttribute("status", "Can not input blank for Email!");
            } //Check if the username already exists or not
            else if (userDAO.checkExitUser(userName)) {
                isExisted = true;
                request.setAttribute("status", "Username existed. Try again!");
            } else {
                User user = new User(userName, password, isTeacher, email);
                userDAO.registerUser(user);
                request.setAttribute("status", "Register successfully");
            }
            
            if(isBlank || isExisted){
                request.setAttribute("userName", userName);
                request.setAttribute("email", email);
                if (isTeacher) {
                    request.setAttribute("isTeacher", true);
                } else {
                    request.setAttribute("isStudent", true);
                }
            }

            request.setAttribute("component", "register.jsp");
            request.getRequestDispatcher("index.jsp").forward(request, response);

        } catch (Exception e) {
            request.setAttribute("exception", e.getMessage());
            request.setAttribute("component", "error/errorPage.jsp");
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
