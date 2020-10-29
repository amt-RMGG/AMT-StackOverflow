package io.stackunderflow.flow.ui.web.user;

import io.stackunderflow.flow.application.ServiceRegistry;
import io.stackunderflow.flow.application.identitymgmt.UserFacade;
import io.stackunderflow.flow.application.identitymgmt.UserQuery;
import io.stackunderflow.flow.application.identitymgmt.authenticate.UserDTO;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//Specific question
@WebServlet(name = "UserPageHandler", urlPatterns = "/user")
public class UserQueryEntryPoint extends HttpServlet {

    @Inject
    private ServiceRegistry serviceRegistry;
    private UserFacade userFacade;

    @Override
    public void init() throws ServletException {
        super.init();
    }

    @PostConstruct
    public void postConstruct(){
        userFacade = serviceRegistry.getUserFacade();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //If no username is passed in the url parameter, we return on the home page
        String username = req.getParameter("username");
        if(username == null){
            resp.sendRedirect("/stackunderflow/");
            return;
        }

        UserQuery query = UserQuery.builder()
                .username(username)
                .build();

        UserDTO userDTO = userFacade.getUser(query);

        req.setAttribute("user", userDTO);
        req.getRequestDispatcher("/WEB-INF/views/userprofile.jsp").forward(req, resp);
    }

}