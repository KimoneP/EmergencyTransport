
package chatapp;

import java.io.DataOutputStream;
import java.net.Socket;
import java.util.ArrayList;


public class refreshThread extends Thread
{
    private static ArrayList<Socket> socketList = new ArrayList<Socket>();
    private static ArrayList<String> userList = new ArrayList<String>();
    
    public refreshThread(ArrayList<Socket> socketList, ArrayList<String> userList)
    {
        this.socketList = socketList;
        this.userList = userList;
    }
        
    
    public void run()
    {
        while(true)
        {
            // constantly checks for and removes sockets that are force closed
            for (int i = 0; i < socketList.size(); i++) 
            {
                Socket socket = socketList.get(i);
                
                 if (!socket.isConnected())
                 {
                 
                    // searches for the chat that the client is currently in
                    for (Chat c : server.chats) 
                    {
                        // if we find the chat that the user is in
                        if (c.membersList.contains(socket))
                        {
                            // inform all recipients that the user has left
                            for (int j = 0; j < c.membersList.size() ; j++) 
                            {
                                try 
                                {
                                    // sending new port number to client
                                    DataOutputStream dout = new DataOutputStream(c.membersList.get(j).getOutputStream());
                                    dout.writeUTF(userList.get(i) + " has left the chat");
                                    dout.flush();
                                } 
                                catch (Exception e) 
                                {
                                    System.out.println("Inform of user force close: "+e);
                                }
                                
                            }
                            
                            // remove the member from the membersList in that chat
                            c.membersList.remove(socket);
                            // if there is currently no one in the chat list 
                        }
                    }
                    
                    userList.remove(userList.get(i));
                    socketList.remove(socket);
                    
                 }
            }
        }
        
    }
               
}
