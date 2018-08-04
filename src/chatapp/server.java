
package chatapp;

import java.io.*;
import java.net.*;
import java.util.*;

public class server
{
    private static int num = 0;
    private static ArrayList<Socket> socketList = new ArrayList<Socket>();  // list of all sockets connected to the server
    private static ArrayList<String> userList = new ArrayList<String>();    // list of all usernames connected to the server - matches socketList
    protected static ArrayList<Chat> chats = new ArrayList<Chat>();           // list of all chats currently open
    
    public static void main (String [] args)
    {
        // populate the "chats" array with the pre-defined group chat - no users
        chats.add(new Chat(true));
        
        // create a thread to test that all sockets in the socketList are still connected
        Thread rThread = new Thread(new refreshThread(socketList, userList));  
        rThread.start();
        
        try
        {
            System.out.println("Server Running");  
            ServerSocket ss = new ServerSocket(1337);       // Listening for new clients
            String oops = "ff";
            
            // server continuously waits for new clients, until the user decides to stop the server
            while(true)
            { 
                Socket s = ss.accept();                     // accepts new client at 1337
                num++;                                      // increment new port number
                
                //extract the username and password from userInfo
                String userInfo = new DataInputStream(s.getInputStream()).readUTF();    // get the username and password once the connection is made
                Scanner scanner = new Scanner(userInfo).useDelimiter("#");
                String username = scanner.next();
                String password = scanner.next();
                scanner.close();
                System.out.println("Username: " + username);
                System.out.println("Password: " + password);
                

                
                // populates list of users permitted
                userList u = new userList();
                
                // if the user info matches the info in usersList class, allow access 
                if (u.containsName(username))
                {
                    // check that the password received is the matching password for the username
                    if (u.passwords.get(u.usernames.indexOf(username)).equals(password))
                    {
                        // calculate new port number
                        int newport = 1337 + num; 
                        
                        // sending new port number to client
                        DataOutputStream dout = new DataOutputStream(s.getOutputStream());
                        dout.writeInt(newport);
                        dout.flush();
                        dout.close();

                        // creating new socket from the new port number
                        ServerSocket newss = new ServerSocket(newport); 
                        s = newss.accept();
                        
                        // adding new socket and username to lists
                        socketList.add(s);   
                        userList.add(username);
                        
                        // create new client thread to listen to the new socket created
                        Thread thread = new Thread(new clientThread(newport,s,socketList, userList, username));  
                        thread.start(); 
                    }
                    else
                    {
                        // sending permission denied message to client
                        DataOutputStream dout = new DataOutputStream(s.getOutputStream());
                        dout.writeInt(0);
                        dout.flush();
                        dout.close();
                        System.out.println("Wrong Password");   // debug
                    }
                }
                else
                {
                    // sending permission denied message to client
                    DataOutputStream dout = new DataOutputStream(s.getOutputStream());
                    dout.writeInt(0);
                    dout.flush();
                    dout.close();
                    System.out.println("Wrong Username");   // debug
                }
                
                
                if(oops.equals("Oh no"))
                {
                   break;                 
                }
            } 
        }
        catch(Exception e)
        {
            System.out.println("Server error:\n"+e);
        }
    
  }
}