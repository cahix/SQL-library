package data;

import model.Book;

import java.sql.*;
import java.util.ArrayList;

public class DatabaseHandler {

    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL      = "jdbc:mysql://localhost/library?serverTimezone=UTC";

    static final String USER = "admin";
    static final String PASS = "admin";

    Connection con = null;
    Statement stmt = null;
    ResultSet rs   = null;

    //get SQL content
    private ResultSet makeResultset(String SQLquery){
        try {
            Class.forName(JDBC_DRIVER);
            con = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = con.createStatement();
            rs = stmt.executeQuery(SQLquery);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return rs;
    }

    //write to database
    private void makeUpdate(String SQLquery){
        try {
            Class.forName(JDBC_DRIVER);
            con = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = con.createStatement();
            stmt.executeUpdate(SQLquery);
        } catch (Exception se){
            se.printStackTrace();
        } finally {
            closeConnections();
        }
    }

    public boolean isValidUserLogin(String userName, String userPassword){
        boolean isValidUser = false;

        String query = "SELECT * FROM users WHERE name = \"" + userName + "\" AND password = \"" + userPassword + "\"";
        rs = makeResultset(query);

        try {
            if (rs.next()) {
                isValidUser = true;
            }
        } catch (Exception se){
            se.printStackTrace();
        } finally {
            closeConnections();
        }
        return isValidUser;
    }

    public boolean databaseContainsISBN(String isbn){
        boolean DBcontainsISBN = false;
        String query = "SELECT * FROM books WHERE isbn = \"" + isbn + "\"";
        rs = makeResultset(query);
        try {
            if (rs.next()) {
                DBcontainsISBN = true;
            }
        } catch (Exception se){
            se.printStackTrace();
        } finally {
            closeConnections();
        }
        return DBcontainsISBN;
    }

    //check if book matches database format (it is not very strict)
    public boolean isValidBook(String name, String autor, String published, String isbn){
        return(name != null && name.length() <= 30 && autor != null && autor.length() <= 30 &&
                published != null && published.matches("[0-9]+") && published.length() == 4 &&
                  isbn != null && isbn.matches("[0-9,-]+") && !databaseContainsISBN(isbn));
    }

    //load books table
    public ArrayList<Book> getAllBooks(){
        ArrayList<Book> bookList = new ArrayList<>();
        ResultSet rs = makeResultset("SELECT * FROM books");
        try {
            while (rs.next()) {
                String name = rs.getString("name");
                String autor = rs.getString("autor");
                String published = rs.getString("published");
                String isbn = rs.getString("isbn");
                Book book = new Book(name,autor,published,isbn);
                book.setStatus(rs.getString("status"));
                bookList.add(book);
            }
        } catch (SQLException se){
            se.printStackTrace();
        } finally {
            closeConnections();
        }
        return bookList;
    }

    public void setBookStatus(String isbn, String status){
        String sql = "UPDATE books SET status = \"" + status +  "\" WHERE (isbn = \"" + isbn + "\")";
        makeUpdate(sql);
    }

    //add book to database
    public void addBook(String name, String autor, String published, String isbn){
        String status = "Available"; // per default
        String sql = "INSERT INTO books (isbn,name,autor,published,status) VALUES (\"" + isbn + "\",\"" + name + "\",\""+ autor + "\",\"" + published + "\",\"" + status + "\")";
        makeUpdate(sql);
    }

    private void closeConnections() {
        try{
            if(rs != null){
                rs.close();
            }
        } catch (SQLException se){
            se.printStackTrace();
        }
        try{
            if(stmt != null){
                stmt.close();
            }
        }catch (SQLException se){
            se.printStackTrace();
        }
        try {
            if(con!=null)
                con.close();
        }catch (SQLException se){
            se.printStackTrace();
        }
    }
}