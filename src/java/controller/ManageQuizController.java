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

import dao.QuestionDAO;
import dao.impl.QuestionDAOImpl;
import entity.Question;
import entity.User;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * The class contains method handles the HTTP GET method, handles the HTTP POST
 * method, handles the Request of manager quiz page. This class call QuestionDAO
 * to get all question by user id and paging and then forward data to
 * "index.jsp" for display. If there is any error occurs, send error message to
 * errorPage.jsp page.
 *
 * <p>
 * Bugs: None
 *
 * @author PhatNT
 */
public class ManageQuizController extends HttpServlet {

    /**
     * Processes requests for GET methods to get all question by user id. Use
     * getQuestion method to get all question by user id and then forward data
     * to "index.jsp" for display. Throw the ServletException and IOException
     * class if there is any error occurring when finding data or connect
     *
     *
     * @param request is a request from client to server. Stores attributes:
     * component to store the displayed page, listResult store list of
     * questions, question store question, totalPage store total of page,
     * currentPage store index of current page. If the user type is not teacher
     * then noPermission to notify the user does not have permission. It's a
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
        try {
            HttpSession session = request.getSession();
            User model = (User) session.getAttribute("user");
            if (!model.isIsTeacher()) {
                request.setAttribute("noPermiss", true);
            } else {

                int pageSize = 3;

                String pageRequest = request.getParameter("page");
                int page = 1;
                if (pageRequest != null) {
                    try {
                        page = Integer.parseInt(pageRequest);
                    } catch (Exception e) {
                        request.setAttribute("errorManage", e.getMessage());
                    }

                }
                QuestionDAO questionDAO = new QuestionDAOImpl();
                int numOfQuest = questionDAO.getTotalQuestionByUserID(model.getId());
                //number of pages 
                if (numOfQuest != 0) {
                    int totalPage = (int) Math.ceil((double) numOfQuest / pageSize);
                    //Check if the current page is larger than the largest
                    if (page <= totalPage) {

                        int startItem = (page - 1) * pageSize + 1;
                        List<Question> listResult = questionDAO.getQuestionByUserId(startItem, page * pageSize, model.getId());
                        request.setAttribute("listResult", listResult);
                        request.setAttribute("totalPage", totalPage);
                        request.setAttribute("currentPage", page);
                    } else {
                        request.setAttribute("errorManage", "Page position exceeds page number");
                    }
                }else{
                    request.setAttribute("noResultMessage", "You don't have any questions yet");
                }

                request.setAttribute("numberOfQuestion", numOfQuest);
            }

            request.setAttribute("component", "manageQuiz.jsp");
            request.getRequestDispatcher("index.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("exception", e.getMessage());
            request.setAttribute("component", "error/errorPage.jsp");
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    }

    /**
     * Processes requests for POST methods. Throw the ServletException and
     * IOException class if there is any error occurring when finding data or
     * connect
     *
     *
     * @param request is a request from client to server.. It's a
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
