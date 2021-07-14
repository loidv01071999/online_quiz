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
package dao.impl;

import context.DBContext;
import dao.AnswerDAO;
import entity.Answer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;

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
public class AnswerDAOImpl extends DBContext implements AnswerDAO {

    /**
     * Default constructor to throws NamingException when extends DBContext
     *
     * @throws javax.naming.NamingException
     */
    public AnswerDAOImpl() throws NamingException {
        super();
    }

    /**
     * Insert a new answer into database.
     *
     * @param answer is the answer object you want to insert.
     * @throws java.lang.Exception
     */
    @Override
    public void addAnswer(Answer answer) throws Exception {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            String sql = "insert into answer([questionId], answer, isTrue) values(?, ?, ?);";
            Integer id = null;
            conn = getConnection();
            conn.setAutoCommit(false);
            ps = conn.prepareStatement(sql);
            ps.setObject(1, answer.getQestionId());
            ps.setObject(2, answer.getAnswer());
            ps.setObject(3, answer.isIsTrue());
            ps.executeUpdate();
            conn.commit();
        } catch (Exception e) {
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException e2) {
                    throw e2;
                }
            }
            throw e;

        } finally {
            closePreparedStatement(ps);
            closeConnection(conn);
        }
    }

    /**
     * Find the answer by question id. The answer have questionId matched will
     * be returned. The result contain a list of answer objects with ID,
     * questionId, answer,isTrue attributes
     *
     * @param quesId is the question id. It is an int
     * @return a list of Answer object.
     * @throws java.lang.Exception
     */
    @Override
    public List<Answer> getAnswerByQuestionId(int quesId) throws Exception {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Answer> list = new ArrayList<Answer>();

        try {
            String sql = "SELECT  *   FROM [answer] where questionId = ?";
            conn = getConnection();
            ps = conn.prepareStatement(sql);
            ps.setObject(1, quesId);
            rs = ps.executeQuery();
            while (rs.next()) {
                Answer as = new Answer();
                as.setId(rs.getInt("id"));
                as.setQestionId(rs.getInt("questionId"));
                as.setAnswer(rs.getString("answer"));
                as.setIsTrue(rs.getBoolean("isTrue"));
                list.add(as);
            }
            return list;
        } catch (Exception e) {
            throw e;
        } finally {
            closeResultSet(rs);
            closePreparedStatement(ps);
            closeConnection(conn);
        }
    }

    /**
     * Find number of answers returned when selecting answer by questionId. The
     * result contain int number.
     *
     * @param questionId the id of question. It is an int
     * @return number of result. It's an int.
     * @throws java.lang.Exception
     */
    @Override
    public int getNumberOfTrueAnswerByQuestionId(int questionId) throws Exception {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int count = 0;

        try {
            String sql = "select count (*) from answer where questionId = ? and isTrue = 1;";
            conn = getConnection();
            ps = conn.prepareStatement(sql);
            ps.setObject(1, questionId);
            rs = ps.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            closeResultSet(rs);
            closePreparedStatement(ps);
            closeConnection(conn);
        }
        return count;
    }

    /**
     * Find the answer by id. Answer have id matched will be returned The result
     * contain a answer object with ID, questionId, answer,image, isTrue
     * attributes
     *
     * @param answerId the id of digital need search. It is an int.
     * @return a Answer object.
     * @throws java.lang.Exception
     */
    @Override
    public Answer getAnswerById(int answerId) throws Exception {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String sql = "select * from answer where id = ?;";
            conn = getConnection();
            ps = conn.prepareStatement(sql);
            ps.setObject(1, answerId);
            rs = ps.executeQuery();
            if (rs.next()) {
                Answer as = new Answer();
                as.setId(rs.getInt("id"));
                as.setQestionId(rs.getInt("questionId"));
                as.setAnswer(rs.getString("answer"));
                as.setIsTrue(rs.getBoolean("isTrue"));
                return as;
            }
        } catch (Exception e) {
            throw e;
        } finally {
            closeResultSet(rs);
            closePreparedStatement(ps);
            closeConnection(conn);
        }

        return null;

    }

}
