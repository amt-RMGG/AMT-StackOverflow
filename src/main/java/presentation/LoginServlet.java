package presentation;

import business.UserCredentialsHashMap;

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

            if(UserCredentialsHashMap.checkCredentials(username, pswd))
            {
                //TODO : Redirect to index.jsp
                //TODO : Manage user sessions
                response.sendRedirect("index.jsp");
                return;
            }
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        doGet(request, response);
    }
}

