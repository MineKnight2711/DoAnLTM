/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package forms;

import models.Account;
import facial_recognition.FaceReconigtion;
import java.awt.Color;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
/**
 *
 * @author dell
 */
public class frmCameraAcess extends javax.swing.JFrame {
    private static Account account;
    private Thread thread;
    private static String frm;
    private FaceReconigtion face;

    /**
     * Creates new form frmCameraAcess
     * @param account
     * @param frm
     */
    public frmCameraAcess(Account account, String frm) {
        initComponents();
        frmCameraAcess.frm = frm;
        frmCameraAcess.account = account; 
        face = new FaceReconigtion();
        face.getDisplaySaveValue(lblAnhChup,lblSoAnh, account);
        face.getProgress(progressSaveImage);
        progressSaveImage.setVisible(false);
        face.setMode(true);
        runableThread();
        getRootPane().setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.cyan));           
    }       
    
    private void runableThread(){
        Runnable frameGrabber = () -> {
                while (true) {
                    face.initializeCamera("Lưu", lblCameraDisplay);
                }
            };
            // Create a new thread for grabbing frames from the camera
            thread = new Thread(frameGrabber); 
            thread.setDaemon(true);  
            thread.start(); 
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        fcChooseImages = new javax.swing.JFileChooser();
        lblCameraDisplay = new javax.swing.JLabel();
        btnMoCamera = new javax.swing.JButton();
        btnBack = new javax.swing.JButton();
        btnLuuKhuonMat = new javax.swing.JButton();
        lblAnhChup = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        lblSoAnh = new javax.swing.JLabel();
        btnChonAnh = new javax.swing.JButton();
        progressSaveImage = new spinner_progress.SpinnerProgress();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnMoCamera.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnMoCamera.setText("Mở camera");
        btnMoCamera.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMoCameraActionPerformed(evt);
            }
        });

        btnBack.setText("Trở về");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        btnLuuKhuonMat.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnLuuKhuonMat.setText("Lưu khuôn mặt");
        btnLuuKhuonMat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLuuKhuonMatActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("/10");

        lblSoAnh.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblSoAnh.setText("0");

        btnChonAnh.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnChonAnh.setText("Chọn ảnh");
        btnChonAnh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChonAnhActionPerformed(evt);
            }
        });

        progressSaveImage.setForeground(new java.awt.Color(255, 153, 51));
        progressSaveImage.setValue(50);
        progressSaveImage.setIndeterminate(true);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(btnMoCamera, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(62, 62, 62)
                        .addComponent(btnLuuKhuonMat, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(progressSaveImage, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24)
                        .addComponent(btnChonAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnBack)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblCameraDisplay, javax.swing.GroupLayout.PREFERRED_SIZE, 652, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblAnhChup, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(lblSoAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(115, 115, 115)))))
                .addContainerGap(27, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnBack)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblCameraDisplay, javax.swing.GroupLayout.PREFERRED_SIZE, 388, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblAnhChup, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(lblSoAnh))))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnMoCamera, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnChonAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLuuKhuonMat, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(progressSaveImage, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnMoCameraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMoCameraActionPerformed
        // TODO add your handling code here:
        if (face.isIsRecording()) {
            // Stop recording
            face.setIsRecording(false);
            btnMoCamera.setText("Mở camera");
        } else {
            // Start recording
            face.setIsRecording(true);
            btnMoCamera.setText("Tắt camera");
        }
    }//GEN-LAST:event_btnMoCameraActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        // TODO add your handling code here:
        face.setIsRecording(false);
        if(frm.equals("capNhat")){     
            frmInfo open = new frmInfo(account);
            face.setIsRecording(false);
            thread.interrupt();            
            open.setVisible(true);
        }
        else{
            frmLogin open = new frmLogin();
            open.setVisible(true);
        }
        this.dispose();
    }//GEN-LAST:event_btnBackActionPerformed

    private void btnLuuKhuonMatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuuKhuonMatActionPerformed
        progressSaveImage.setVisible(true);
        if(!face.isIsRecording()){
            JOptionPane.showMessageDialog(null, "Chưa bật camera");
            return;
        }
        if(!face.isCheck()){            
            face.setCheck(true);
        }
    }//GEN-LAST:event_btnLuuKhuonMatActionPerformed

    private void btnChonAnhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChonAnhActionPerformed
        // TODO add your handling code here:
        JFileChooser fcChooseImage = new JFileChooser();
        fcChooseImage.setMultiSelectionEnabled(true);
        fcChooseImage.setFileSelectionMode(JFileChooser.FILES_ONLY);
        // Set file filter to allow only image files
        FileNameExtensionFilter imageFilter = new FileNameExtensionFilter("Image files", "jpg", "jpeg", "png", "gif");
        fcChooseImage.setFileFilter(imageFilter);

        if (fcChooseImage.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            try {
                int count = 0;
                File[] selectedFiles = fcChooseImage.getSelectedFiles();
                List<byte[]> imageList = new ArrayList<>();
                for (File image : selectedFiles) {
                    Path imagePath = image.toPath();
                    byte[] imageBytes = Files.readAllBytes(imagePath);
                    byte[] faceImage = face.detctFace(imageBytes);
                    if (faceImage != null) {
                        imageList.add(faceImage);
                        count++;
                    }
                }
                if(count == 0){
                    JOptionPane.showMessageDialog(null, String.format("Không nhận diện được khuôn mặt nào trong tổng số %d ảnh đã chọn", selectedFiles.length));
                    return;
                }
                JOptionPane.showMessageDialog(null, String.format("Nhận diện được %d khuôn mặt trong tổng %d số ảnh đã chọn", count, selectedFiles.length));
                frmDisplayChooseImage open = new frmDisplayChooseImage(imageList, account);
                open.setVisible(true);

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex);
            }
        }
    }//GEN-LAST:event_btnChonAnhActionPerformed

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
            java.util.logging.Logger.getLogger(frmCameraAcess.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmCameraAcess.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmCameraAcess.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmCameraAcess.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmCameraAcess(account, frm).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnChonAnh;
    private javax.swing.JButton btnLuuKhuonMat;
    private javax.swing.JButton btnMoCamera;
    private javax.swing.JFileChooser fcChooseImages;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lblAnhChup;
    private javax.swing.JLabel lblCameraDisplay;
    private javax.swing.JLabel lblSoAnh;
    private spinner_progress.SpinnerProgress progressSaveImage;
    // End of variables declaration//GEN-END:variables
}
