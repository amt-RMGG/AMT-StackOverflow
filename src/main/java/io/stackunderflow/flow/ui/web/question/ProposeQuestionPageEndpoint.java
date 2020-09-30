package io.stackunderflow.flow.ui.web.question;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "ProposeQuestionPageHandler", urlPatterns = "/askQuestion")
public class ProposeQuestionPageEndpoint extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //Transfer of the errors attributs from the session to the request
        Object errors = req.getSession().getAttribute("errors");
        req.setAttribute("errors", errors);
        req.getSession().removeAttribute("errors");
        req.getRequestDispatcher("/WEB-INF/views/askquestion.jsp").forward(req, resp);
    }
}