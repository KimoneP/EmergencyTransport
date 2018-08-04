
package chatapp;

//import java.awt.image.BufferedImage;
import java.io.DataInputStream;
import java.io.DataOutputStream;
//import java.io.File;
import java.net.Socket;
//import java.util.ArrayList;
//import javax.imageio.ImageIO;
//import javax.swing.JFileChooser;
//import javax.swing.JOptionPane;

public class singleChat extends javax.swing.JFrame 
{

    protected String serverIP;
    protected String username;
    protected Socket s;
    protected DataOutputStream dout;
    protected DataInputStream din;
    protected listenThread listener;
    protected String recipient;
    
    
    public singleChat(String username, String serverIP, Socket s, String recipient) 
    {
        initComponents();
        this.username = username;
        this.serverIP = serverIP;
        this.s = s;
        this.recipient = recipient;
        
        // listener thread created for this chat
        listener = new listenThread(s, txtChat);
        Thread thread = new Thread(listener);
        thread.start();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtTextField = new javax.swing.JTextField();
        btnSend = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtChat = new javax.swing.JTextArea();
        btnClose = new javax.swing.JButton();
        btnAttach = new javax.swing.JButton();
        btnJoinChat = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setResizable(false);

        txtTextField.setName(""); // NOI18N
        txtTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTextFieldActionPerformed(evt);
            }
        });
        txtTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTextFieldKeyPressed(evt);
            }
        });

        btnSend.setText("Send");
        btnSend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSendActionPerformed(evt);
            }
        });

        txtChat.setEditable(false);
        txtChat.setColumns(20);
        txtChat.setRows(5);
        jScrollPane1.setViewportView(txtChat);

        btnClose.setText("Back");
        btnClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCloseActionPerformed(evt);
            }
        });

        btnAttach.setText("Attach");
        btnAttach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAttachActionPerformed(evt);
            }
        });

        btnJoinChat.setText("Join Chat");
        btnJoinChat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnJoinChatActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 494, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(btnClose, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnJoinChat, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(98, 98, 98))
                            .addComponent(txtTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 398, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnSend, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnAttach, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(23, 23, 23)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 353, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSend, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnClose, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnJoinChat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnAttach, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTextFieldActionPerformed

    private void btnSendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSendActionPerformed
        send();
    }//GEN-LAST:event_btnSendActionPerformed

    private void send() 
    {
        // get text
        String msg = txtTextField.getText();
        
        try
        {
            // send message
            dout = new DataOutputStream(s.getOutputStream());
            
            // Message Format = "recipient#username: Message"
            dout.writeUTF(recipient + "#" +username + ": " + msg); 
            
            // write message to text area
            txtChat.append("Me: " + msg);
            txtTextField.setText("");
        }
        catch(Exception e)
        {
            System.out.println("send exeption: "+e);
        }
    }

    private void btnCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCloseActionPerformed
       
        try 
        {   
            // tell the server that you want to exit the current chat
            dout = new DataOutputStream(s.getOutputStream());
       
            dout.writeUTF(recipient+"#[control]exitchat[control]");
            dout.flush();
            
            // wait  for confirmation byte from server
            din = new DataInputStream(s.getInputStream());
            if (din.readByte() == 1)
            {
                // close
                this.dispose();
                new menu(username, serverIP, s).setVisible(true);
            }
            else
            {
                System.out.println("Server says you cannot leave the chat");
            }
            
            
                    
        } 
        catch (Exception e) 
        {
            System.out.println("close exception"+ e);
        }
        
    }//GEN-LAST:event_btnCloseActionPerformed

    private void btnAttachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAttachActionPerformed
        /*try 
        {
            JFileChooser chooser = new JFileChooser();
            chooser.showOpenDialog(null);
            File inputFile = chooser.getSelectedFile();
            BufferedImage image = ImageIO.read(inputFile);
        
            ByteOutputStream base = new ByteOutputStream();
            ImageIO.write(image, "jpg", base);
            
            dout.write(base.toByteArray());
            dout.flush();
        } 
        catch (Exception e) 
        {
            System.out.println("Image error: " + e);
        }
        */
    }//GEN-LAST:event_btnAttachActionPerformed

    private void txtTextFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTextFieldKeyPressed
        int keyCode = evt.getKeyCode();
        if (keyCode == 10){
            send();
        }
               
    }//GEN-LAST:event_txtTextFieldKeyPressed

    private void btnJoinChatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnJoinChatActionPerformed
         
      /*  try 
        {            
            // connect to listening server socket
            s = new Socket(serverIP,1337);
      
            // get new socket number
            DataInputStream din = new DataInputStream(s.getInputStream());
            int port = Integer.parseInt(din.readUTF());
            
            // create new socket
            s = new Socket(serverIP, port);

            // ask for username
            txtChat.append(username + " joined\n");
            dout = new DataOutputStream(s.getOutputStream());
            dout.writeUTF(username + " joined");

            // listener thread
            listener = new listenThread(s, txtChat);
            Thread thread = new Thread(listener);
            thread.start();
        } 
        catch (Exception e) 
        {
            System.out.println("open exception" + e);
            JOptionPane.showMessageDialog(rootPane, "Could not connect to server.");
        }
        */
        
        
        
    }//GEN-LAST:event_btnJoinChatActionPerformed

   
    
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(singleChat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(singleChat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(singleChat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(singleChat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() 
            {
                try 
                {
                    new singleChat("Kimone","196.42.71.151", new Socket("196.42.71.151", 3021), "group").setVisible(true);
                } catch (Exception e) 
                {
                    System.out.println("Server not running");
                }
                
            }
        });
    }
    


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAttach;
    private javax.swing.JButton btnClose;
    private javax.swing.JButton btnJoinChat;
    private javax.swing.JButton btnSend;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea txtChat;
    private javax.swing.JTextField txtTextField;
    // End of variables declaration//GEN-END:variables
}
