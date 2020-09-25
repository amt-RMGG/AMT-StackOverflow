package business;

import java.util.Hashtable;
import java.util.Map;

public class UserCredentialsHashMap extends UserCredentialsManager{

    private Map credentials;

    UserCredentialsHashMap()
    {
        credentials = new Hashtable<String, String>();
    }

    public boolean addUser(String username, String pswd)
    {
        if(credentials.containsKey(username))
        {
            return false;
        }
        credentials.put(username, pswd);
        return true;
    }

    public boolean checkCredentials(String username, String pswd)
    {
        return credentials.get(username).equals(pswd);
    }
}
