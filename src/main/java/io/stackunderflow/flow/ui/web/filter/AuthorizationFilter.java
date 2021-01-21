package io.stackunderflow.flow.ui.web.filter;

import io.stackunderflow.flow.application.identitymgmt.authenticate.UserDTO;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "AuthorizationFilter", urlPatterns = "/*")
public class AuthorizationFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest  request     = (HttpServletRequest) req;
        HttpServletResponse response    = (HttpServletResponse) resp;

        // Static content (css, js, ...) never restricted !
        if(isPublicRessouce(request.getRequestURI())){
            chain.doFilter(req, resp);
            return;
        }

        UserDTO currentUser = (UserDTO) request.getSession().getAttribute("currentUser");

        //Always redirect to the login page the non logged users
        if(currentUser == null){
            String targetURL = request.getRequestURI();
            if(request.getQueryString() != null){
                targetURL += "?" + request.getQueryString();
            }
            request.getSession().setAttribute("targetUrl", targetURL); //dans la video pas commente, mais Liechti trouve ca bizarre donc on enleve ^^
            request.getSession().removeAttribute("targetUrl");

            //Si le user n'est pas connecte, on le redirige sur la page "home"
            ((HttpServletResponse)resp).sendRedirect("/stackunderflow/home");
            return;
        }
        if(request.getRequestURI().equals("/stackunderflow"))
        {
            ((HttpServletResponse)resp).sendRedirect("stackunderflow/questions");
        }
        chain.doFilter(req, resp);
    }

    boolean isPublicRessouce(String requestURI) {
        if(requestURI.startsWith("/stackunderflow/badge"))
            return true;
        if(requestURI.startsWith("/stackunderflow/search"))
            return true;
        if(requestURI.startsWith("/stackunderflow/user"))
            return true;
        if(requestURI.startsWith("/stackunderflow/question"))
            return true;
        if(requestURI.startsWith("/stackunderflow/questions"))
            return true;
        if(requestURI.startsWith("/stackunderflow/home"))
            return true;
        if(requestURI.startsWith("/stackunderflow/assets"))
            return true;
        if(requestURI.startsWith("/stackunderflow/login"))
            return true;
        if(requestURI.startsWith("/stackunderflow/logout"))
            return true;
        if(requestURI.startsWith("/stackunderflow/register"))
            return true;

        return false;
    }
}
