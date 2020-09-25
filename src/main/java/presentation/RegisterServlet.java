package presentation;

import business.UserCredentialsManager;
import business.UserCredentialsManagerFactory;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "RegisterServlet", urlPatterns = "/registerService")
public class RegisterServlet extends javax.servlet.http.HttpServlet{
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        String username = request.getParameter("username");
        String pswd = request.getParameter("pswd");
        String confirm = request.getParameter("confirm");

        response.setContentType("text/html");

        if(username != null)
        {
            if(!pswd.equals(confirm))
            {
                response.getWriter().println("Error : Passwords do not match");
                return;
            }

            UserCredentialsManager userCredentialManager = UserCredentialsManager.getManagerFactory().getInstance();
            if(!username.equals(""))
            {
                if(userCredentialManager.addUser(username, pswd))
                {
                    //TODO : Redirect to index.jsp
                    response.getWriter().println("Registration successful");
                    return;
                }
            }
        }

        response.getWriter().println("Registration failed");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        doGet(request, response);
    }
}
