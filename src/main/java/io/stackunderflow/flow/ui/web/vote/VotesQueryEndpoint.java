package io.stackunderflow.flow.ui.web.vote;

import io.stackunderflow.flow.application.ServiceRegistry;
import io.stackunderflow.flow.application.vote.VoteFacade;
import io.stackunderflow.flow.application.vote.VoteQuery;
import io.stackunderflow.flow.application.vote.VoteDTO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "QuestionsPageHandler", urlPatterns = "/questions")
public class VotesQueryEndpoint extends HttpServlet {

    private ServiceRegistry serviceRegistry;
    private VoteFacade voteFacade;

    @Override
    public void init() throws ServletException {
        super.init();
        serviceRegistry = ServiceRegistry.getServiceRegistry();
        voteFacade = serviceRegistry.getVoteFacade();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        VoteDTO voteDTO = voteFacade.getVotes(VoteQuery.builder().build());
        req.setAttribute("votes", votesDTO);
        req.getRequestDispatcher("/WEB-INF/views/questions.jsp").forward(req, resp);
    }
}
