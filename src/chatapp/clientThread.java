
package chatapp;

import java.io.*;
import java.net.*;
import java.util.*;

class clientThread extends Thread{

protected int port;
protected Socket s;
protected Socket outSocket;
protected ArrayList<Socket> socketList;
protected ArrayList<String> userList;
protected String username;

public clientThread(int portin, Socket sIn, ArrayList<Socket> inList, ArrayList<String> userList,String username)
{
    this.port = portin;
    this.s = sIn;
    this.socketList = inList;
    this.userList = userList;
    this.username = username;
}

// listens for messages from the client
    public void run()
    {
        try
        {
            DataInputStream dis;
            DataOutputStream dout;
            while(true)
            {
                //gets chat message from client
                dis = new DataInputStream(s.getInputStream());
                String str = (String)dis.readUTF();
                
                // quit option selected
                if(str.equals("[control]Logout[control]"))
                {
                    // permists client to disconnect from server
                    
                    // remove user from the server
                    socketList.remove(s);
                    userList.remove(username);
                    
                    
                    // confirmation byte sent
                    dout = new DataOutputStream(s.getOutputStream());
                    dout.writeByte(1);
                    dout.flush();
                    
                    // stops listening when the user logs out
                    break;
                }
                else if (str.equals("[control]userList[control]"))
                {
                    // client requests the list of users connected to the server(i.e. usersList)
                    
                    /*dout = new DataOutputStream(s.getOutputStream());
                    for(String user: userList)
                    {
                        dout.writeUTF("user");
                    }
                    dout.writeUTF("[control]userListEnd[control]");
                    dout.flush();*/
                    
                    ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
                    oos.writeObject(userList);  // possible error - may have to send individually
                }
                else if (str.equals("[control]joingroup[control]"))
                {
                    // client requests to be added to the group chat
                    
                    // add user to the group chat's membersList - the group chat is always the first in the array
                    server.chats.get(0).membersList.add(s);
                    
                    // send confirmation of connection to the chat
                    dout = new DataOutputStream(s.getOutputStream());
                    dout.writeByte(1);
                    dout.flush();
                }
                
                else
                {
                    
                    // the client sends a data message for a recipient
             
                    // shift out the recipient's name
                    Scanner scanner = new Scanner(str).useDelimiter("#");
                    
                    // get the socket for the corresponding user - scanner.next() has the username of the recipient
                    Socket recipient = socketList.get(userList.indexOf(scanner.next()));
                    // get the actual message
                    String message = scanner.next();
                    scanner.close();
                    
                    // if the message is sent to the group chat
                    if(str.equals("[control]exitchat[control]"))    // possible error
                    {
                        // client requests to exit the chat they are currently in

                        // searches for the chat that the client is currently in
                        for (Chat c : server.chats) 
                        {
                            // if we find the chat that the user is in
                            if (c.membersList.contains(s))
                            {
                                // remove the member from the membersList in that chat
                                c.membersList.remove(s);
                            }
                        }

                        // send confirmation message - client can exit chat
                        dout = new DataOutputStream(s.getOutputStream());
                        dout.writeByte(1);
                        dout.flush();

                        // change the message from a control message to a data message and send the message normally in the next if statement
                        // inform recipient that client has exited the chat
                        message = username + " has left the chat";

                    }
                    if (recipient.equals("[control]group[control]"))
                    {
                        // write message to all users in the chat - in membersList
                        for(Socket member: server.chats.get(0).membersList)
                        {
                            // send the message to all members, not including the sender
                            if (member.getPort() != s.getPort())
                            {
                               dout = new DataOutputStream(member.getOutputStream());
                               dout.writeUTF(message);
                               dout.flush();
                            }

                        }
                        scanner.close();
                    }
                    else    // if the message is sent to the individual chat
                    {
                        // send the message to the socket
                        dout = new DataOutputStream(recipient.getOutputStream());
                        dout.writeUTF(message);
                        dout.flush();
                    }
                    
                    
                    
                }

                
            }
        } 
        catch (Exception e)
        {
            System.out.println("Thread error:\n" + e);
        }
   }
}