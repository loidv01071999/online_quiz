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

import entity.Answer;
import java.util.List;

/**
 * The class contains method to query data from Answer table. There are
 * addAnswer method to insert new answer, getAnswerByQuestionId method to get
 * answer by question id, getNumberOfTrueAnswerByQuestionId method to get number
 * of true answer of a question by question id. getAnswerById method to get
 * answer by answer id each method will throw an object of
 * <code>java.lang.Exception</code>
 *
 * Bugs: None
 *
 * @author PhatNT
 */
public interface AnswerDAO {

    /**
     * Insert a new answer into database.
     *
     * @param answer is the answer object you want to insert.
     * @throws java.lang.Exception
     */
    public void addAnswer(Answer answer) throws Exception;

    /**
     * Find the answer by question id. The answer have questionId matched will
     * be returned. The result contain a list of answer objects with ID,
     * questionId, answer,isTrue attributes
     *
     * @param quesId is the question id. It is an int
     * @return a list of Answer object.
     * @throws java.lang.Exception
     */
    public List<Answer> getAnswerByQuestionId(int quesId) throws Exception;

    /**
     * Find number of answers returned when selecting answer by questionId. The
     * result contain int number.
     *
     * @param questionId the id of question. It is an int
     * @return number of result. It's an int.
     * @throws java.lang.Exception
     */
    public int getNumberOfTrueAnswerByQuestionId(int questionId) throws Exception;

    /**
     * Find the answer by id. Answer have id matched will be returned The result
     * contain a answer object with ID, questionId, answer,image, isTrue
     * attributes
     *
     * @param answerId the id of digital need search. It is an int.
     * @return a Answer object.
     * @throws java.lang.Exception
     */
    public Answer getAnswerById(int answerId) throws Exception;
}
