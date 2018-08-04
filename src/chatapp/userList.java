
package chatapp;

import java.util.ArrayList;

// contains the list of usernames and passwords allowed to login
public class userList 
{
    public ArrayList<String> usernames = new ArrayList<String>();
    public ArrayList<String> passwords = new ArrayList<String>();
    
    public userList()
    {
        usernames.add("Kimone");
        usernames.add("Heston");
        usernames.add("Steven");
        
        passwords.add("0000");
        passwords.add("0001");
        passwords.add("0002");
        
    }
    
    public boolean containsName(String username)
    {
        for (String name : usernames) 
        {
            if(name.equals(username))
            {
                return true;
            }
        }
        return false;
    }
    
}
