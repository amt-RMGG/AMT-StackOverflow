package io.stackunderflow.flow.ui.web.login;


import io.stackunderflow.flow.application.ServiceRegistry;
import io.stackunderflow.flow.application.identitymgmt.IdentityManagementFacade;
import io.stackunderflow.flow.application.identitymgmt.authenticate.AuthenticateCommand;
import io.stackunderflow.flow.application.identitymgmt.authenticate.AuthenticationFailedException;
import io.stackunderflow.flow.application.identitymgmt.authenticate.CurrentUserDTO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "LoginCommandHandler", urlPatterns = "/login.do")
public class LoginCommandEndpoint extends HttpServlet {

    private ServiceRegistry serviceRegistry = ServiceRegistry.getServiceRegistry();
    private IdentityManagementFacade identityManagementFacade = serviceRegistry.getIdentityManagementFacade();

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().removeAttribute("errors");

        CurrentUserDTO currentUser = null;

        AuthenticateCommand authenticateCommand = AuthenticateCommand.builder()
                .username(req.getParameter("username"))
                .clearTextPassword(req.getParameter("password"))
                .build();

        try {
            currentUser = identityManagementFacade.authenticate(authenticateCommand);
            req.getSession().setAttribute("currentUser", currentUser);

            //Pour savoir ou le user était avant de vouloir se loger
            String targetUrl = (String) req.getSession().getAttribute("targetUrl");
            //Si c'est vide, on va sur login par défault
            targetUrl = (targetUrl != null) ? targetUrl : "/stackunderflow/questions"; //TODO : regler ce probleme d'url !
            resp.sendRedirect(targetUrl);
            return;
        }catch (AuthenticationFailedException e){
            req.getSession().setAttribute("errors", (e.getMessage()));
            resp.sendRedirect("/stackunderflow/login");
            return;
        }

    }
}
