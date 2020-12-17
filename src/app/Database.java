package app;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.sql.*;
import java.util.ArrayList;

/**
 * The type Database.
 */
public class Database {

    /**
     * class that manipulates all  the database functions */
    private Connection connect() {

        String url = "jdbc:sqlite:./src/db/users.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    /**
     * Create db.
     *
     * @throws ClassNotFoundException the class not found exception
     */
    public void createDb() throws ClassNotFoundException {
        String url = "jdbc:sqlite:./src/db/users.db";
        Class.forName("org.sqlite.JDBC");
        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("The driver name is " + meta.getDriverName());
                System.out.println("A new db has been created.");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Create ut.
     */
    public void createUT() {
        String url = "jdbc:sqlite:./src/db/users.db";


        String sql = "CREATE TABLE IF NOT EXISTS users(\n"
                + "	id integer PRIMARY KEY AUTOINCREMENT,\n"
                + "	Username text NOT NULL,\n"
                + "	Password text NOT NULL\n"
                + ");";

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Create bt.
     */
    public void createBT() {
        String url = "jdbc:sqlite:./src/db/users.db";


        String sql = "CREATE TABLE IF NOT EXISTS books(\n"
                + "	id integer PRIMARY KEY AUTOINCREMENT,\n"
                + "	Title text NOT NULL,\n"
                + "	Author text NOT NULL,\n"
                + "	Editor text NOT NULL,\n"
                + "	Description text NOT NULL,\n"
                + "	Price integer,\n"
                + "	Year integer,\n"
                + "	ISBN text NOT NULL,\n"
                + "	Image BLOB\n"
                + ");";

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Check cred boolean.
     *
     * @param username the username
     * @param password the password
     * @return the boolean
     */
    public boolean checkCred(String username, String password){

        boolean itExists = false;

        try{
            Connection conn = this.connect();
            String query = "select * from users where Username='"+username+"' and Password='"+password+"'";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();

            if(rs.next()){
                itExists = true;
            }
            rs.close();
            preparedStatement.close();
            conn.close();
        }catch (Exception e){
            e.printStackTrace();
        }

        return itExists;
    }

    /**
     * Check usn boolean.
     *
     * @param username the username
     * @return the boolean
     */
    public boolean checkUsn(String username){
        boolean itExists = false;

        try{
            Connection conn = this.connect();
            String query = "select * from users where Username='"+username+"'";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();

            if(rs.next()){
                itExists = true;
            }
            preparedStatement.close();
            rs.close();
            conn.close();
        }catch (Exception e){
            e.printStackTrace();
        }


        return itExists;
    }

    /**
     * Insert usr.
     *
     * @param username the username
     * @param password the password
     */
    public void insertUsr(String username, String password){
        final String SQL = "INSERT INTO users VALUES($next_id,?,?)";
        try (Connection con = this.connect();
            PreparedStatement ps = con.prepareStatement(SQL);) {
            ps.setString(2, username);
            ps.setString(3, password);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /**
     * Insert book.
     *
     * @param title       the title
     * @param author      the author
     * @param editura     the editura
     * @param description the description
     * @param isbn        the isbn
     * @param price       the price
     * @param year        the year
     * @param image       the image
     */
    public void insertBook(String title, String author, String editura, String description,String isbn, int price, int year, byte[] image){
        final String SQL = "INSERT INTO books VALUES($next_id,?,?,?,?,?,?,?,?)";
        try (Connection con = this.connect();
             PreparedStatement ps = con.prepareStatement(SQL);) {
            ps.setString(2, title);
            ps.setString(3, author);
            ps.setString(4, editura);
            ps.setString(5, description);
            ps.setInt(6, price);
            ps.setInt(7, year);
            ps.setString(8,isbn);
            ps.setBytes(9,image);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /**
     * Check book boolean.
     *
     * @param title the title
     * @return the boolean
     */
    public boolean checkBook(String title){
        boolean itExists = false;

        try{
            Connection conn = this.connect();
            String query = "select * from books where Title='"+title+"'";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();

            if(rs.next()){
                itExists = true;
            }
            preparedStatement.close();
            rs.close();
            conn.close();
        }catch (Exception e){
            e.printStackTrace();
        }


        return itExists;
    }

    /**
     * Create rt.
     */
    public void createRT() {
        String url = "jdbc:sqlite:./src/db/users.db";


        String sql = "CREATE TABLE IF NOT EXISTS rent(\n"
                + "	id integer PRIMARY KEY AUTOINCREMENT,\n"
                + "	User text NOT NULL,\n"
                + "	Title text NOT NULL\n"
                + ");";

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Insert rent.
     *
     * @param username the username
     * @param title    the title
     */
    public void insertRent(String username, String title){
        final String SQL = "INSERT INTO rent VALUES($next_id,?,?)";
        try (Connection con = this.connect();
             PreparedStatement ps = con.prepareStatement(SQL);) {
            ps.setString(2, username);
            ps.setString(3, title);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /**
     * Get book array list.
     *
     * @param username the username
     * @return the array list
     */
    public ArrayList<String> getBook(String username){
        ArrayList<String> resultArray = new ArrayList<>();
        try{
            Connection conn = this.connect();
            String query1 = "select * from rent where User='"+username+"'";
            PreparedStatement preparedStatement = conn.prepareStatement(query1);
            ResultSet rs = preparedStatement.executeQuery();

            while(rs.next()){
                resultArray.add(rs.getString("Title"));
            }
            preparedStatement.close();
            rs.close();
            conn.close();
        }catch (Exception e){
            e.printStackTrace();
        }

        return resultArray;
    }

    /**
     * Check rent boolean.
     *
     * @param title the title
     * @param user  the user
     * @return the boolean
     */
    public boolean checkRent(String title,String user){
        boolean itExists = false;

        try{
            Connection conn = this.connect();
            String query = "select * from rent where Title='"+title+"' and User='"+user+"'";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();

            if(rs.next()){
                itExists = true;
            }
            preparedStatement.close();
            rs.close();
            conn.close();
        }catch (Exception e){
            e.printStackTrace();
        }


        return itExists;
    }

    /**
     * Get by array list.
     *
     * @param choice the choice
     * @param value  the value
     * @return the array list
     */
    public ArrayList<Book> getBy(String choice, String value){
        ArrayList<Book> books = new ArrayList<>();
        Book b;

        try{
            Connection conn = this.connect();
            String query1 = "select * from books where "+choice+" like "+"'%"+value+"%'";
            PreparedStatement preparedStatement = conn.prepareStatement(query1);
            ResultSet rs = preparedStatement.executeQuery();

            while(rs.next()){
                b = new Book(rs.getString("Title"),
                             rs.getString("Author"),
                             rs.getInt("Price"),
                             rs.getInt("Year"),
                             rs.getString("Editor"),
                             rs.getString("Description"),
                             rs.getString("ISBN"),
                             read(rs.getBytes("Image")));
                books.add(b);
            }
            preparedStatement.close();
            rs.close();
            conn.close();
        }catch (Exception e){
            e.printStackTrace();
        }

        return books;
    }

    /**
     * Read file.
     *
     * @param data the data
     * @return the file
     */
    public File read(byte[] data) {
        try {


            ByteArrayInputStream baip = new ByteArrayInputStream(data);
            ObjectInputStream ois = new ObjectInputStream(baip);
            File dataobj = (File ) ois.readObject();
            return dataobj ;

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
