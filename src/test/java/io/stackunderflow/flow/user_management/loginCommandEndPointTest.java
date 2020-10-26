package io.stackunderflow.flow.user_management;

import io.stackunderflow.flow.ui.web.login.LoginCommandEndpoint;
import org.junit.Test;
import org.mockito.Mockito;

import javax.servlet.http.HttpServletRequest;

import static org.mockito.Mockito.when;


public class loginCommandEndPointTest {
    @Test
    public void validLoginTest(){
        LoginCommandEndpoint endpoint = new LoginCommandEndpoint();
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        when(request.getParameter("username")).thenReturn("Alice");
        when(request.getParameter("password")).thenReturn("asdf");
    }
}
