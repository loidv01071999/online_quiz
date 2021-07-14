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

/**
 * The class define attributes,constructor, getter, setter of answer.
 *
 * @author PhatNT
 */
public class Answer {
    private int id;
    private int qestionId;
    private String answer;
    private boolean isTrue;

    public Answer() {
    }
    /**
     * Constructor with attributes: id, qestionId, answer, isTrue.
     *
     * @param id the id of answer. It is an int
     * @param qestionId the question id of corresponding question. It is an int.
     * @param answer the content of answer. It is a string.
     * @param isTrue the correctness of the answer. It is a boolean.
     */
    public Answer(int id, int qestionId, String answer, boolean isTrue) {
        this.id = id;
        this.qestionId = qestionId;
        this.answer = answer;
        this.isTrue = isTrue;
    }
    /**
     *
     * @return the id of answer. It is an int
     */
    public int getId() {
        return id;
    }
    /**
     *
     * @param id the id of answer. It is an int.
     */
    public void setId(int id) {
        this.id = id;
    }

    
    /**
     *
     * @return the question id of answer. It is an int
     */
    public int getQestionId() {
        return qestionId;
    }
    /**
     *
     * @param qestionId the id of question. It is an int.
     */
    public void setQestionId(int qestionId) {
        this.qestionId = qestionId;
    }
    /**
     *
     * @return the content of answer. It is a string
     */
    public String getAnswer() {
        return answer;
    }
    /**
     *
     * @param answer the content of answer. It is a string.
     */
    public void setAnswer(String answer) {
        this.answer = answer;
    }
    /**
     *
     * @return the correctness of the answer. It is a boolean
     */
    public boolean isIsTrue() {
        return isTrue;
    }
    /**
     *
     * @param isTrue the correctness of the answer. It is an boolean.
     */
    public void setIsTrue(boolean isTrue) {
        this.isTrue = isTrue;
    }
    
    
}
