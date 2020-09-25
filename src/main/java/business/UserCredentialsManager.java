package business;

import business.UserCredentialsManagerFactory;

public abstract class UserCredentialsManager {
    private static UserCredentialsManagerFactory userCredentialsManagerFactory;

    public static UserCredentialsManagerFactory getManagerFactory() {
        return userCredentialsManagerFactory;
    }

    public static void setManagerFactory(UserCredentialsManagerFactory newUserCredentialsManagerFactory) {
        userCredentialsManagerFactory = newUserCredentialsManagerFactory;
    }

    public abstract boolean addUser(String username, String pswd);
    public abstract boolean checkCredentials(String username, String pswd);
}
