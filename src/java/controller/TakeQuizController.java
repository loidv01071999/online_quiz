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
package controller;

import dao.AnswerDAO;
import dao.QuestionDAO;
import dao.impl.AnswerDAOImpl;
import dao.impl.QuestionDAOImpl;
import entity.Question;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * The class contains method handles the HTTP GET method, handles the HTTP POST
 * method, handles the Request of take quiz page. This class call QuestionDAO to
 * get total of question, get random question and call AnswerDAO to get number
 * of true answer by question id, get answer by id and then forward data to
 * "index.jsp" for display. If there is any error occurs, send error message to
 * errorPage.jsp page.
 *
 * <p>
 * Bugs: None
 *
 * @author PhatNT
 */
public class TakeQuizController extends HttpServlet {

    /**
     * Processes requests for HTTP GET to take quiz questions according to the
     * required number. Use getTotalQuestion method to get total of question,
     * getRandomQuestion to get random question according to the require number
     * and then forward data to "index.jsp" for display. Throw the
     * ServletException and IOException class if there is any error occurring
     * when finding data or connect
     *
     *
     * @param request is a request from client to server. Stores attributes:
     * component to store the displayed page, errorTakeQuiz store error massage,
     * quesList store list random questions, realTimeExam store actual exam
     * time. It's a <code>javax.servlet.http.HttpServletRequest</code>;
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
            List<Question> questions = new ArrayList<>();
            String submitTime = (String) session.getAttribute("realSubmitTime");
            if (session.getAttribute("score") != null) {
                submitTime = null;
                session.removeAttribute("score");
            }
            if (submitTime == null) {
                String numberQuestion = request.getParameter("numberQuestion");
                if (numberQuestion == null) {
                    request.setAttribute("component", "takeQuiz/takeQuiz.jsp");

                } else {
                    int totalQuestion = 0;
                    QuestionDAO questionDAO = new QuestionDAOImpl();
                    totalQuestion = questionDAO.getTotalQuestion();
                    //validate number question
                    if (numberQuestion.matches("^\\d+$")) {
                        AnswerDAO answerDAO = new AnswerDAOImpl();
                        int requestQuestion = Integer.parseInt(numberQuestion);
                        //make sure users enter the number of questions that are smaller than the number of existing questions
                        if (requestQuestion > totalQuestion) {
                            request.setAttribute("errorTakeQuiz", "You must enter a number in rage [1," + totalQuestion + "]");
                            request.setAttribute("component", "takeQuiz/takeQuiz.jsp");
                        } else {
                            //allowed time to do quiz
                            int realTimeExam = 10 * requestQuestion;
                            Calendar defaultSubmitTime = Calendar.getInstance();
                            defaultSubmitTime.add(defaultSubmitTime.SECOND, realTimeExam);
                            String defaultSubmitTimeFormat = getDateFormat(defaultSubmitTime);
                            questions = questionDAO.getRandomQuestion(requestQuestion);

                            //set list answer for each question
                            for (Question question : questions) {
                                question.setAnswerList(answerDAO.getAnswerByQuestionId(question.getId()));
                            }
                            session.setAttribute("quesList", questions);
                            session.setAttribute("realSubmitTime", defaultSubmitTimeFormat);
                            request.setAttribute("component", "takeQuiz/doQuiz.jsp");
                        }
                    } else {
                        request.setAttribute("errorTakeQuiz", "You must enter a number in rage [1," + totalQuestion + "]");
                        request.setAttribute("component", "takeQuiz/takeQuiz.jsp");
                    }
                }
            } else {
                request.setAttribute("component", "takeQuiz/doQuiz.jsp");
            }
            request.getRequestDispatcher("index.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("exception", e.getMessage());
            request.setAttribute("component", "error/errorPage.jsp");
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    }

    /**
     * Processes requests for HTTP POST to calculate the score after completing
     * the quiz. Use getNumberOfTrueAnswerByQuestionId method to number of true
     * answer by question id, getAnswerById to get answer by id and then forward
     * data to "index.jsp" for display. Throw the ServletException and
     * IOException class if there is any error occurring when finding data or
     * connect
     *
     *
     * @param request is a request from client to server. Stores attributes:
     * component to store the displayed page, score store total score, percent
     * store percent correct answer. It's a
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
            HttpSession session = request.getSession();
            //list of question id
            String questionIds[] = request.getParameterValues("quenstionId");
            double score = 0;
            //number of question
            double totalQuestion = questionIds.length;

            AnswerDAO answerDAO = new AnswerDAOImpl();
            //number of question choose wrong answer
            double wrongQuestionChooser = 0;
            //count the number of questions that choose the wrong answer
            for (String questionId : questionIds) {
                String answerChooser[] = request.getParameterValues("answer-for-" + questionId);
                //No answer selected
                if (answerChooser == null) {
                    wrongQuestionChooser++;
                    //The number of selected answers is more than the correct number of correct answers
                } else if (answerChooser.length > answerDAO.getNumberOfTrueAnswerByQuestionId(Integer.parseInt(questionId))) {
                    wrongQuestionChooser++;
                    //selected wrong answer
                } else {
                    for (String a : answerChooser) {
                        if (!answerDAO.getAnswerById(Integer.parseInt(a)).isIsTrue()) {
                            wrongQuestionChooser++;
                            break;
                        }
                    }
                }
            }
            score = ((totalQuestion - wrongQuestionChooser) / totalQuestion) * 10;
            NumberFormat formatter = new DecimalFormat("##.#");
            NumberFormat formatPercent = new DecimalFormat("#");
            score = Double.valueOf(formatter.format(score));

            session.removeAttribute("realSubmitTime");
            session.removeAttribute("quesList");
            session.setAttribute("score", score);
            session.setAttribute("percent", formatPercent.format(score * 10));
            request.setAttribute("component", "takeQuiz/showScore.jsp");

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

    public String getDateFormat(Calendar defaultSubmitTime) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String defaultSubmitTimeFormat = sdf.format(defaultSubmitTime.getTime());
        return defaultSubmitTimeFormat;
    }

    public Date convertStringToDate(String date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.parse(date);
    }
}
