
package chatapp;

import java.net.Socket;
import java.util.ArrayList;

// stores information about each chat available on the menu screen
public class Chat 
{
    protected ArrayList<Socket> membersList = new ArrayList<Socket>();
    protected boolean group;        // is the chat a group or private chat
    
    public Chat(boolean group)
    {
        this.group = group;
    }
    
    public void addMember(Socket member)
    {
        membersList.add(member);
    }
    
    public void removeMember(Socket member)
    {
        membersList.remove(member);
    }
    
}
