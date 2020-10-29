package io.stackunderflow.flow.ui.web.vote;

import io.stackunderflow.flow.application.ServiceRegistry;
import io.stackunderflow.flow.application.identitymgmt.authenticate.CurrentUserDTO;
import io.stackunderflow.flow.application.vote.ProposeVoteCommand;
import io.stackunderflow.flow.application.vote.VoteFacade;
import io.stackunderflow.flow.domain.question.QuestionId;
import io.stackunderflow.flow.domain.vote.VoteType;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@WebServlet(name = "SubmitVoteCommandHandler", urlPatterns = "/submitVote.do")
public class ProposeVoteCommandEndpoint extends HttpServlet {
    private ServiceRegistry serviceRegistry = ServiceRegistry.getServiceRegistry();
    private VoteFacade voteFacade = serviceRegistry.getVoteFacade();
    private VoteType type;

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getParameter("type") == "DEFAULT")
            type = VoteType.DEFAULT;
        else if(request.getParameter("type") == "DOWNVOTE")
            type = VoteType.DOWNVOTE;
        else
            type = VoteType.UPVOTE;

        ProposeVoteCommand command = ProposeVoteCommand.builder()
            .username(((CurrentUserDTO)request.getSession().getAttribute("currentUser")).getUsername())
            .idQuestion(new QuestionId(request.getParameter("idQuestion")))
            .type(type).build();
    }
}
