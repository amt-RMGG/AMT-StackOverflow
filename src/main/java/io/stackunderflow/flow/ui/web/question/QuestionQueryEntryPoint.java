package io.stackunderflow.flow.ui.web.question;

import io.stackunderflow.flow.application.ServiceRegistry;
import io.stackunderflow.flow.application.question.QuestionFacade;
import io.stackunderflow.flow.application.question.QuestionQuery;
import io.stackunderflow.flow.application.question.QuestionsDTO;
import io.stackunderflow.flow.domain.question.QuestionId;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//Specific question
@WebServlet(name = "QuestionPageHandler", urlPatterns = "/question")
public class QuestionQueryEntryPoint extends HttpServlet {

    @Inject
    private ServiceRegistry serviceRegistry;
    private QuestionFacade questionFacade;

    @Override
    public void init() throws ServletException {
        super.init();
    }

    @PostConstruct
    public void postConstruct(){
        questionFacade = serviceRegistry.getQuestionFacade();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //If no id is passed in the url parameter, we return on the home page
        String idFromReq = req.getParameter("id");
        if(idFromReq == null){
            resp.sendRedirect("/stackunderflow/");
            return;
        }

        QuestionQuery query = QuestionQuery.builder()
                .id(new QuestionId(idFromReq))
                .build();

        QuestionsDTO questionsDTO = questionFacade.getQuestions(query);

        req.setAttribute("question", questionsDTO.getQuestions().get(0));
        req.setAttribute("answers", questionsDTO.getQuestions().get(0).getAnswers());
        req.getRequestDispatcher("/WEB-INF/views/question.jsp").forward(req, resp);
    }

}
