package presentation;
import business.QuestionListGenerator;
import model.Question;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "HomepageServlet", urlPatterns = "/allQuestions")
public class HomepageServlet extends javax.servlet.http.HttpServlet {

    private QuestionListGenerator service; // we will see later how to replace this with dependency injection

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        service = new QuestionListGenerator();
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws ServletException, IOException {
        List<Question> model = service.generateQuestion();
        request.setAttribute("questions", model);
        request.getRequestDispatcher("/WEB-INF/views/viewAllQuestions.jsp").forward(request, response);
    }
}
