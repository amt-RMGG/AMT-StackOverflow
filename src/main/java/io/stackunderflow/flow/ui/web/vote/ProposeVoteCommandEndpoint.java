package io.stackunderflow.flow.ui.web.vote;

import io.stackunderflow.flow.application.ServiceRegistry;
import io.stackunderflow.flow.application.gamification.Badge;
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
import java.util.Optional;

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


        Optional<Badge> badge;
        ProposeVoteCommand command;
        if(request.getParameter("objectType").equals("question")) {
            command = ProposeVoteCommand.builder()
                    .username(((UserDTO) request.getSession().getAttribute("currentUser")).getUsername())
                    .idQuestion(new QuestionId(request.getParameter("idQuestion")))
                    .type(type).build();

            badge = voteFacade.proposeVote(command, false);
        }
        else {
            command = ProposeVoteCommand.builder()
                    .username(((UserDTO) request.getSession().getAttribute("currentUser")).getUsername())
                    .idQuestion(new QuestionId(request.getParameter("idAnswer")))
                    .type(type).build();

            badge = voteFacade.proposeVote(command, true);
        }
        String awardedBadge = "";
        if(badge.isPresent()){
            awardedBadge = "&awardedBadge=" + badge.get().getName();
        }
        response.sendRedirect("question?id="+request.getParameter("idQuestion") + awardedBadge);
    }
}
