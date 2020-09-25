package presentation;

import business.UserCredentialsManager;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "LoginServlet", urlPatterns = "/loginService")
public class LoginServlet extends javax.servlet.http.HttpServlet{
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        String username = request.getParameter("username");
        String pswd = request.getParameter("pswd");

        response.setContentType("text/html");

        if(username != null)
        {

            UserCredentialsManager userCredentialManager = UserCredentialsManager.getManagerFactory().getInstance();

            if(userCredentialManager.checkCredentials(username, pswd))
            {
                //TODO : Redirect to index.jsp
                //TODO : Manage user sessions
                response.getWriter().println("Login Successful");
                return;
            }
        }

        response.getWriter().println("Login Failed");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        doGet(request, response);
    }
}

