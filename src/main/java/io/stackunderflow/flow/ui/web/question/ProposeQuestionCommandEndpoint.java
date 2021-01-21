package io.stackunderflow.flow.ui.web.question;

import io.stackunderflow.flow.application.ServiceRegistry;
import io.stackunderflow.flow.application.gamification.Badge;
import io.stackunderflow.flow.application.identitymgmt.authenticate.UserDTO;
import io.stackunderflow.flow.application.question.ProposeQuestionCommand;
import io.stackunderflow.flow.application.question.QuestionFacade;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet(name = "SubmitQuestionsCommandHandler", urlPatterns = "/submitQuestion.do")
public class ProposeQuestionCommandEndpoint extends HttpServlet {

    @Inject
    private ServiceRegistry serviceRegistry;
    private QuestionFacade questionFacade;

    @PostConstruct
    public void postConstruct(){
        questionFacade = serviceRegistry.getQuestionFacade();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProposeQuestionCommand command = ProposeQuestionCommand.builder()
                .author(((UserDTO)req.getSession().getAttribute("currentUser")).getUsername())
                .text(req.getParameter("text"))
                .title(req.getParameter("title"))
                .build();

        Optional<Badge> badge = questionFacade.proposeQuestion(command);
        String awardedBadge = "";
        if(badge.isPresent()){
            awardedBadge = "?awardedBadge=" + badge.get().getName();
        }
        resp.sendRedirect("questions" + awardedBadge);
    }
}
