/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package health;

import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;

public class RequestConversation extends javax.swing.JFrame {
    int requestNumber;
    String userID;
    String userType;
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    String element;

    /**
     * Creates new form RequestConversation
     *
     * @param new_requestID
     * @param new_userID
     * @param new_userType
     */
    public RequestConversation(int new_requestID, String new_userID, String new_userType) {
        initComponents();
        requestNumber = new_requestID;
        userID = new_userID;
        userType = new_userType;
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:db\\health");
            //JOptionPane.showMessageDialog (null, "Connected");
            Statement statement = conn.createStatement();
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        String sql = "select * from Message where RID=?";
        try {
            pst = conn.prepareStatement(sql);
            String temp = Integer.toString(requestNumber);
            pst.setString(1, temp);
            rs = pst.executeQuery();
            currentRequest.setLineWrap(true);
            currentRequest.setWrapStyleWord(true);
            addToRequest.setLineWrap(true);
            addToRequest.setWrapStyleWord(true);
            if (rs.next()) {
                element = rs.getString("TimeStamp");
                currentRequest.append(element + "\n");
                element = rs.getString("Message");
                currentRequest.append(element + "\n");
                while (rs.next()) {
                    element = rs.getString("TimeStamp");
                    currentRequest.append(element + "\n");
                    element = rs.getString("Message");
                    currentRequest.append(element + "\n");
                }
            } else {
                JOptionPane.showMessageDialog(null, "No message added");
            }
            if ("Doctor".equals(userType)) {
                sql = "update Message set DUsername=? where RID =?";
                pst = conn.prepareStatement(sql);
                pst.setString(1, userID);
                pst.setString(2, temp);
                pst.execute();
            }

        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        } finally {
            try {
                rs.close();
                pst.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }

        sql = "select Status from Request where RID =?";
        try {
            pst = conn.prepareStatement(sql);
            String temp = Integer.toString(requestNumber);
            pst.setString(1, temp);
            rs = pst.executeQuery();
            if ("Closed".equals(rs.getString("Status"))) {
                closeButton.setEnabled(false);
                addButton.setEnabled(false);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        } finally {
            try {
                rs.close();
                pst.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        currentRequest = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        addToRequest = new javax.swing.JTextArea();
        addButton = new javax.swing.JButton();
        closeButton = new javax.swing.JButton();
        backButton = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        currentRequest.setColumns(20);
        currentRequest.setRows(5);
        jScrollPane1.setViewportView(currentRequest);

        addToRequest.setColumns(20);
        addToRequest.setRows(5);
        jScrollPane2.setViewportView(addToRequest);

        addButton.setText("Add to Request");
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });

        closeButton.setText("Close Request");
        closeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeButtonActionPerformed(evt);
            }
        });

        backButton.setText("Back");
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Papyrus", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(51, 51, 255));
        jLabel7.setText("HealthConnect");

        jLabel1.setFont(new java.awt.Font("Eras Demi ITC", 2, 24)); // NOI18N
        jLabel1.setText("View Request");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 462, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 367, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(66, 66, 66)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(addButton, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(31, 31, 31)
                                                                .addComponent(closeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(30, 30, 30)
                                                                .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 393, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(0, 19, Short.MAX_VALUE)))
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel7)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(4, 4, 4)
                                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 393, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(closeButton)
                                                        .addComponent(addButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(backButton))
                                                .addGap(6, 6, 6))
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>                        

    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        int pane = JOptionPane.showConfirmDialog(null, "Are you sure you want to add your message to the request?", "Add To Request", JOptionPane.YES_NO_OPTION);
        if (pane == 0) {
            String sql = "insert into Message (RID, DUsername, TimeStamp, Message) values (?, ?, ?, ?)";

            try {
                pst = conn.prepareStatement(sql);
                String temp = Integer.toString(requestNumber);
                pst.setString(1, temp);
                pst.setString(2, userID);
                Date date = new Date();
                String timestamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(date);
                pst.setString(3, timestamp);
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("\n");
                stringBuilder.append(addToRequest.getText());
                stringBuilder.append("\n Added by ").append(userType).append(" ").append(userID);
                String finalString = stringBuilder.toString();
                pst.setString(4, finalString);
                pst.execute();
                JOptionPane.showMessageDialog(null, "Message added");
                sql = "update Request set Status='In Progress' where RID =?";
                pst = conn.prepareStatement(sql);
                temp = Integer.toString(requestNumber);
                pst.setString(1, temp);
                pst.execute();
                currentRequest.append("\n");
                currentRequest.append(timestamp);
                currentRequest.append("\n");
                currentRequest.append(finalString);
                addToRequest.setText("");
                sql = "update Message set DUsername=? where RID =?";
                pst = conn.prepareStatement(sql);
                pst.setString(1, userID);
                pst.setString(2, temp);
                pst.execute();
            } catch (SQLException | HeadlessException e) {
                JOptionPane.showMessageDialog(null, e);
            } finally {
                try {
                    rs.close();
                    pst.close();
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, e);
                }
            }
        }

    }

    private void closeButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        int pane = JOptionPane.showConfirmDialog(null, "Are you sure you want to close the request?", "Close Request", JOptionPane.YES_NO_OPTION);
        if (pane == 0) {
            String sql = "update Request set Status='Closed' where RID =?";
            try {
                pst = conn.prepareStatement(sql);
                String temp = Integer.toString(requestNumber);
                pst.setString(1, temp);
                pst.execute();
                JOptionPane.showMessageDialog(null, "Request has been closed.");
            } catch (SQLException | HeadlessException e) {
                JOptionPane.showMessageDialog(null, e);
            } finally {
                try {
                    rs.close();
                    pst.close();
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, e);
                }
            }

            if ("Doctor".equals(userType)) {
                NewJFrame n = new NewJFrame();
                DoctorView d = new DoctorView(userID);
                d.setVisible(true);
                dispose();
            } else {
                NewJFrame n = new NewJFrame();
                PatientView p = new PatientView(userID);
                p.setVisible(true);
                dispose();
            }
        }
    }

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        try {
            rs.close();
            pst.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        if ("Doctor".equals(userType)) {
            NewJFrame n = new NewJFrame();
            DoctorView d = new DoctorView(userID);
            d.setVisible(true);
            dispose();
        } else {
            NewJFrame n = new NewJFrame();
            PatientView p = new PatientView(userID);
            p.setVisible(true);
            dispose();
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
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RequestConversation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        NewJFrame n = new NewJFrame();
        final DoctorView d = new DoctorView(n.getUsername());
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new RequestConversation(d.getRequestID(), d.getUsername(), d.getUserType()).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify                     
    private javax.swing.JButton addButton;
    private javax.swing.JTextArea addToRequest;
    private javax.swing.JButton backButton;
    private javax.swing.JButton closeButton;
    private javax.swing.JTextArea currentRequest;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration                   
}
