package webapp;

import control.WebAppController;
import data.DatabaseHandler;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "bookCreation")
public class BookCreation extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if(WebAppController.getInstance().userIsLoggedIn()) {  // check if login is still active
            String name = request.getParameter("name");
            String autor = request.getParameter("autor");
            String published = request.getParameter("published");
            String isbn = request.getParameter("isbn");
            DatabaseHandler databaseHandler = new DatabaseHandler();

            if(databaseHandler.isValidBook(name,autor,published,isbn)) {
                databaseHandler.addBook(name, autor, published, isbn);    //save new book
            }
            else {
                request.setAttribute("errorMessage", "The given data is invalid.");   //error on wrong book data
                request.getRequestDispatcher("/book_creation.jsp").forward(request,response);
            }
        }
        String redirectURL = request.getContextPath() + "/login";
        response.sendRedirect(redirectURL);
    }
}