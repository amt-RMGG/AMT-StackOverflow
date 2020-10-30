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

@WebServlet(name = "UpdateUserCommandHandler", urlPatterns = "/updateUser.do")
public class UserUpdateCommandEndpoint extends HttpServlet {

    @Inject
    private ServiceRegistry serviceRegistry;
    private UserFacade userFacade;

    @PostConstruct
    public void postConstruct(){
        userFacade = serviceRegistry.getUserFacade();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String username = req.getParameter("currentuname");

        UserDTO currentUserDTO = (UserDTO) req.getSession().getAttribute("currentUser");
        if(currentUserDTO.getUsername().equals(username)){ //if the updated user is the connected one
            String firstname = req.getParameter("fname");
            String lastname = req.getParameter("lname");
            String email = req.getParameter("email");

            UserQuery query = UserQuery.builder()
                    .username(username)
                    .email(email)
                    .firstName(firstname)
                    .lastName(lastname)
                    .build();


            userFacade.updateUser(query);

            req.getSession().setAttribute("currentUser", userFacade.getUser(UserQuery.builder().username(username).build()));

            resp.sendRedirect("user?username=" + username);
        }else{
            resp.sendRedirect("home");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

}