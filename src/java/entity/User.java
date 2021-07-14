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
 * The class define attributes,constructor, getter, setter of user.
 *
 * @author PhatNT
 */
public class User {

    private int id;
    private String username;
    private String password;
    private boolean isTeacher;
    private String email;

    public User() {
    }

    /**
     * Constructor with attributes: username, password, isTeacher, email.
     *
     * @param username is user name of user. It is a string
     * @param password is password of user. It is a string.
     * @param isTeacher confirmed as a teacher or not. It is a boolean.
     * @param email is email of user. It is a string.
     */
    public User(String username, String password, boolean isTeacher, String email) {
        this.username = username;
        this.password = password;
        this.isTeacher = isTeacher;
        this.email = email;
    }

    /**
     * Constructor with attributes: username, password, isTeacher, email.
     *
     * @param id the id of user. It is an int
     * @param username the user name of user. It is a string
     * @param password the password of user. It is a string.
     * @param isTeacher confirmed as a teacher or not. It is a boolean.
     * @param email is email of user. It is a string.
     */
    public User(int id, String username, String password, boolean isTeacher, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.isTeacher = isTeacher;
        this.email = email;
    }

    /**
     *
     * @return user name of user. It is a string
     */
    public String getUsername() {
        return username;
    }

    /**
     *
     * @param username is name of user. It is a string
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     *
     * @return password of user. It is a string
     */
    public String getPassword() {
        return password;
    }

    /**
     *
     * @param password is password of user. It is a string.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     *
     * @return confirmed as a teacher or not. It is a boolean.
     */
    public boolean isIsTeacher() {
        return isTeacher;
    }

    /**
     *
     * @param isTeacher is the confirmed as a teacher or not. It is a boolean.
     */
    public void setIsTeacher(boolean isTeacher) {
        this.isTeacher = isTeacher;
    }

    /**
     *
     * @return email of user. It is a string
     */
    public String getEmail() {
        return email;
    }

    /**
     *
     * @param email is email of user. It is a string
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     *
     * @return email of user. It is an int
     */
    public int getId() {
        return id;
    }

    /**
     *
     * @param id is the id of user. It is an int.
     */
    public void setId(int id) {
        this.id = id;
    }

}
