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

import dao.AnswerDAO;
import dao.QuestionDAO;
import dao.impl.AnswerDAOImpl;
import dao.impl.QuestionDAOImpl;
import entity.Answer;
import entity.User;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * The class contains method handles the HTTP GET method, handles the HTTP POST
 * method, handles the Request of make quiz page. This class call QuestionDAO to
 * store question, AnswerDAO to store the answer corresponding to the question
 * and forward data to "index.jsp" for display. If there is any error occurs,
 * send error message to errorPage.jsp page.
 *
 * <p>
 * Bugs: None
 *
 * @author PhatNT
 */
public class MakeQuizController extends HttpServlet {

    /**
     * Processes requests for HTTP GET to check user type in session. Use
     * index.jsp to forward results. Throw the ServletException and IOException
     * class if there is any error occurring when finding data or connect
     *
     *
     * @param request is a request from client to server. Stores attributes:
     * component to store the displayed page. If the user type is not teacher
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
        HttpSession session = request.getSession();
        User model = (User) session.getAttribute("user");
        if (!model.isIsTeacher()) {
            request.setAttribute("noPermiss", true);
        }
        request.setAttribute("component", "makeQuiz.jsp");
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    /**
     * Processes requests for POST methods to store question information. Use
     * addQuestion method to store question, addAnswer to store answer
     * corresponding to the question and then forward data to "index.jsp" for
     * display. Throw the ServletException and IOException class if there is any
     * error occurring when finding data or connect
     *
     *
     * @param request is a request from client to server. Stores attributes:
     * component to store the displayed page, notice store notification message,
     * question store question, answer store list answer. It's a
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
        request.setCharacterEncoding("UTF-8");

        try {
            HttpSession session = request.getSession();
            User model = (User) session.getAttribute("user");
            boolean isBlank = false;
            String question = request.getParameter("question");
            String answers[] = request.getParameterValues("answer");
            String trueAnswers[] = request.getParameterValues("trueAnswer");
            if (question.trim().isEmpty()) {
                isBlank = true;
                question = question.trim();
                request.setAttribute("notice", "Can not input blank in question!");
            }else{
                for (int i = 0; i < answers.length; i++) {
                    if (answers[i].trim().isEmpty()) {
                        isBlank = true;
                        int index = i + 1;
                        request.setAttribute("notice", "Option " + index + " can not input blank!");
                        answers[i] = answers[i].trim();
                        break;
                    }
                }
            }
            if (trueAnswers != null && trueAnswers.length < 4 && !isBlank) {
                QuestionDAO questionDAO = new QuestionDAOImpl();
                // add question
                int questionId = questionDAO.addQuestion(question.trim(), model.getId());

                List<Answer> listAnswer = new ArrayList<>();
                //set false for all answer
                for (int i = 0; i < answers.length; i++) {
                    Answer answer = new Answer();
                    answer.setQestionId(questionId);
                    answer.setAnswer(answers[i].trim());
                    answer.setIsTrue(false);
                    listAnswer.add(answer);
                }
                //Set the correct value for what creators choose
                for (String a : trueAnswers) {
                    listAnswer.get(Integer.parseInt(a)).setIsTrue(true);
                }

                AnswerDAO answerDAO = new AnswerDAOImpl();
                // add all answer into db
                for (Answer answer : listAnswer) {
                    answerDAO.addAnswer(answer);
                }
                request.setAttribute("notice", "Insert question success");
            } else {
                request.setAttribute("question", question);
                request.setAttribute("answer", answers);
                if (trueAnswers == null) {
                    if (!isBlank) {
                        request.setAttribute("notice", "Please choose correct answer.");
                    }
                } else {
                    //returns the value corresponding to each checkbox
                    for (String c : trueAnswers) {
                        request.setAttribute("choice" + Integer.parseInt(c), true);
                    }
                    if (!isBlank) {
                        request.setAttribute("notice", "You can only choose from 1 to 3 correct answers");
                    }
                }
            }

            request.setAttribute("component", "makeQuiz.jsp");
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
