package io.stackunderflow.flow.ui.web.login;

import io.stackunderflow.flow.application.ServiceRegistry;
import io.stackunderflow.flow.application.identitymgmt.IdentityManagementFacade;
import io.stackunderflow.flow.application.identitymgmt.login.RegisterCommand;
import io.stackunderflow.flow.application.identitymgmt.login.RegistrationFailedException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet(name = "RegisterCommandHandler", urlPatterns = "/register.do")
public class RegisterCommandEndpoint extends HttpServlet {

    private ServiceRegistry serviceRegistry = ServiceRegistry.getServiceRegistry();
    private IdentityManagementFacade identityManagementFacade = serviceRegistry.getIdentityManagementFacade();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().removeAttribute("errors");

        RegisterCommand registerCommand = RegisterCommand.builder()
                .username(req.getParameter("username"))
                .firstname(req.getParameter("firstname"))
                .lastname(req.getParameter("lastname"))
                .email(req.getParameter("email"))
                .clearTextPassword(req.getParameter("password"))
                .build();

        try {
            identityManagementFacade.register(registerCommand);
            req.getRequestDispatcher("/login.do").forward(req, resp);
            return;
        }catch (RegistrationFailedException e){
            req.getSession().setAttribute("errors", (e.getMessage()));
            resp.sendRedirect("/login");
            return;
        }
    }
}
