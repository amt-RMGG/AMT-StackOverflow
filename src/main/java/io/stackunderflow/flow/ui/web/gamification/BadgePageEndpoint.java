package io.stackunderflow.flow.ui.web.gamification;

import io.stackunderflow.flow.application.gamification.Badge;
import io.stackunderflow.flow.application.gamification.GamificationFacade;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "BadgePageHandler", urlPatterns = "/badge")
public class BadgePageEndpoint extends HttpServlet{

    @Inject
    GamificationFacade badgeFacade;

    @Override
    public void init() throws ServletException {
        super.init();
    }
    @PostConstruct
    public void postConstruct(){}

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Badge> badges = badgeFacade.getAvailaibleBadges();
        req.setAttribute("badges", badges);
        req.getRequestDispatcher("/WEB-INF/views/badgepage.jsp").forward(req, resp);
    }
}
