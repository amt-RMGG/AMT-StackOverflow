package io.stackunderflow.flow.ui.web.gamification;

import io.stackunderflow.flow.application.ServiceRegistry;
import io.stackunderflow.flow.application.gamification.Badge;
import io.stackunderflow.flow.application.gamification.GamificationFacade;
import io.stackunderflow.flow.application.identitymgmt.authenticate.UserDTO;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Provider;
import java.util.List;

@WebServlet(name = "BadgePageHandler", urlPatterns = "/badges")
public class BadgePageEndpoint extends HttpServlet{

    @Inject
    ServiceRegistry serviceRegistry;
    GamificationFacade badgeFacade;

    @Override
    public void init() throws ServletException {
        super.init();
    }
    @PostConstruct
    public void postConstruct(){
        badgeFacade = serviceRegistry.getGamificationFacade();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        //If no username is passed in the url parameter, we return on the home page
        HttpServletRequest  request     = (HttpServletRequest) req;
        UserDTO currentUser = (UserDTO) request.getSession().getAttribute("currentUser");
        String username = currentUser.getUsername();

        List<Badge> availaibleBadges = badgeFacade.getAvailaibleBadges();
        req.setAttribute("availaibleBadges", availaibleBadges);

        List<Badge> userBadges = badgeFacade.getUserBadges(username);
        req.setAttribute("userBadges", userBadges);

        req.getRequestDispatcher("/WEB-INF/views/badgepage.jsp").forward(req, resp);
    }
}
