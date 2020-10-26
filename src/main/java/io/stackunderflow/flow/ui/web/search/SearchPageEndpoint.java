package io.stackunderflow.flow.ui.web.search;

import io.stackunderflow.flow.application.ServiceRegistry;
import io.stackunderflow.flow.application.question.QuestionFacade;
import io.stackunderflow.flow.application.question.QuestionsDTO;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "SearchPageHandler", urlPatterns = "/search")
public class SearchPageEndpoint extends HttpServlet {

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
        String searchString = req.getParameter("searchText");
        QuestionsDTO questionsDTO = questionFacade.searchQuestions(searchString);
        req.setAttribute("questions", questionsDTO);
        req.getRequestDispatcher("/WEB-INF/views/search.jsp").forward(req, resp);
    }
}