package presentation;

import business.QuestionListGenerator;
import model.Question;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;


@WebServlet(name = "QuestionServlet", urlPatterns = "/question")
public class QuestionServlet extends javax.servlet.http.HttpServlet {

    private QuestionListGenerator service; // we will see later how to replace this with dependency injection

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        service = new QuestionListGenerator();
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        Question question = service.getQuestionById(Integer.parseInt(id));
        request.setAttribute("question", question);
        request.getRequestDispatcher("/WEB-INF/views/viewQuestion.jsp").forward(request, response);
    }
}
