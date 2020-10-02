package io.stackunderflow.flow.ui.web.question;

import io.stackunderflow.flow.application.ServiceRegistry;
import io.stackunderflow.flow.application.identitymgmt.authenticate.CurrentUserDTO;
import io.stackunderflow.flow.application.question.ProposeQuestionCommand;
import io.stackunderflow.flow.application.question.QuestionFacade;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Currency;

@WebServlet(name = "SubmitQuestionsCommandHandler", urlPatterns = "/submitQuestion.do")
public class ProposeQuestionCommandEndpoint extends HttpServlet {

    private ServiceRegistry serviceRegistry = ServiceRegistry.getServiceRegistry();
    private QuestionFacade questionFacade = serviceRegistry.getQuestionFacade();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProposeQuestionCommand command = ProposeQuestionCommand.builder()
                .author(((CurrentUserDTO)req.getSession().getAttribute("currentUser")).getUsername())
                .text(req.getParameter("text"))
                .title(req.getParameter("title"))
                .build();

        questionFacade.proposeQuestion(command);
        resp.sendRedirect("questions");
    }
}
