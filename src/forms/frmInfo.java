/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package forms;

import utils.KeyPressCheck;
import utils.CheckInput;
import at.favre.lib.crypto.bcrypt.BCrypt;
import com.google.gson.Gson;
import models.Account;
import db_connection.DBAccess;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Base64;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import models.OperationJson;
import utils.BaseURL;
import utils.EncodeDecode;

/**
 *
 * @author dell
 */
public class frmInfo extends javax.swing.JFrame {
    private static Account account;
    private DocumentListener textChangeListener;
    private final DBAccess  access; 
    private final CheckInput inputCheck;
    private final KeyPressCheck keyCheck;
    private final Gson gson;
    /**
     * Creates new form FrmInfo
     * @param account
     */
    public frmInfo(Account account) {
        initComponents();
        txtAccount.setEnabled(false);
        access = new DBAccess();
        gson=new Gson();
        inputCheck = new CheckInput();
        keyCheck = new KeyPressCheck();
        frmInfo.account = account;
        GroupRadioBox();
        CheckKeyPress();
        TextChangeEvent();
        btnChangePassword.setEnabled(false);
        LoadInfo(account);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btngRadioBox = new javax.swing.ButtonGroup();
        lblThongTinCaNhan = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
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
        btnUpdateInfo = new javax.swing.JButton();
        btnResetInfo = new javax.swing.JButton();
        btnAddFace = new javax.swing.JButton();
        btnXemKhuonMat = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        lblMatKhauCu = new javax.swing.JLabel();
        lblMatKhau = new javax.swing.JLabel();
        lblNhapLaiMatKhau = new javax.swing.JLabel();
        cbHienMatKhau = new javax.swing.JCheckBox();
        btnChangePassword = new javax.swing.JButton();
        passfRe_enterNewPassword = new javax.swing.JPasswordField();
        passfOldPassword = new javax.swing.JPasswordField();
        passfNewPassword = new javax.swing.JPasswordField();
        lblDoiMatKhau = new javax.swing.JLabel();
        lblTaiKhoan = new javax.swing.JLabel();
        txtAccount = new javax.swing.JTextField();
        btnLogOut = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblThongTinCaNhan.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblThongTinCaNhan.setText("Thông tin cá nhân");

        lblGioiTinh.setText("Giới tính:");

        lblSoDienThoai.setText("Số điện thoại:");

        lblDiaChi.setText("Địa chỉ:");

        lblEmail.setText("Email:");

        lblHo.setText("Họ:");

        jLabel2.setText("Tên lót/Tên:");

        jLabel3.setText("Ngày sinh:");

        dcBrithday.setDateFormatString("dd/MM/yyyy");
        dcBrithday.setMaxSelectableDate(new java.util.Date(253370743280000L));

        rbFemale.setText("Nữ");

        rbOther.setText("Khác");

        rbMale.setText("Nam");

        btnUpdateInfo.setText("Cập nhật thông tin");
        btnUpdateInfo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateInfoActionPerformed(evt);
            }
        });

        btnResetInfo.setText("Đặt lại thông tin");
        btnResetInfo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetInfoActionPerformed(evt);
            }
        });

        btnAddFace.setText("Thêm khuôn mặt");
        btnAddFace.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddFaceActionPerformed(evt);
            }
        });

        btnXemKhuonMat.setText("Xem khuôn mặt");
        btnXemKhuonMat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXemKhuonMatActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(lblHo)
                            .addComponent(jLabel3)
                            .addComponent(lblGioiTinh))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtLastName)
                                .addComponent(txtFirstName)
                                .addComponent(dcBrithday, javax.swing.GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(rbMale)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(rbFemale)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                                .addComponent(rbOther))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(lblSoDienThoai)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtPhone, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblDiaChi))
                                .addGap(42, 42, 42)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btnAddFace)
                                .addGap(18, 18, 18)
                                .addComponent(btnXemKhuonMat, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btnResetInfo)
                                .addGap(18, 18, 18)
                                .addComponent(btnUpdateInfo)))
                        .addGap(6, 6, 6)))
                .addGap(42, 42, 42))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblHo)
                    .addComponent(txtLastName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(dcBrithday, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rbMale)
                            .addComponent(rbFemale)
                            .addComponent(rbOther))
                        .addGap(6, 6, 6))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(lblGioiTinh)
                        .addGap(18, 18, 18)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSoDienThoai)
                    .addComponent(txtPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDiaChi)
                    .addComponent(txtAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblEmail)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnUpdateInfo)
                    .addComponent(btnResetInfo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnXemKhuonMat)
                    .addComponent(btnAddFace)))
        );

        lblMatKhauCu.setText("Mật khẩu cũ:");

        lblMatKhau.setText("Mật khẩu mới:");

        lblNhapLaiMatKhau.setText("Nhập lại mật khẩu:");

        cbHienMatKhau.setText("Hiện mật khẩu");
        cbHienMatKhau.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbHienMatKhauActionPerformed(evt);
            }
        });

        btnChangePassword.setText("Đổi mật khẩu");
        btnChangePassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChangePasswordActionPerformed(evt);
            }
        });

        lblDoiMatKhau.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblDoiMatKhau.setText("Đôỉ mật khẩu");

        lblTaiKhoan.setText("Tài khoản:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(16, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(cbHienMatKhau)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 57, Short.MAX_VALUE)
                        .addComponent(btnChangePassword)
                        .addGap(33, 33, 33))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(lblNhapLaiMatKhau)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lblMatKhau)
                                        .addComponent(lblMatKhauCu))
                                    .addGap(22, 22, 22)))
                            .addComponent(lblTaiKhoan))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtAccount)
                            .addComponent(passfRe_enterNewPassword, javax.swing.GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)
                            .addComponent(passfOldPassword, javax.swing.GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)
                            .addComponent(passfNewPassword))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addComponent(lblDoiMatKhau)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblDoiMatKhau)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTaiKhoan)
                    .addComponent(txtAccount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(passfOldPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblMatKhauCu))
                .addGap(24, 24, 24)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(passfNewPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblMatKhau))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNhapLaiMatKhau)
                    .addComponent(passfRe_enterNewPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(cbHienMatKhau)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(btnChangePassword)
                        .addGap(14, 14, 14))))
        );

        btnLogOut.setText("Đăng xuất");
        btnLogOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogOutActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnLogOut)
                .addGap(45, 45, 45)
                .addComponent(lblThongTinCaNhan))
            .addGroup(layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(lblThongTinCaNhan))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnLogOut)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(58, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void LoadInfo(Account acc){
        try{
            Date brithday = acc.getBrithday();   
            txtLastName.setText(acc.getLast_Name());
            txtFirstName.setText(acc.getFrist_Name());
            dcBrithday.setDate(brithday);
            String gender = acc.getGender();
            switch (gender) {
                case "Nam":{
                    rbMale.setSelected(true);
                    break;
                }
                case "Nữ":{
                    rbFemale.setSelected(true);
                    break;
                }
                case "Khác":{
                    rbOther.setSelected(true);
                    break;
                }
                default:
                    break;
            }
            txtPhone.setText(acc.getPhone());
            txtAddress.setText(acc.getAddress());
            txtEmail.setText(acc.getEmail());
            txtAccount.setText(acc.getAccount());
        }
        catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex);
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
                UpdateInfoButtonStatus();
                ChangePasswordButtonStatus();
            }
        };
        txtLastName.getDocument().addDocumentListener(textChangeListener);
        txtFirstName.getDocument().addDocumentListener(textChangeListener);
        txtPhone.getDocument().addDocumentListener(textChangeListener);
        txtAddress.getDocument().addDocumentListener(textChangeListener);
        txtEmail.getDocument().addDocumentListener(textChangeListener);
        txtAccount.getDocument().addDocumentListener(textChangeListener);
        passfOldPassword.getDocument().addDocumentListener(textChangeListener);
        passfNewPassword.getDocument().addDocumentListener(textChangeListener);
        passfRe_enterNewPassword.getDocument().addDocumentListener(textChangeListener);
    }
    
    private void CheckKeyPress(){
        txtLastName.addKeyListener(keyCheck.OnlyCharTextField());
        txtFirstName.addKeyListener(keyCheck.OnlyCharTextField());
        txtPhone.addKeyListener(keyCheck.OnlyNumberTextField());
    }
    
    private void UpdateInfoButtonStatus(){
        if(txtLastName.getText().isEmpty() || txtFirstName.getText().isEmpty() || txtPhone.getText().isEmpty() || 
            txtPhone.getText().isEmpty() || txtAddress.getText().isEmpty() || txtEmail.getText().isEmpty())
             btnUpdateInfo.setEnabled(false);
        else 
            btnUpdateInfo.setEnabled(true);
    }
    
    private void ChangePasswordButtonStatus(){
        if(passfOldPassword.getText().isEmpty() || passfNewPassword.getText().isEmpty() || passfRe_enterNewPassword.getText().isEmpty() )
            btnChangePassword.setEnabled(false);
        else 
            btnChangePassword.setEnabled(true);
    }
    
    private String chonGioiTinh(){
        if(rbMale.isSelected())
            return "Nam";
        if(rbFemale.isSelected())
            return "Nữ";
        if(rbOther.isSelected())
            return "Khác";       
        JOptionPane.showMessageDialog(null, "Chưa chọn giới tính");
        return null;
    }
    
    private void GroupRadioBox(){
        btngRadioBox.add(rbMale);
        btngRadioBox.add(rbFemale);
        btngRadioBox.add(rbOther);
    }
    
    private void cbHienMatKhauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbHienMatKhauActionPerformed
        // TODO add your handling code here:
        if(cbHienMatKhau.isSelected()){
            passfOldPassword.setEchoChar((char) 0);
            passfNewPassword.setEchoChar((char) 0);
            passfRe_enterNewPassword.setEchoChar((char) 0);
        }
        else{
            passfOldPassword.setEchoChar('\u2022');
            passfNewPassword.setEchoChar('\u2022');
            passfRe_enterNewPassword.setEchoChar('\u2022');
        }

    }//GEN-LAST:event_cbHienMatKhauActionPerformed
    
    private void ReloadInfo(String account){
        Account acc = access.getUser(account);
        LoadInfo(acc);
    }
    
    private void LogOut(){
        frmLogin open = new frmLogin();
        open.setVisible(true);
        this.dispose();
    }
    
    private void btnChangePasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChangePasswordActionPerformed
        String oldPass = new String(passfOldPassword.getPassword());
        String newPass = new String(passfNewPassword.getPassword());
        String reEnterPass = new String(passfRe_enterNewPassword.getPassword());
        OperationJson sendJson=new OperationJson();
        sendJson.setOperation("change-password/"+account.getID_User());
        if(!inputCheck.CheckPassword(newPass, reEnterPass))
            return;
        try{
            Socket socket = new Socket(BaseURL.SERVER_ADDRESS, BaseURL.PORT); 

            // Khởi tạo InputStream và output Stream để giao tiếp với server
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            newPass =  BCrypt.withDefaults().hashToString(12, newPass.toCharArray());
            String passwordValidate=oldPass+"-"+newPass;
            String encodedPasswordValidate=EncodeDecode.encodeToBase64(passwordValidate);
            sendJson.setData(encodedPasswordValidate);
            out.println(gson.toJson(sendJson));
            String result=EncodeDecode.decodeBase64FromJson(in.readLine());
            switch (result) {
                case "Success":
                    JOptionPane.showMessageDialog(this, "Đổi mật khẩu thành công!");
                    frmLogin login=new frmLogin();
                    login.setVisible(true);
                    account=null;
                    this.dispose();
                    break;
                case "Unknown":
                    JOptionPane.showMessageDialog(this, "Lỗi chưa chưa xác định!"+result,"Cảnh báo",0);
                    break;
                default:
                    if(result.equals("Account not found")){
                        JOptionPane.showMessageDialog(this, "Không tìm thấy tài khoản !","Cảnh báo",0);
                    }
                    else if(result.equals("WrongOldOrNewPass")||result.equals("WrongPass")){
                        JOptionPane.showMessageDialog(this, "Bạn nhập sai mật khẩu cũ hoặc mật khẩu mới!","Cảnh báo",0);
                    }else{
                        JOptionPane.showMessageDialog(this, "Lỗi chưa  xác định!"+result,"Cảnh báo",0);
                    }
                    break;
            }
        }
        catch(IOException ex) {
            JOptionPane.showMessageDialog(this, "Lỗi!"+ex.toString(),"Cảnh báo",0);
        }
    }//GEN-LAST:event_btnChangePasswordActionPerformed

    private void btnLogOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogOutActionPerformed
        // TODO add your handling code here:
        LogOut();
    }//GEN-LAST:event_btnLogOutActionPerformed

    private void btnUpdateInfoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateInfoActionPerformed
        // TODO add your handling code here:
        if(!inputCheck.CheckBrithday(dcBrithday.getDate()))
            return;
        if(!inputCheck.CheckSDT(txtPhone.getText()))
            return;
        if(!inputCheck.CheckEmail(txtEmail.getText()))
            return;
        try{
            Socket socket = new Socket(BaseURL.SERVER_ADDRESS, BaseURL.PORT); 

            // Khởi tạo InputStream và output Stream để giao tiếp với server
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            account.setFrist_Name(txtFirstName.getText());
            account.setLast_Name(txtLastName.getText());
            account.setBrithday(dcBrithday.getDate());
            account.setAddress(txtAddress.getText());
            account.setEmail(txtEmail.getText());
            account.setPhone(txtPhone.getText());
            account.setGender(chonGioiTinh());
            account.setAccount(txtAccount.getText());
            OperationJson operationJson=new OperationJson();
            operationJson.setOperation("update");
            String accountToJson=gson.toJson(account);
            operationJson.setData(EncodeDecode.encodeToBase64(accountToJson));
            String sendJson=gson.toJson(operationJson);
            out.println(sendJson);
            String respone=EncodeDecode.decodeBase64FromJson(in.readLine());
            if(respone.equals("Success"))
            {
                 JOptionPane.showMessageDialog(null, "Cập nhật thông tin thành công");
            }
            else
            {
                 JOptionPane.showMessageDialog(null, "Có lỗi xảy ra!\n"+"Lỗi :"+respone,"Lỗi",0);
            }
            ReloadInfo(txtAccount.getText());
        }
        catch(IOException ex){
            JOptionPane.showMessageDialog(null, ex);            
        }        
    }//GEN-LAST:event_btnUpdateInfoActionPerformed

    private void btnResetInfoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetInfoActionPerformed
        // TODO add your handling code here:
        ReloadInfo(txtAccount.getText());
    }//GEN-LAST:event_btnResetInfoActionPerformed

    private void btnAddFaceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddFaceActionPerformed
        // TODO add your handling code here:
        frmCameraAcess open = new frmCameraAcess(account,"capNhat");
        open.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnAddFaceActionPerformed

    private void btnXemKhuonMatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXemKhuonMatActionPerformed
        // TODO add your handling code here:f
        frmLoadImageData open = new frmLoadImageData(account);
        open.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnXemKhuonMatActionPerformed

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
            java.util.logging.Logger.getLogger(frmInfo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmInfo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmInfo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmInfo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmInfo(account).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddFace;
    private javax.swing.JButton btnChangePassword;
    private javax.swing.JButton btnLogOut;
    private javax.swing.JButton btnResetInfo;
    private javax.swing.JButton btnUpdateInfo;
    private javax.swing.JButton btnXemKhuonMat;
    private javax.swing.ButtonGroup btngRadioBox;
    private javax.swing.JCheckBox cbHienMatKhau;
    private com.toedter.calendar.JDateChooser dcBrithday;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lblDiaChi;
    private javax.swing.JLabel lblDoiMatKhau;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblGioiTinh;
    private javax.swing.JLabel lblHo;
    private javax.swing.JLabel lblMatKhau;
    private javax.swing.JLabel lblMatKhauCu;
    private javax.swing.JLabel lblNhapLaiMatKhau;
    private javax.swing.JLabel lblSoDienThoai;
    private javax.swing.JLabel lblTaiKhoan;
    private javax.swing.JLabel lblThongTinCaNhan;
    private javax.swing.JPasswordField passfNewPassword;
    private javax.swing.JPasswordField passfOldPassword;
    private javax.swing.JPasswordField passfRe_enterNewPassword;
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
