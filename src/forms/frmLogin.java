/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package forms;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.awt.Color;
import models.Account;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import models.OperationJson;
import routes.FormRoute;
import utils.BaseURL;
import utils.EncodeDecode;

/**
 *
 * @author dell
 */
public class frmLogin extends javax.swing.JFrame {
    private DocumentListener textChangeListener;
    private final Gson gson;
    /**
     * Creates new form FrmLogin
     */
    public frmLogin() {
        initComponents();        
        TextChangeEvent();
        btnLogin.setEnabled(false);     
        gson=new GsonBuilder().setDateFormat("MMM d, yyyy").create();
        getRootPane().setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.cyan)); 
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtAccount = new javax.swing.JTextField();
        btnLogin = new javax.swing.JButton();
        cbShowPassword = new javax.swing.JCheckBox();
        passfPassword = new javax.swing.JPasswordField();
        btnResister = new javax.swing.JButton();
        btnNhanDienTest = new javax.swing.JButton();
        btnChangeServer = new javax.swing.JButton();
        lbLoginBanner = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(136, 136, 189));

        jLabel1.setFont(new java.awt.Font("Bahnschrift", 0, 14)); // NOI18N
        jLabel1.setText("Tài khoản:");

        jLabel2.setFont(new java.awt.Font("Bahnschrift", 0, 14)); // NOI18N
        jLabel2.setText("Mật khẩu:");

        btnLogin.setFont(new java.awt.Font("Bahnschrift", 0, 18)); // NOI18N
        btnLogin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/login.png"))); // NOI18N
        btnLogin.setText("Đăng nhập");
        btnLogin.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        btnLogin.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });

        cbShowPassword.setFont(new java.awt.Font("Bahnschrift", 0, 14)); // NOI18N
        cbShowPassword.setText("Hiện mật khẩu");
        cbShowPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbShowPasswordActionPerformed(evt);
            }
        });

        btnResister.setFont(new java.awt.Font("Bahnschrift", 0, 18)); // NOI18N
        btnResister.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/register.png"))); // NOI18N
        btnResister.setText("Đăng ký");
        btnResister.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        btnResister.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnResister.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResisterActionPerformed(evt);
            }
        });

        btnNhanDienTest.setFont(new java.awt.Font("Bahnschrift", 0, 18)); // NOI18N
        btnNhanDienTest.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/face-detection.png"))); // NOI18N
        btnNhanDienTest.setText("Nhận diện ");
        btnNhanDienTest.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        btnNhanDienTest.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnNhanDienTest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNhanDienTestActionPerformed(evt);
            }
        });

        btnChangeServer.setFont(new java.awt.Font("Bahnschrift", 0, 18)); // NOI18N
        btnChangeServer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/server.png"))); // NOI18N
        btnChangeServer.setText("Đổi server");
        btnChangeServer.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        btnChangeServer.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnChangeServer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChangeServerActionPerformed(evt);
            }
        });

        lbLoginBanner.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/login_banner.jpg"))); // NOI18N

        jLabel3.setFont(new java.awt.Font("Bahnschrift", 0, 18)); // NOI18N
        jLabel3.setText("Đăng nhập");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(lbLoginBanner)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtAccount, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(btnChangeServer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(btnResister, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGap(25, 25, 25)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(btnLogin, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addComponent(btnNhanDienTest)
                                                .addGap(1, 1, 1))))
                                    .addComponent(passfPassword))
                                .addGap(21, 21, 21))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel1)
                                    .addComponent(cbShowPassword))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(133, 133, 133)
                        .addComponent(jLabel3)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(13, 13, 13)
                .addComponent(txtAccount, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(passfPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(cbShowPassword)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnResister, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNhanDienTest, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnChangeServer, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17))
            .addComponent(lbLoginBanner, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
//        SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
//            @Override
//            protected Void doInBackground() throws Exception {
//                login();
//                return null;
//            }
//        };
//        worker.execute();
        login();
    }//GEN-LAST:event_btnLoginActionPerformed
    private void login(){
        try (Socket socket = new Socket(BaseURL.SERVER_ADDRESS, BaseURL.PORT)){
            // TODO add your handling code here:
            String account = txtAccount.getText();
            //char[] password = passfMatKhau.getPassword();
            String password = new String(passfPassword.getPassword());
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            
            OperationJson operationJson=new OperationJson();
            operationJson.setOperation("login/"+account);
            operationJson.setData(EncodeDecode.encodeToBase64(password));
            String sendJson=gson.toJson(operationJson);
            //Gửi dữ liệu lên server
            out.println(sendJson);
           
            OperationJson receivedJson=gson.fromJson(in.readLine(), OperationJson.class);
            switch (receivedJson.getOperation()) {
                case "Success":
                    JOptionPane.showMessageDialog(this, "Đăng nhập thành công!");
                    String receivedAccountJson=EncodeDecode.decodeBase64FromJson(receivedJson.getData().toString());                  
                    Account acc = gson.fromJson(receivedAccountJson, Account.class);
                    FormRoute.openFormInfo(this, acc);
                    this.dispose();
                    break;
                case "WrongPass":
                    JOptionPane.showMessageDialog(this, "Sai mật khẩu !","Lỗi",0);
                    break;
                case "AccountNotFound":
                    JOptionPane.showMessageDialog(this, "Không tìm thấy tài khoản!","Lỗi",0);
                    break;
                default:
                    JOptionPane.showMessageDialog(this, "Lỗi chưa xác định!","Lỗi",0);
                    break;
            }
            socket.close();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Có lỗi xảy ra!"+ex.toString(),"Lỗi",JOptionPane.ERROR_MESSAGE);
        }
    }
    private void TextChangeEvent(){
        textChangeListener = new DocumentListener(){
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateTextFields();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateTextFields();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateTextFields();
            }
            private void updateTextFields(){
                if(txtAccount.getText().isEmpty() || passfPassword.getText().isEmpty()){
                    btnLogin.setEnabled(false);
                }
                else
                    btnLogin.setEnabled(true);
            }
        };
        txtAccount.getDocument().addDocumentListener(textChangeListener);
        passfPassword.getDocument().addDocumentListener(textChangeListener);
    }
    
    private void cbShowPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbShowPasswordActionPerformed
        // TODO add your handling code here:
        if(cbShowPassword.isSelected())
            passfPassword.setEchoChar((char) 0);
        else
            passfPassword.setEchoChar('\u2022');             
    }//GEN-LAST:event_cbShowPasswordActionPerformed

    private void btnResisterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResisterActionPerformed
        FormRoute.openFormRegister(this);
    }//GEN-LAST:event_btnResisterActionPerformed

    private void btnNhanDienTestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNhanDienTestActionPerformed
        FormRoute.openFormRecognitionTest(this);
    }//GEN-LAST:event_btnNhanDienTestActionPerformed

    private void btnChangeServerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChangeServerActionPerformed
        // TODO add your handling code here:
        FormRoute.opFormChoseServer(this);
    }//GEN-LAST:event_btnChangeServerActionPerformed

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
            java.util.logging.Logger.getLogger(frmLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmLogin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnChangeServer;
    private javax.swing.JButton btnLogin;
    private javax.swing.JButton btnNhanDienTest;
    private javax.swing.JButton btnResister;
    private javax.swing.JCheckBox cbShowPassword;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel lbLoginBanner;
    private javax.swing.JPasswordField passfPassword;
    private javax.swing.JTextField txtAccount;
    // End of variables declaration//GEN-END:variables
}
