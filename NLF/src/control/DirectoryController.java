package control;

import data.DatabaseHandler;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "directoryController")
public class DirectoryController extends HttpServlet {
    private DatabaseHandler databaseHandler;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if(WebAppController.getInstance().userIsLoggedIn()) {  //check if login is active
            String action = request.getParameter("action");
            String isbn = request.getParameter("isbn");

            switch (action) {
                case "LOAN":
                    loanBookFromLibrary(isbn);
                    break;

                case "RETURN":
                    returnBookToLibraray(isbn);
                    break;

                default:
                    break;
            }
        }
        String redirectURL = request.getContextPath() + "/login";  //reload table or go to login screen
        response.sendRedirect(redirectURL);
    }

    //write to SQL
    private void loanBookFromLibrary(String isbn){
        databaseHandler = new DatabaseHandler();
        databaseHandler.setBookStatus(isbn,"Borrowed");
    }

    //write to SQL
    private void returnBookToLibraray(String isbn){
        databaseHandler = new DatabaseHandler();
        databaseHandler.setBookStatus(isbn,"Available");
    }
}