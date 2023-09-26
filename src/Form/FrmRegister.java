/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Form;

import at.favre.lib.crypto.bcrypt.BCrypt;
import facial_recognition.Account;
import facial_recognition.DBAccess;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 *
 * @author dell
 */
public class FrmRegister extends javax.swing.JFrame {
    private Account acc;
    private DBAccess access; 
    private DocumentListener textChangeListener;
    private CheckInput inputCheck;
    private KeyPressCheck keyCheck;
    /**
     * Creates new form FrmRegister
     */
    public FrmRegister() {        
        initComponents();
        CreateAccountFieldStatus(false);
        TextChangeEvent();
        SetDateNow();
        CheckKeyPress();
        access = new DBAccess();
        inputCheck = new CheckInput();
        keyCheck = new KeyPressCheck();
        GroupRadioBox();
        rbMale.setSelected(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btgRadioBox = new javax.swing.ButtonGroup();
        pnlInfo = new javax.swing.JPanel();
        txtEmail = new javax.swing.JTextField();
        txtAddress = new javax.swing.JTextField();
        txtPhone = new javax.swing.JTextField();
        txtFirstName = new javax.swing.JTextField();
        txtLastName = new javax.swing.JTextField();
        lblGioiTinh = new javax.swing.JLabel();
        lblSoDienThoai = new javax.swing.JLabel();
        lblDiaChi = new javax.swing.JLabel();
        lblEmail = new javax.swing.JLabel();
        lblHo = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        dcBrithday = new com.toedter.calendar.JDateChooser();
        rbFemale = new javax.swing.JRadioButton();
        rbOther = new javax.swing.JRadioButton();
        rbMale = new javax.swing.JRadioButton();
        pnlAccount = new javax.swing.JPanel();
        lblTaiKhoan = new javax.swing.JLabel();
        lblMatKhau = new javax.swing.JLabel();
        lblNhapLaiMatKhau = new javax.swing.JLabel();
        txtAccount = new javax.swing.JTextField();
        cbShowPassword = new javax.swing.JCheckBox();
        btnCreateAccount = new javax.swing.JButton();
        passfRe_enterPassword = new javax.swing.JPasswordField();
        passfPassword = new javax.swing.JPasswordField();
        lblTaoTaiKhoan = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        btnTroVe = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        txtEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEmailActionPerformed(evt);
            }
        });

        txtLastName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtLastNameKeyPressed(evt);
            }
        });

        lblGioiTinh.setText("Giới tính:");

        lblSoDienThoai.setText("Số điện thoại:");

        lblDiaChi.setText("Địa chỉ:");

        lblEmail.setText("Email:");

        lblHo.setText("Họ:");

        jLabel2.setText("Tên lót/Tên:");

        jLabel3.setText("Ngày sinh:");

        rbFemale.setText("Nữ");

        rbOther.setText("Khác");

        rbMale.setText("Nam");

        javax.swing.GroupLayout pnlInfoLayout = new javax.swing.GroupLayout(pnlInfo);
        pnlInfo.setLayout(pnlInfoLayout);
        pnlInfoLayout.setHorizontalGroup(
            pnlInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInfoLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(pnlInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlInfoLayout.createSequentialGroup()
                        .addGroup(pnlInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(lblHo)
                            .addComponent(jLabel3)
                            .addComponent(lblGioiTinh))
                        .addGap(18, 18, 18)
                        .addGroup(pnlInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtLastName)
                                .addComponent(txtFirstName)
                                .addComponent(dcBrithday, javax.swing.GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE))
                            .addGroup(pnlInfoLayout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(rbMale)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(rbFemale)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(rbOther))))
                    .addGroup(pnlInfoLayout.createSequentialGroup()
                        .addGroup(pnlInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlInfoLayout.createSequentialGroup()
                                .addGroup(pnlInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblDiaChi))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(pnlInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtAddress)
                                    .addComponent(txtEmail, javax.swing.GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE)))
                            .addGroup(pnlInfoLayout.createSequentialGroup()
                                .addComponent(lblSoDienThoai)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtPhone, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(6, 6, 6)))
                .addGap(42, 42, 42))
        );
        pnlInfoLayout.setVerticalGroup(
            pnlInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInfoLayout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(pnlInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblHo)
                    .addComponent(txtLastName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(dcBrithday, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlInfoLayout.createSequentialGroup()
                        .addGroup(pnlInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rbMale)
                            .addComponent(rbFemale)
                            .addComponent(rbOther))
                        .addGap(6, 6, 6))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlInfoLayout.createSequentialGroup()
                        .addComponent(lblGioiTinh)
                        .addGap(18, 18, 18)))
                .addGroup(pnlInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSoDienThoai)
                    .addComponent(txtPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDiaChi)
                    .addComponent(txtAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblEmail)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        lblTaiKhoan.setText("Tài khoản:");

        lblMatKhau.setText("Mật khẩu:");

        lblNhapLaiMatKhau.setText("Nhập lại mật khẩu:");

        cbShowPassword.setText("Hiện mật khẩu");
        cbShowPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbShowPasswordActionPerformed(evt);
            }
        });

        btnCreateAccount.setText("Tạo tài khoản");
        btnCreateAccount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateAccountActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlAccountLayout = new javax.swing.GroupLayout(pnlAccount);
        pnlAccount.setLayout(pnlAccountLayout);
        pnlAccountLayout.setHorizontalGroup(
            pnlAccountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAccountLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlAccountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlAccountLayout.createSequentialGroup()
                        .addComponent(cbShowPassword)
                        .addGap(45, 45, 45)
                        .addComponent(btnCreateAccount))
                    .addGroup(pnlAccountLayout.createSequentialGroup()
                        .addGroup(pnlAccountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblNhapLaiMatKhau)
                            .addGroup(pnlAccountLayout.createSequentialGroup()
                                .addGroup(pnlAccountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lblMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblTaiKhoan))
                                .addGap(43, 43, 43)))
                        .addGroup(pnlAccountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(pnlAccountLayout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addGroup(pnlAccountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(passfPassword, javax.swing.GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)
                                    .addComponent(txtAccount)))
                            .addGroup(pnlAccountLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(passfRe_enterPassword)))))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        pnlAccountLayout.setVerticalGroup(
            pnlAccountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAccountLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(pnlAccountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlAccountLayout.createSequentialGroup()
                        .addGroup(pnlAccountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblTaiKhoan)
                            .addComponent(txtAccount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26)
                        .addGroup(pnlAccountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblMatKhau)
                            .addComponent(passfPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(45, 45, 45))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlAccountLayout.createSequentialGroup()
                        .addGap(96, 96, 96)
                        .addGroup(pnlAccountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblNhapLaiMatKhau)
                            .addComponent(passfRe_enterPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(14, 14, 14)
                .addGroup(pnlAccountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbShowPassword)
                    .addComponent(btnCreateAccount))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        lblTaoTaiKhoan.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblTaoTaiKhoan.setText("Tạo tài khoản");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel4.setText("Nhập thông tin cá nhân");

        btnTroVe.setText("Trở về");
        btnTroVe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTroVeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(74, 74, 74)
                .addComponent(pnlInfo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnTroVe)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                        .addComponent(jLabel4)
                        .addGap(84, 84, 84))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(pnlAccount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(66, 66, 66))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblTaoTaiKhoan)
                .addGap(149, 149, 149))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(btnTroVe))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlInfo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTaoTaiKhoan)
                .addGap(18, 18, 18)
                .addComponent(pnlAccount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    /*INPUT STATUS*/
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
                if(txtLastName.getText().isEmpty() || txtFirstName.getText().isEmpty() || txtPhone.getText().isEmpty() || 
                   txtPhone.getText().isEmpty() || txtAddress.getText().isEmpty() || txtEmail.getText().isEmpty() || ChonGioiTinh() == null)
                    CreateAccountFieldStatus(false);
                else 
                    CreateAccountFieldStatus(true);                  
            }
        };
        txtLastName.getDocument().addDocumentListener(textChangeListener);
        txtFirstName.getDocument().addDocumentListener(textChangeListener);
        txtPhone.getDocument().addDocumentListener(textChangeListener);
        txtAddress.getDocument().addDocumentListener(textChangeListener);
        txtEmail.getDocument().addDocumentListener(textChangeListener);
        txtAccount.getDocument().addDocumentListener(textChangeListener);
        passfPassword.getDocument().addDocumentListener(textChangeListener);
        passfRe_enterPassword.getDocument().addDocumentListener(textChangeListener);
    }
    
    private void CreateAccountFieldStatus(boolean value){
        txtAccount.setEnabled(value);
        passfPassword.setEnabled(value);
        passfRe_enterPassword.setEnabled(value);
        cbShowPassword.setEnabled(value);
        if(!value){
            btnCreateAccount.setEnabled(value);
            return;
        }            
        if(txtAccount.getText().isEmpty() || passfPassword.getText().isBlank() || passfRe_enterPassword.getText().isBlank())
            btnCreateAccount.setEnabled(false);
        else
            btnCreateAccount.setEnabled(true);
    }
    
     private void GroupRadioBox(){
        btgRadioBox.add(rbMale);
        btgRadioBox.add(rbFemale);
        btgRadioBox.add(rbOther);
    }
     
     private void SetDateNow(){
         Calendar calendar = Calendar.getInstance();
         dcBrithday.setDate(calendar.getTime());
     }
    private void txtEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEmailActionPerformed
    
   
    /*CHECK INPUT*/
    private String ChonGioiTinh(){
        if(rbMale.isSelected())
            return "Nam";
        if(rbFemale.isSelected())
            return "Nữ";
        if(rbOther.isSelected())
            return "Khác";       
        JOptionPane.showMessageDialog(null, "Chưa chọn giới tính");
        return null;
    }
    
    private void CheckKeyPress(){
        txtLastName.addKeyListener(keyCheck.OnlyCharTextField());
        txtFirstName.addKeyListener(keyCheck.OnlyCharTextField());
        txtPhone.addKeyListener(keyCheck.OnlyNumberTextField());
    }    
    
    /*ACTION PERFORM*/
    private void ClearAllText(){
        txtLastName.setText("");
        txtFirstName.setText("");
        txtPhone.setText("");
        txtAddress.setText("");
        txtEmail.setText("");
    }
    
    
    private void btnTroVeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTroVeActionPerformed
        // TODO add your handling code here:
        FrmLogin open = new FrmLogin();
        open.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnTroVeActionPerformed

    private void btnCreateAccountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateAccountActionPerformed
        // TODO add your handling code here:
        String password = new String(passfPassword.getPassword());
        String password1 = new String(passfRe_enterPassword.getPassword());
        if(!inputCheck.CheckBrithday(dcBrithday.getDate()))
            return;
        if(!inputCheck.CheckSDT(txtPhone.getText()))
            return;
        if(!inputCheck.CheckEmail(txtEmail.getText()))
            return;
        if(!inputCheck.CheckPassword(password, password1))
            return;
        try{ 
            password =  BCrypt.withDefaults().hashToString(12, password.toCharArray());
            Date selectedDate = dcBrithday.getDate();
            java.sql.Date birthday = new java.sql.Date(selectedDate.getTime());
            acc = new Account();
            acc.setID_User("");
            acc.setFrist_Name(txtFirstName.getText());
            acc.setLast_Name(txtLastName.getText());
            acc.setBrithday(birthday);
            acc.setGender(ChonGioiTinh());
            acc.setPhone(txtPhone.getText());
            acc.setAddress(txtAddress.getText());
            acc.setEmail(txtEmail.getText());
            acc.setAccount(txtAccount.getText());
            acc.setPassword(password);          
            String query = String.format("INSERT INTO user VALUES ('%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s')",
                                    acc.getID_User(), acc.getAccount(), acc.getPassword(), acc.getFrist_Name(), acc.getLast_Name(),
                                    acc.getBrithday(), acc.getGender(), acc.getPhone(), acc.getAddress(), acc.getEmail());
            access.Register(query);
            ClearAllText();
        }
        catch(Exception ex){
            JOptionPane.showMessageDialog(rootPane, ex,"LOI",1);
        }      
        
    }//GEN-LAST:event_btnCreateAccountActionPerformed

    private void cbShowPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbShowPasswordActionPerformed
        // TODO add your handling code here:
        if(cbShowPassword.isSelected()){
            passfPassword.setEchoChar((char) 0);
            passfRe_enterPassword.setEchoChar((char) 0);
        }            
        else{
            passfPassword.setEchoChar('\u2022');  
            passfRe_enterPassword.setEchoChar('\u2022');
        }
            
    }//GEN-LAST:event_cbShowPasswordActionPerformed

    private void txtLastNameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLastNameKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLastNameKeyPressed

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
            java.util.logging.Logger.getLogger(FrmRegister.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmRegister.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmRegister.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmRegister.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmRegister().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup btgRadioBox;
    private javax.swing.JButton btnCreateAccount;
    private javax.swing.JButton btnTroVe;
    private javax.swing.JCheckBox cbShowPassword;
    private com.toedter.calendar.JDateChooser dcBrithday;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel lblDiaChi;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblGioiTinh;
    private javax.swing.JLabel lblHo;
    private javax.swing.JLabel lblMatKhau;
    private javax.swing.JLabel lblNhapLaiMatKhau;
    private javax.swing.JLabel lblSoDienThoai;
    private javax.swing.JLabel lblTaiKhoan;
    private javax.swing.JLabel lblTaoTaiKhoan;
    private javax.swing.JPasswordField passfPassword;
    private javax.swing.JPasswordField passfRe_enterPassword;
    private javax.swing.JPanel pnlAccount;
    private javax.swing.JPanel pnlInfo;
    private javax.swing.JRadioButton rbFemale;
    private javax.swing.JRadioButton rbMale;
    private javax.swing.JRadioButton rbOther;
    private javax.swing.JTextField txtAccount;
    private javax.swing.JTextField txtAddress;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtFirstName;
    private javax.swing.JTextField txtLastName;
    private javax.swing.JTextField txtPhone;
    // End of variables declaration//GEN-END:variables
}
