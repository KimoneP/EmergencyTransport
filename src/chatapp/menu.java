
package chatapp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
@SuppressWarnings("unchecked")


public class menu extends javax.swing.JFrame 
{
    protected String username;
    protected String serverIP;
    protected Socket s;
    protected ArrayList<String> userList;   // list of users online
    protected DataOutputStream dout;
    protected DataInputStream din;
    
    public menu(String username, String serverIP, Socket s) 
    {
        initComponents();
        this.username = username;
        this.serverIP = serverIP;
        this.s = s;
        
        refresh();  // checks online users
    }

   
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cmbUsers = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        btnJoinGroup = new javax.swing.JButton();
        btnChat = new javax.swing.JButton();
        btnLogout = new javax.swing.JButton();
        btnRefresh = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        cmbUsers.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Users");

        btnJoinGroup.setText("Join Group");
        btnJoinGroup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnJoinGroupActionPerformed(evt);
            }
        });

        btnChat.setText("Chat");
        btnChat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChatActionPerformed(evt);
            }
        });

        btnLogout.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnLogout.setText("Logout");
        btnLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogoutActionPerformed(evt);
            }
        });

        btnRefresh.setText("refresh");
        btnRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(btnLogout, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
            .addGroup(layout.createSequentialGroup()
                .addGap(167, 167, 167)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cmbUsers, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnJoinGroup)
                            .addComponent(btnChat, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(162, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(132, 132, 132)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbUsers, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                    .addComponent(btnChat, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addComponent(btnJoinGroup, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnLogout, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnJoinGroupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnJoinGroupActionPerformed
        
        // control message to indicate user joining group chat 
        String indicateGroup = "[control]joingroup[control]";
        
        try 
        {
            // tell server that the user is joining the group chat
            dout = new DataOutputStream(s.getOutputStream());
            dout.writeUTF(indicateGroup);
            dout.flush(); 
            
            // opens group chat screen
            new singleChat(username, serverIP, s, indicateGroup ).setVisible(true);
            this.dispose();
        } 
        catch (Exception e) 
        {
            System.out.println("Error sending group join indication: " + e);
        }
        
        
        
        
    }//GEN-LAST:event_btnJoinGroupActionPerformed

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
        try 
        {
            // informs server of logout action
           dout = new DataOutputStream(s.getOutputStream());
           dout.writeUTF("[control]Logout[control]");
           dout.flush(); 
           
           // wait for confirmation from server to logout
           din = new DataInputStream(s.getInputStream());
           if (din.readByte() == 1)
           {
                // transitions to login screen
                new login().setVisible(true);
                this.dispose();
           }
           else
           {
               System.out.println("Sorry, the server says that you cannot jlog out");
           }
           
            
        } 
        catch (Exception e) 
        {
            System.out.println("Error in Logout message: " + e);
        }
        
       
    }//GEN-LAST:event_btnLogoutActionPerformed

    private void btnChatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChatActionPerformed
        
        
        
        // gets the name selected in the combo box
        String recipient = cmbUsers.getSelectedItem().toString();
        
        // a name was selected
        if (!recipient.equals(""))
        {
            // refreshes the list of users online
            refresh();
            
            // check that the recipient is still online - after refreshing the userList
            if (userList.contains(recipient))
            {
                // sets recipient name to the username in the combo box
                new singleChat(username, serverIP, s, recipient).setVisible(true);
            }
            else
            {
                JOptionPane.showMessageDialog(rootPane, recipient +" has gone offline.");
            }
                
            
        }
        else
        {
            // tells user to select a recipient
            JOptionPane.showMessageDialog(rootPane, "Please select user to chat with.");
        }
        
    }//GEN-LAST:event_btnChatActionPerformed

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
        refresh();
    }//GEN-LAST:event_btnRefreshActionPerformed

    
    
    public void refresh()
    {
        try 
        {
            // request list of online users from server
            DataOutputStream dout = new DataOutputStream(s.getOutputStream());
            dout.writeUTF("[control]userList[control]");
            dout.flush();
            
            // get list of online users from server
            ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
            this.userList = (ArrayList<String>) ois.readObject();   
            
            // remove the client's username - prevents the client from chatting with itself
            this.userList.remove(username);
            
            // populate combo box
            cmbUsers.setModel(new DefaultComboBoxModel(userList.toArray()));
        } 
        catch (Exception e) 
        {
            System.out.println("could not be refreshed: " + e);
        }
        
    }
    
    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try 
                {
                    new menu("Kimone", "196.42.71.151", new Socket("196.42.71.151", 3021)).setVisible(true);
                } catch (Exception e) 
                {
                    System.out.println("Server not running");
                }
                
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnChat;
    private javax.swing.JButton btnJoinGroup;
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnRefresh;
    private javax.swing.JComboBox<String> cmbUsers;
    private javax.swing.JLabel jLabel2;
    // End of variables declaration//GEN-END:variables
}
