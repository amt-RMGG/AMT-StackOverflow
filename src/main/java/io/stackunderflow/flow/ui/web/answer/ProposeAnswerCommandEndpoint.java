package io.stackunderflow.flow.ui.web.answer;

import io.stackunderflow.flow.application.ServiceRegistry;
import io.stackunderflow.flow.application.answer.ProposeAnswerCommand;
import io.stackunderflow.flow.application.identitymgmt.authenticate.UserDTO;
import io.stackunderflow.flow.application.question.QuestionFacade;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "SubmitAnswerCommandHandler", urlPatterns = "/submitAnswer.do")
public class ProposeAnswerCommandEndpoint extends HttpServlet {

    @Inject
    private ServiceRegistry serviceRegistry;
    private QuestionFacade questionFacade;

    @PostConstruct
    public void postConstruct(){
        questionFacade = serviceRegistry.getQuestionFacade();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ProposeAnswerCommand command = ProposeAnswerCommand.builder()
                .author(((UserDTO)req.getSession().getAttribute("currentUser")).getUsername())
                .text(req.getParameter("text"))
                .questionId(req.getParameter("questionId"))
                .build();

        questionFacade.proposeAnswer(command);
        resp.sendRedirect("questions");
    }
}