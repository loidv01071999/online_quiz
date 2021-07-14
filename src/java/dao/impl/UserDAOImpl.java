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
import dao.UserDAO;
import entity.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;

/**
 * The class contains method to query data from user table. There are
 * checkExitUser method to check user already exists, registerUser method to
 * insert a new user, login method to check login information each method will
 * throw an object of <code>java.lang.Exception</code>
 *
 * Bugs: None
 *
 * @author PhatNT
 */
public class UserDAOImpl extends DBContext implements UserDAO {

    /**
     * Default constructor to throws NamingException when extends DBContext
     *
     * @throws javax.naming.NamingException
     */
    public UserDAOImpl() throws NamingException {
        super();
    }

    /**
     * Check user already exists. The user have username matched will be return
     * true.
     *
     * @param username is username information need insert. It's a user object
     * @return status check exist. It's a boolean
     * @throws java.lang.Exception
     */
    @Override
    public boolean checkExitUser(String username) throws Exception {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String sql = "SELECT username FROM  [user] where username =?";
            conn = getConnection();
            ps = conn.prepareStatement(sql);
            ps.setObject(1, username);
            rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            throw e;
        } finally {
            closeResultSet(rs);
            closePreparedStatement(ps);
            closeConnection(conn);
        }
        return false;
    }

    /**
     * Insert a new user into database.
     *
     * @param user is user information need insert. It's a user object
     * @throws java.lang.Exception
     */
    @Override
    public void registerUser(User user) throws Exception {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            String sql = "INSERT INTO [user]([username],[password],[isTeacher],[email]) "
                    + "VALUES  (?,?,?,?)";
            conn = getConnection();
            conn.setAutoCommit(false);
            ps = conn.prepareStatement(sql);
            ps.setObject(1, user.getUsername());
            ps.setObject(2, user.getPassword());
            ps.setObject(3, user.isIsTeacher());
            ps.setObject(4, user.getEmail());
            ps.executeUpdate();
            conn.commit();
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
            closePreparedStatement(ps);
            closeConnection(conn);
        }

    }

    /**
     * Find the user by username and password. The user have username and
     * password matched will be returned
     *
     * @param username is username use to login. It's a string
     * @param password is password use to login. It's a string
     * @return a user object.
     * @throws java.lang.Exception
     */
    @Override
    public User login(String username, String password) throws Exception {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String sql = " SELECT *  FROM [user]  where [username]= ? and [password] =?";
            conn = getConnection();
            ps = conn.prepareStatement(sql);
            ps.setObject(1, username);
            ps.setObject(2, password);
            rs = ps.executeQuery();
            if (rs.next()) {
                User us = new User();
                us.setId(rs.getInt("id"));
                us.setUsername(rs.getString("username"));
                us.setPassword(rs.getString("password"));
                us.setIsTeacher(rs.getBoolean("isTeacher"));
                us.setEmail(rs.getString("email"));
                return us;
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
