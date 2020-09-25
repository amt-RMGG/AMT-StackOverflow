package business;

public class HashMapCredentialsFactory implements UserCredentialsManagerFactory {
    private UserCredentialsManager credentials;

    @Override
    public UserCredentialsManager getInstance()
    {
        if(credentials == null)
        {
            credentials = new UserCredentialsHashMap();
        }
        return credentials;
    }
}
