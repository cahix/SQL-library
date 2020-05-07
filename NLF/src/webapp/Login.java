package webapp;

import control.WebAppController;
import data.DatabaseHandler;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "login")
public class Login extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            User user = new User();
            DatabaseHandler databaseHandler = new DatabaseHandler();

            if(user.isValidUser(request.getParameter("username"),request.getParameter("password"))){  //correct userdata
                WebAppController.getInstance().setUserLoggedIn(true);
                request.setAttribute("bookList", databaseHandler.getAllBooks());
                request.getRequestDispatcher("/book_directory.jsp").forward(request,response);
            }
            else {
                WebAppController.getInstance().setUserLoggedIn(false);                                    //incorrect userdata
                request.setAttribute("errorMessage", "Invalid login data. Please try again.");
                request.getRequestDispatcher("/login.jsp").forward(request,response);
            }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DatabaseHandler databaseHandler = new DatabaseHandler();
        if(WebAppController.getInstance().userIsLoggedIn()){
            request.setAttribute("bookList", databaseHandler.getAllBooks());
            request.getRequestDispatcher("/book_directory.jsp").forward(request,response);
        }
        else {
            request.setAttribute("errorMessage", "No active login.");  //mainly happens on timeout
            request.getRequestDispatcher("/login.jsp").forward(request,response);
        }
    }
}