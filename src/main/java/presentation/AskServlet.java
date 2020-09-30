package presentation;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AskServlet", urlPatterns = "/askService")
public class AskServlet extends javax.servlet.http.HttpServlet{
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        String title = request.getParameter("title");
        String question = request.getParameter("question");

        response.setContentType("text/html");

        if(title != null && question!= null)
        {
            // TODO
            // Insert dans database
            // Redirection sur page de question
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        doGet(request, response);
    }
}