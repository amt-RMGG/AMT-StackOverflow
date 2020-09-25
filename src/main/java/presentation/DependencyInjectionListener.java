package presentation;

import business.HashMapCredentialsFactory;
import business.UserCredentialsManagerFactory;
import business.UserCredentialsManager;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class DependencyInjectionListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent pServletContextEvent) {
        UserCredentialsManagerFactory credentialsManagerFactory = new HashMapCredentialsFactory();
        UserCredentialsManager.setManagerFactory(credentialsManagerFactory);
    }

    @Override
    public void contextDestroyed(ServletContextEvent pServletContextEvent) {
        // NOP
    }
}
