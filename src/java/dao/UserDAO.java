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

import entity.User;

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
public interface UserDAO {

    /**
     * Check user already exists. The user have username matched will be return
     * true.
     *
     * @param username is username information need insert. It's a user object
     * @return status check exist. It's a boolean
     * @throws java.lang.Exception
     */
    public boolean checkExitUser(String username) throws Exception;

    /**
     * Insert a new user into database.
     *
     * @param user is user information need insert. It's a user object
     * @throws java.lang.Exception
     */
    public void registerUser(User user) throws Exception;

    /**
     * Find the user by username and password. The user have username and
     * password matched will be returned
     *
     * @param username is username use to login. It's a string
     * @param password is password use to login. It's a string
     * @return a user object.
     * @throws java.lang.Exception
     */
    public User login(String username, String password) throws Exception;
}
