package business;

import java.util.Hashtable;
import java.util.Map;

public class UserCredentialsHashMap {

    static private Map<String, String> credentials = new Hashtable<String, String>();

    UserCredentialsHashMap()
    {
        credentials = new Hashtable<String, String>();
    }

    static public boolean addUser(String username, String pswd)
    {
        if(credentials.containsKey(username))
        {
            return false;
        }
        credentials.put(username, pswd);
        return true;
    }

    static public boolean checkCredentials(String username, String pswd)
    {
        return credentials.get(username).equals(pswd);
    }
}
