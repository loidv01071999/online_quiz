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
package dao;

import entity.Question;
import java.util.List;

/**
 * The class contains method to query data from question table. There are
 * addQuestion method to insert new question, getQuestionByUserId method to get
 * questions by user id and page index, getTotalQuestion method to get total of
 * question, getTotalQuestionByUserID method to get total of question by user
 * id. getRandomQuestion method to get random question each method will throw an
 * object of <code>java.lang.Exception</code>
 *
 * Bugs: None
 *
 * @author PhatNT
 */
public interface QuestionDAO {

    /**
     * Insert a new question into database.
     *
     * @param question is the content of the question. It's a string
     * @param userId is the id of the question creator. It's an int
     * @return the question id just inserted. It's an int
     * @throws java.lang.Exception
     */
    public int addQuestion(String question, int userId) throws Exception;

    /**
     * Find the question by user id. The question have userId matched will be
     * returned. The result contain a list of question objects with ID,
     * question, dateCreate,userId attributes
     *
     * @param from starting point for taking question. It is an int
     * @param to end point for taking question. It is an int
     * @param userId is the id of the question creator. It is an int
     * @return a list of question object.
     * @throws java.lang.Exception
     */
    public List<Question> getQuestionByUserId(int from, int to, int userId) throws Exception;

    /**
     * Find total of answers returned when selecting question. The result
     * contain int number.
     *
     * @return number of question. It's an int.
     * @throws java.lang.Exception
     */
    public int getTotalQuestion() throws Exception;

    /**
     * Find total of question returned when selecting answer by userId. The
     * result contain int number.
     *
     * @param userId is the id of the question creator. It is an int
     * @return number of question. It's an int.
     * @throws java.lang.Exception
     */
    public int getTotalQuestionByUserID(int userId) throws Exception;

    /**
     * Get random questions with corresponding number. The result contain a list
     * of question objects with ID, question, dateCreate,userId attributes
     *
     * @param numberOfQuestion number of question to do in the quiz. It is an
     * int
     * @return a list of question object.
     * @throws java.lang.Exception
     */
    public List<Question> getRandomQuestion(int numberOfQuestion) throws Exception;
}
