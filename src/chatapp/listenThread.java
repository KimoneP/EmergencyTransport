
package chatapp;

import java.io.DataInputStream;
import java.net.Socket;
import javax.swing.JTextArea;

public class listenThread extends Thread
{
    protected DataInputStream din;
    protected Socket s;
    protected JTextArea txtChat;
    protected boolean stopListen;
    
    public listenThread(Socket s, JTextArea txtChat)
    {
        this.s = s;
        this.txtChat = txtChat;
        this.stopListen = false;
    }
    
    public void run()
    {
        while (!stopListen)
        {
            try 
            {
                // get message from server 
                din = new DataInputStream(s.getInputStream());
                String in = (String)din.readUTF();
                
                // if received a disconnect confirmation
                if (in.equals("[message]Disconnect[message]"))
                {
                    stopListen = true;
                    s.close();
                }
                else
                {
                    // print message to chat
                    txtChat.append(in + "\n");
                }
               
            } 
            catch (Exception e) 
            {
                System.out.println("listen exception: " + e);
                this.stopListen = true;
            }
            
        }
    }
   
}
