package io.stackunderflow.flow.ui.web.vote;

import io.stackunderflow.flow.application.ServiceRegistry;
import io.stackunderflow.flow.application.identitymgmt.authenticate.UserDTO;
import io.stackunderflow.flow.application.vote.ProposeVoteCommand;
import io.stackunderflow.flow.application.vote.VoteFacade;
import io.stackunderflow.flow.domain.question.QuestionId;
import io.stackunderflow.flow.domain.vote.VoteType;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "SubmitVoteCommandHandler", urlPatterns = "/submitVote.do")
public class ProposeVoteCommandEndpoint extends HttpServlet {
    @Inject
    private ServiceRegistry serviceRegistry;
    private VoteFacade voteFacade;
    private VoteType type;

    @PostConstruct
    public void postConstruct() {
        voteFacade = serviceRegistry.getVoteFacade();
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getParameter("type").equals("UPVOTE"))
            type = VoteType.UPVOTE;
        else if(request.getParameter("type").equals("DOWNVOTE"))
            type = VoteType.DOWNVOTE;
        else
            type = VoteType.DEFAULT;

        ProposeVoteCommand command = ProposeVoteCommand.builder()
            .username(((UserDTO)request.getSession().getAttribute("currentUser")).getUsername())
            .idQuestion(new QuestionId(request.getParameter("idQuestion")))
            .type(type).build();
        voteFacade.proposeVote(command);
        response.sendRedirect("question?id="+request.getParameter("idQuestion"));
    }
}
