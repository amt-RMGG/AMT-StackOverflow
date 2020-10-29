package io.stackunderflow.flow.ui.web.vote;

import io.stackunderflow.flow.application.ServiceRegistry;
import io.stackunderflow.flow.application.identitymgmt.authenticate.CurrentUserDTO;
import io.stackunderflow.flow.application.vote.ProposeVoteCommand;
import io.stackunderflow.flow.application.vote.VoteFacade;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "SubmitVoteCommandHandler", urlPatterns = "/submitVote.do")
public class ProposeVoteCommandEndpoint extends HttpServlet {
    private ServiceRegistry serviceRegistry = ServiceRegistry.getServiceRegistry();
    private VoteFacade voteFacade = serviceRegistry.getVoteFacade();

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProposeVoteCommand command = ProposeVoteCommand.builder()
            .idUser(((CurrentUserDTO)request.getSession().getAttribute("currentUser")).getUsername())
            .idQuestion(request.getParameter("idQuestion"))
            .type(request.getParameter("type")).build();
    }
}
