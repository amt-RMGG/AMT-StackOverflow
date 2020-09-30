package io.stackunderflow.flow.ui.web.question;

import io.stackunderflow.flow.application.ServiceRegistry;
import io.stackunderflow.flow.application.question.QuestionFacade;
import io.stackunderflow.flow.application.question.QuestionQuery;
import io.stackunderflow.flow.application.question.QuestionsDTO;
import io.stackunderflow.flow.domain.question.Question;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "QuestionsPageHandler", urlPatterns = "/questions")
public class QuestionsQueryEndpoint extends HttpServlet {

    private ServiceRegistry serviceRegistry;
    private QuestionFacade questionFacade;

    @Override
    public void init() throws ServletException {
        super.init();
        serviceRegistry = ServiceRegistry.getServiceRegistry();
        questionFacade = serviceRegistry.getQuestionFacade();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        QuestionsDTO questionsDTO = questionFacade.getQuestions(QuestionQuery.builder().build());
        req.setAttribute("questions", questionsDTO);
        req.getRequestDispatcher("/WEB-INF/views/questions.jsp").forward(req, resp);
    }
}
