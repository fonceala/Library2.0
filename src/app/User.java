package app;

import java.util.ArrayList;
import java.util.List;

/**
 * The type User.
 */
public class User {

    /**
     * Manages the proprieties for the user
     * Has functions for transforming the image to byte array and back to file object
     * in order to store the image in the database*/

    private String username;
    private String password;
    private List<String> rentedBooks;

    /**
     * Instantiates a new User.
     *
     * @param username the username
     * @param password the password
     */
    public User(String username,String password){
        this.username = username;
        this.password = password;
        rentedBooks = new ArrayList<>();
    }

    /**
     * Gets username.
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets username.
     *
     * @param username the username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets password.
     *
     * @param password the password
     */
    public void setPassword(String password) {
        this.password = password;
    }


}
