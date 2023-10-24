/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package forms;

import utils.KeyPressCheck;
import utils.CheckInput;
import at.favre.lib.crypto.bcrypt.BCrypt;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.awt.Color;
import models.Account;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Date;
import java.util.concurrent.CompletableFuture;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import models.OperationJson;
import routes.FormRoute;
import utils.AES;
import utils.BaseURL;
import utils.RequestServer;

/**
 *
 * @author dell
 */
public class frmInfo extends javax.swing.JFrame {
    private static Account account;
    private DocumentListener textChangeListener;
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
        gson=new GsonBuilder().setDateFormat("MMM d, yyyy").create();
        inputCheck = new CheckInput();
        keyCheck = new KeyPressCheck();
        setDefaultProgress();
        frmInfo.account = account;
        GroupRadioBox();
        CheckKeyPress();
        TextChangeEvent();
        btnChangePassword.setEnabled(false);
        loadInfo(account);
        getRootPane().setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.cyan)); 
    }
    private void setDefaultProgress(){
        progressLoadImage.setVisible(false);
        progressChangePass.setVisible(false);
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
        btnLogOut = new javax.swing.JButton();
        progressLoadImage = new spinner_progress.SpinnerProgress();
        btnXemKhuonMat = new javax.swing.JButton();
        btnUpdateInfo = new javax.swing.JButton();
        btnAddFace = new javax.swing.JButton();
        btnResetInfo = new javax.swing.JButton();
        txtEmail = new javax.swing.JTextField();
        lblEmail = new javax.swing.JLabel();
        lblDiaChi = new javax.swing.JLabel();
        txtAddress = new javax.swing.JTextField();
        txtPhone = new javax.swing.JTextField();
        lblSoDienThoai = new javax.swing.JLabel();
        lblGioiTinh = new javax.swing.JLabel();
        rbMale = new javax.swing.JRadioButton();
        rbFemale = new javax.swing.JRadioButton();
        rbOther = new javax.swing.JRadioButton();
        dcBrithday = new com.toedter.calendar.JDateChooser();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtFirstName = new javax.swing.JTextField();
        txtLastName = new javax.swing.JTextField();
        lblHo = new javax.swing.JLabel();
        btnChangePassword = new javax.swing.JButton();
        progressChangePass = new spinner_progress.SpinnerProgress();
        cbHienMatKhau = new javax.swing.JCheckBox();
        passfRe_enterNewPassword = new javax.swing.JPasswordField();
        lblNhapLaiMatKhau = new javax.swing.JLabel();
        passfNewPassword = new javax.swing.JPasswordField();
        lblMatKhau = new javax.swing.JLabel();
        passfOldPassword = new javax.swing.JPasswordField();
        lblMatKhauCu = new javax.swing.JLabel();
        lblTaiKhoan = new javax.swing.JLabel();
        txtAccount = new javax.swing.JTextField();
        lblDoiMatKhau = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblThongTinCaNhan.setFont(new java.awt.Font("Bahnschrift", 0, 24)); // NOI18N
        lblThongTinCaNhan.setText("Thông tin cá nhân");
        getContentPane().add(lblThongTinCaNhan, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 20, -1, -1));

        btnLogOut.setFont(new java.awt.Font("Bahnschrift", 0, 14)); // NOI18N
        btnLogOut.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/login.png"))); // NOI18N
        btnLogOut.setText("Đăng xuất");
        btnLogOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogOutActionPerformed(evt);
            }
        });
        getContentPane().add(btnLogOut, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 40));

        progressLoadImage.setForeground(new java.awt.Color(255, 153, 51));
        progressLoadImage.setToolTipText("");
        progressLoadImage.setValue(50);
        progressLoadImage.setIndeterminate(true);
        getContentPane().add(progressLoadImage, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 390, 40, 30));

        btnXemKhuonMat.setFont(new java.awt.Font("Bahnschrift", 0, 14)); // NOI18N
        btnXemKhuonMat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/face-list.png"))); // NOI18N
        btnXemKhuonMat.setText("Xem khuôn mặt");
        btnXemKhuonMat.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        btnXemKhuonMat.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnXemKhuonMat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXemKhuonMatActionPerformed(evt);
            }
        });
        getContentPane().add(btnXemKhuonMat, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 390, 190, 40));

        btnUpdateInfo.setFont(new java.awt.Font("Bahnschrift", 0, 14)); // NOI18N
        btnUpdateInfo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/update-info.png"))); // NOI18N
        btnUpdateInfo.setText("Cập nhật thông tin");
        btnUpdateInfo.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        btnUpdateInfo.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnUpdateInfo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateInfoActionPerformed(evt);
            }
        });
        getContentPane().add(btnUpdateInfo, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 340, 190, 40));

        btnAddFace.setFont(new java.awt.Font("Bahnschrift", 0, 14)); // NOI18N
        btnAddFace.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/add-image.png"))); // NOI18N
        btnAddFace.setText("Thêm khuôn mặt");
        btnAddFace.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        btnAddFace.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnAddFace.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddFaceActionPerformed(evt);
            }
        });
        getContentPane().add(btnAddFace, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 390, 190, 40));

        btnResetInfo.setFont(new java.awt.Font("Bahnschrift", 0, 14)); // NOI18N
        btnResetInfo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/reset.png"))); // NOI18N
        btnResetInfo.setText("Đặt lại thông tin");
        btnResetInfo.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        btnResetInfo.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnResetInfo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetInfoActionPerformed(evt);
            }
        });
        getContentPane().add(btnResetInfo, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 340, 190, 40));
        getContentPane().add(txtEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 300, 260, 30));

        lblEmail.setFont(new java.awt.Font("Bahnschrift", 0, 14)); // NOI18N
        lblEmail.setText("Email:");
        getContentPane().add(lblEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 310, 50, 20));

        lblDiaChi.setFont(new java.awt.Font("Bahnschrift", 0, 14)); // NOI18N
        lblDiaChi.setText("Địa chỉ:");
        getContentPane().add(lblDiaChi, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 270, -1, -1));
        getContentPane().add(txtAddress, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 260, 260, 30));
        getContentPane().add(txtPhone, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 220, 260, 30));

        lblSoDienThoai.setFont(new java.awt.Font("Bahnschrift", 0, 14)); // NOI18N
        lblSoDienThoai.setText("Số điện thoại:");
        getContentPane().add(lblSoDienThoai, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 230, -1, -1));

        lblGioiTinh.setFont(new java.awt.Font("Bahnschrift", 0, 14)); // NOI18N
        lblGioiTinh.setText("Giới tính:");
        getContentPane().add(lblGioiTinh, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 190, -1, -1));

        rbMale.setFont(new java.awt.Font("Bahnschrift", 0, 14)); // NOI18N
        rbMale.setText("Nam");
        getContentPane().add(rbMale, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 190, -1, -1));

        rbFemale.setFont(new java.awt.Font("Bahnschrift", 0, 14)); // NOI18N
        rbFemale.setText("Nữ");
        getContentPane().add(rbFemale, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 190, -1, -1));

        rbOther.setFont(new java.awt.Font("Bahnschrift", 0, 14)); // NOI18N
        rbOther.setText("Khác");
        getContentPane().add(rbOther, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 190, -1, -1));

        dcBrithday.setDateFormatString("dd/MM/yyyy");
        dcBrithday.setMaxSelectableDate(new java.util.Date(253370743280000L));
        getContentPane().add(dcBrithday, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 140, 260, 30));

        jLabel3.setFont(new java.awt.Font("Bahnschrift", 0, 14)); // NOI18N
        jLabel3.setText("Ngày sinh:");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, -1, -1));

        jLabel2.setFont(new java.awt.Font("Bahnschrift", 0, 14)); // NOI18N
        jLabel2.setText("Tên lót/Tên:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, -1, -1));
        getContentPane().add(txtFirstName, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 100, 260, 30));
        getContentPane().add(txtLastName, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 60, 260, 30));

        lblHo.setFont(new java.awt.Font("Bahnschrift", 0, 14)); // NOI18N
        lblHo.setText("Họ:");
        getContentPane().add(lblHo, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, -1, -1));

        btnChangePassword.setFont(new java.awt.Font("Bahnschrift", 0, 14)); // NOI18N
        btnChangePassword.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/reset-password.png"))); // NOI18N
        btnChangePassword.setText("Đổi mật khẩu");
        btnChangePassword.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        btnChangePassword.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnChangePassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChangePasswordActionPerformed(evt);
            }
        });
        getContentPane().add(btnChangePassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 691, 160, 40));

        progressChangePass.setForeground(new java.awt.Color(255, 153, 51));
        progressChangePass.setToolTipText("");
        progressChangePass.setValue(50);
        progressChangePass.setIndeterminate(true);
        getContentPane().add(progressChangePass, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 690, -1, 40));

        cbHienMatKhau.setFont(new java.awt.Font("Bahnschrift", 0, 14)); // NOI18N
        cbHienMatKhau.setText("Hiện mật khẩu");
        cbHienMatKhau.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbHienMatKhauActionPerformed(evt);
            }
        });
        getContentPane().add(cbHienMatKhau, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 660, -1, -1));
        getContentPane().add(passfRe_enterNewPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 620, 270, 30));

        lblNhapLaiMatKhau.setFont(new java.awt.Font("Bahnschrift", 0, 14)); // NOI18N
        lblNhapLaiMatKhau.setText("Nhập lại mật khẩu:");
        getContentPane().add(lblNhapLaiMatKhau, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 630, -1, -1));
        getContentPane().add(passfNewPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 580, 270, 30));

        lblMatKhau.setFont(new java.awt.Font("Bahnschrift", 0, 14)); // NOI18N
        lblMatKhau.setText("Mật khẩu mới:");
        getContentPane().add(lblMatKhau, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 590, -1, -1));
        getContentPane().add(passfOldPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 530, 270, 30));

        lblMatKhauCu.setFont(new java.awt.Font("Bahnschrift", 0, 14)); // NOI18N
        lblMatKhauCu.setText("Mật khẩu cũ:");
        getContentPane().add(lblMatKhauCu, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 540, -1, -1));

        lblTaiKhoan.setFont(new java.awt.Font("Bahnschrift", 0, 14)); // NOI18N
        lblTaiKhoan.setText("Tài khoản:");
        getContentPane().add(lblTaiKhoan, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 500, -1, -1));
        getContentPane().add(txtAccount, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 490, 270, 30));

        lblDoiMatKhau.setFont(new java.awt.Font("Bahnschrift", 0, 24)); // NOI18N
        lblDoiMatKhau.setText("Đôỉ mật khẩu");
        getContentPane().add(lblDoiMatKhau, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 440, -1, 40));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/register.jpg"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void loadInfo(Account acc){
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
        if(txtLastName.getText().isEmpty() ||  txtFirstName.getText().isEmpty() || txtPhone.getText().isEmpty() || 
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
    
    
    
    private void btnChangePasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChangePasswordActionPerformed
        progressChangePass.setVisible(true);
        //Thực hiện đổi mật khẩu dưới nền để tránh chèn luồng giao diện
        SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                changePassword();
                return null;
            }
        };
        worker.execute();
    }//GEN-LAST:event_btnChangePasswordActionPerformed
    private void changePassword() {
        //Thử nghiệm phương thức bất đồng bộ async
        CompletableFuture.runAsync(()->{
            try{
                AES aes=new AES();
                String oldPass = new String(passfOldPassword.getPassword());
                String newPass = new String(passfNewPassword.getPassword());
                String reEnterPass = new String(passfRe_enterNewPassword.getPassword());
                if(!newPass.equals(reEnterPass)){
                    JOptionPane.showMessageDialog(this, "Mật khẩu nhập lại chưa chính xác!","Lỗi",0);
                    return;
                }
                newPass = BCrypt.withDefaults().hashToString(12, newPass.toCharArray());
                //Lấy public key của server
                String publicKeyReceived=RequestServer.requestPublicKey();
                //Mã hoá mật khẩu với public key của server
                String passwordValidate=oldPass+"-"+newPass;
                String encryptPassword=aes.encrypt(passwordValidate, aes.getPublicKeyFromString(publicKeyReceived));
                String encryptAccountID=aes.encrypt(account.getID_User(), aes.getPublicKeyFromString(publicKeyReceived));
                //Tạo đối tượng json để chứa dữ liệu gửi đi
                OperationJson changePassRequestJson=new OperationJson();
                changePassRequestJson.setOperation("change-password@"+encryptAccountID);
                changePassRequestJson.setPublicKey(aes.encodePublicKey(aes.getPublicKey()));
                changePassRequestJson.setData(encryptPassword);
                //Gửi request lên server và nhận kết quả trả về
                String response = RequestServer.sendRequestToServer(changePassRequestJson);
                System.out.println("Ket qua tra ve:::"+response);
                OperationJson receivedResponse = gson.fromJson(response, OperationJson.class);
                String result=receivedResponse.getOperation();
                //Bắt các trường hợp đổi mật khẩu thành công và thất bại
                switch (result) {
                    case "Success":
                        JOptionPane.showMessageDialog(this, "Đổi mật khẩu thành công");
                        FormRoute.openFormLogin(this);
                        account=null;
                        break;
                    case "MissingField":
                        JOptionPane.showMessageDialog(this, "Vui lòng điền mật khẩu cũ và mật khẩu mới","Lỗi",0);
                        break;
                    case "WrongPass":
                        JOptionPane.showMessageDialog(this, "Mật khẩu cũ không đúng","Lỗi",0);
                        break;
                    case "Unknown":
                        JOptionPane.showMessageDialog(this, "Lỗi chưa xác định","Lỗi",0);
                        break;
                    default:
                        JOptionPane.showMessageDialog(this, "Lỗi chưa xác định","Lỗi",0);
                        break;
                }
                
                
            }
            catch(Exception ex) {
                JOptionPane.showMessageDialog(this, "Lỗi chưa xác định:"+ex,"Lỗi",0);
            }
        }).whenComplete((result, exception) -> {
            //Phương thức bất đồng bộ được thực hiện xong và bắt các ngoại lệ nếu có
            if (exception == null) {
                setDefaultProgress();
            } else {
                SwingUtilities.invokeLater(() -> {
                    JOptionPane.showMessageDialog(this, "Lỗi: " + exception.getMessage(), "Cảnh báo", JOptionPane.ERROR_MESSAGE);
                });
            }
        });
    }
    private void btnLogOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogOutActionPerformed

        FormRoute.openFormLogin(this);
        account=null;
    }//GEN-LAST:event_btnLogOutActionPerformed

    private void btnUpdateInfoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateInfoActionPerformed
        
        try{                                              
            
            if(!inputCheck.CheckBrithday(dcBrithday.getDate()))
                return;
            if(!inputCheck.CheckSDT(txtPhone.getText()))
                return;
            if(!inputCheck.CheckEmail(txtEmail.getText()))
                return;
            AES aes=new AES();
            //Lấy public key của server
            
            String publicKeyReceived=RequestServer.requestPublicKey();
            //cập nhật các trường dữ liệu của account nếu có
            account.setFrist_Name(txtFirstName.getText());
            account.setLast_Name(txtLastName.getText());
            account.setBrithday(dcBrithday.getDate());
            account.setAddress(txtAddress.getText());
            account.setEmail(txtEmail.getText());
            account.setPhone(txtPhone.getText());
            account.setGender(chonGioiTinh());
            account.setAccount(txtAccount.getText());
            //Mã hoá account trước khi gửi lên server
            String encryptAccount=aes.encrypt(gson.toJson(account), aes.getPublicKeyFromString(publicKeyReceived));
            //Tạo đối tượng json để chứa dữ liệu gửi đi
            OperationJson updateAccountRequestJson=new OperationJson();
            updateAccountRequestJson.setOperation("update");
            updateAccountRequestJson.setPublicKey(aes.encodePublicKey(aes.getPublicKey()));
            updateAccountRequestJson.setData(encryptAccount);
            //Gửi request lên server và nhận kết quả trả về
            String response = RequestServer.sendRequestToServer(updateAccountRequestJson);
            OperationJson responseFromServer=gson.fromJson(response, OperationJson.class);
            String responeResult=responseFromServer.getOperation();
            switch (responeResult) {
                case "Success":
                    JOptionPane.showMessageDialog(this, "Cập nhật tài khoản thành công");
                    String decryptAccount=aes.decrypt(responseFromServer.getData().toString(), aes.getPrivateKey());
                    Account updatedAccount=gson.fromJson(decryptAccount, Account.class);
                    loadInfo(updatedAccount);
                    break;
                case "UpdateFail":
                    JOptionPane.showMessageDialog(this, "Cập nhật thất bại","Lỗi",0);
                    break;
                case "DateTimeFormat":
                    JOptionPane.showMessageDialog(this, "Lỗi định dạng ngày tháng!","Lỗi",0);
                    break;
                default:
                    JOptionPane.showMessageDialog(this, "Lỗi chưa xác định","Lỗi",0);
                    break;
            }
        }
        catch(Exception ex){
             JOptionPane.showMessageDialog(this, "Lỗi chưa xác định:"+ex,"Lỗi",0);     
        }        
    }//GEN-LAST:event_btnUpdateInfoActionPerformed

    private void btnResetInfoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetInfoActionPerformed
        // TODO add your handling code here:
        loadInfo(account);
    }//GEN-LAST:event_btnResetInfoActionPerformed
   
    private void btnAddFaceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddFaceActionPerformed
        // TODO add your handling code here:
        frmCameraAcess open = new frmCameraAcess(account,"capNhat");
        open.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnAddFaceActionPerformed

    private void btnXemKhuonMatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXemKhuonMatActionPerformed
        // TODO add your handling code here:f
        progressLoadImage.setVisible(true);
        SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                frmLoadImageData open = new frmLoadImageData(account);
                open.setVisible(true);
                frmInfo.this.dispose();
                return null;
            }
        };
        worker.execute();
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
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
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
    private spinner_progress.SpinnerProgress progressChangePass;
    private spinner_progress.SpinnerProgress progressLoadImage;
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
