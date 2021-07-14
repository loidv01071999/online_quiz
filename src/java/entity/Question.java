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
package entity;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * The class define attributes,constructor, getter, setter of question.
 * getDateFormat to format create date
 *
 * @author PhatNT
 */
public class Question {

    private int id;
    private String question;
    private Date dateCreate;
    private List<Answer> answerList;
    private int userId;

    public Question() {
    }

    /**
     * Constructor with attributes: id, question, dateCreate, answerList,
     * userId.
     *
     * @param id the id of the question. It is an int
     * @param question the content of the question. It is a string.
     * @param dateCreate question creation date. It is a
     * <code>java.sql.Date</code> object.
     * @param answerList list of answers object. It is a
     * <code>java.util.List</code> object.
     * @param userId the id of the question creator. It is an int.
     */
    public Question(int id, String question, Date dateCreate, List<Answer> answerList, int userId) {
        this.id = id;
        this.question = question;
        this.dateCreate = dateCreate;
        this.answerList = answerList;
        this.userId = userId;
    }

    /**
     *
     * @return question creation date. It is a <code>java.sql.Date</code>
     * object.
     */
    public Date getDateCreate() {
        return dateCreate;
    }

    /**
     *
     * @param dateCreate question creation date. It is a
     * <code>java.sql.Date</code> object.
     */
    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }

    /**
     *
     * @return list of answers object. It is a <code>java.util.List</code>
     * object.
     */
    public List<Answer> getAnswerList() {
        return answerList;
    }

    /**
     *
     * @param answerList list of answers object. It is a
     * <code>java.util.List</code> object.
     */
    public void setAnswerList(List<Answer> answerList) {
        this.answerList = answerList;
    }

    /**
     *
     * @return the id of question. It is an int
     */
    public int getId() {
        return id;
    }

    /**
     *
     * @param id the id of question. It is an int.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     *
     * @return the content of the question. It is a string.
     */
    public String getQuestion() {
        return question;
    }

    /**
     *
     * @param question the content of the question. It is a string.
     */
    public void setQuestion(String question) {
        this.question = question;
    }

    /**
     *
     * @return the id of the question creator. It is an int.
     */
    public int getUserId() {
        return userId;
    }

    /**
     *
     * @param userId the id of the question creator. It is an int.
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * Format date create questions by day - month - year
     *
     * @return the date create. It is a string
     */
    public String getDateFormat() {
        return new SimpleDateFormat("dd-MMM-yyyy").format(this.dateCreate);
    }

}
