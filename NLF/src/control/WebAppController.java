package control;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="webAppController")
public class WebAppController extends HttpServlet {
    private static WebAppController instance = null;

    private boolean userLoggedIn = false;

    public static WebAppController getInstance() {
        if (instance == null){
            instance = new WebAppController();
        }
        return instance;
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String action = request.getParameter("action");

        //handle logout buttons
        if(action.equals("Logout")){
            getInstance().setUserLoggedIn(false);
            String redirectURL = request.getContextPath() + "/login.jsp";
            response.sendRedirect(redirectURL);
        }
    }

    public boolean userIsLoggedIn() {
        return userLoggedIn;
    }

    public void setUserLoggedIn(boolean userIsLoggedIn) {
        this.userLoggedIn = userIsLoggedIn;
    }
}