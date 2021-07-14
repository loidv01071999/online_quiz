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
import dao.QuestionDAO;
import entity.Question;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.naming.NamingException;

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
public class QuestionDAOImpl extends DBContext implements QuestionDAO {

    /**
     * Default constructor to throws NamingException when extends DBContext
     *
     * @throws javax.naming.NamingException
     */
    public QuestionDAOImpl() throws NamingException {
        super();

    }

    /**
     * Insert a new question into database.
     *
     * @param question is the content of the question. It's a string
     * @param userId is the id of the question creator. It's an int
     * @return the question id just inserted. It's an int
     * @throws java.lang.Exception
     */
    @Override
    public int addQuestion(String question, int userId) throws Exception {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String sql = "insert into [question]([question], [dateCreate], [userId]) values(?, ?, ?);";
            Date utilDate = new Date();
            Integer id = null;
            conn = getConnection();
            conn.setAutoCommit(false);
            ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setObject(1, question);
            ps.setObject(2, new java.sql.Date(utilDate.getTime()));
            ps.setObject(3, userId);
            ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            while (rs.next()) {
                id = rs.getInt(1);
            }
            conn.commit();
            return id;
        } catch (SQLException e) {
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException e2) {
                    throw e2;
                }
            }
            throw e;

        } finally {
            closeResultSet(rs);
            closePreparedStatement(ps);
            closeConnection(conn);
        }
    }

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
    @Override
    public List<Question> getQuestionByUserId(int from, int to, int userId) throws Exception {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Question> list = new ArrayList<Question>();

        try {
            String sql = "SELECT * FROM ( SELECT ROW_NUMBER()OVER(ORDER BY id) "
                    + "as Number, * FROM question where userId = ?  ) "
                    + "as DataSearch where Number between ? and ?";
            conn = getConnection();
            ps = conn.prepareStatement(sql);
            ps.setObject(1, userId);
            ps.setObject(2, from);
            ps.setObject(3, to);
            rs = ps.executeQuery();
            while (rs.next()) {
                Question q = new Question();
                q.setId(rs.getInt("id"));
                q.setQuestion(rs.getString("question"));
                q.setDateCreate(rs.getDate("dateCreate"));
                q.setUserId(rs.getInt("userId"));
                list.add(q);
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
     * Find total of answers returned when selecting question. The result
     * contain int number.
     *
     * @return number of question. It's an int.
     * @throws java.lang.Exception
     */
    @Override
    public int getTotalQuestion() throws Exception {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int count = 0;

        try {
            String sql = "SELECT COUNT(id) as total FROM question";
            conn = getConnection();
            ps = conn.prepareStatement(sql);
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
     * Find total of question returned when selecting answer by userId. The
     * result contain int number.
     *
     * @param userId is the id of the question creator. It is an int
     * @return number of question. It's an int.
     * @throws java.lang.Exception
     */
    @Override
    public int getTotalQuestionByUserID(int userId) throws Exception {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int count = 0;

        try {
            String sql = "SELECT COUNT(id) as total FROM question where userId = ?";
            conn = getConnection();
            ps = conn.prepareStatement(sql);
            ps.setObject(1, userId);
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
     * Get random questions with corresponding number. The result contain a list
     * of question objects with ID, question, dateCreate,userId attributes
     *
     * @param numberOfQuestion number of question to do in the quiz. It is an
     * int
     * @return a list of question object.
     * @throws java.lang.Exception
     */
    @Override
    public List<Question> getRandomQuestion(int numberOfQuestion) throws Exception {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Question> list = new ArrayList<Question>();

        try {
            String sql = "SELECT TOP (?)  *  FROM [question] ORDER BY NEWID()";
            conn = getConnection();
            ps = conn.prepareStatement(sql);
            ps.setObject(1, numberOfQuestion);
            rs = ps.executeQuery();
            while (rs.next()) {
                Question q = new Question();
                q.setId(rs.getInt("id"));
                q.setQuestion(rs.getString("question"));
                q.setDateCreate(rs.getDate("dateCreate"));
                q.setUserId(rs.getInt("userId"));
                list.add(q);
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
}
