/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Form;

import com.google.gson.Gson;
import models.Account;
import db_connection.DBAccess;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import models.OperationJson;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.core.MatOfRect;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.videoio.VideoCapture;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;

/**
 *
 * @author dell
 */
public class frmCameraAcess extends javax.swing.JFrame {
    private static Account account;
    private VideoCapture videoCapture;
    private Mat frame;
    private Gson gson;
    private MatOfByte matOfByte;
    private boolean isRecording;
    private Thread thread;
    private DBAccess access; 
    private int countImages;
    private boolean save;
    private byte[] imageData;
    private static String frm;
    private List<byte[]> imageList; 

    /**
     * Creates new form frmCameraAcess
     */
    public frmCameraAcess(Account account, String frm) {
        initComponents();
        this.frm = frm;
        this.account = account; 
        access = new DBAccess();
        save = false;
        imageList=new ArrayList<>();
        gson=new Gson();

                    
    }   
    
    
    private boolean initializeCamera() {        
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        videoCapture = new VideoCapture(0); // Use the default camera (0) or choose the appropriate camera index        
        frame = new Mat();
        matOfByte = new MatOfByte();
        if (!videoCapture.isOpened()){
            JOptionPane.showMessageDialog(null,"Can not access camera" );
            return false;
        } 
        if (videoCapture.isOpened()) {
            Runnable frameGrabber = () -> {
                while (isRecording) {      
                    videoCapture.read(frame);
                    detectAndDrawFaces(frame);
                    // Optionally, perform image processing or face detection here
                    Imgcodecs.imencode(".jpg", frame, matOfByte);
                    imageData = matOfByte.toArray();   
                    if(save && countImages < 50){                      
                        saveFace(imageData);
                    }
                    // Display the image on the JLabel
                    ImageIcon imageIcon = new ImageIcon(imageData);
                    lblCameraDisplay.setIcon(imageIcon);
                    lblCameraDisplay.repaint();
                    // Record video if enabled
                     if (!isRecording){
                        videoCapture.release();
                        lblCameraDisplay.setIcon(null);
                        break;
                    }                        
                }
            };
            // Create a new thread for grabbing frames from the camera
            thread = new Thread(frameGrabber);  
            if(!isRecording){
                thread.interrupt();
                return true;
            }
            thread.setDaemon(true);  
            thread.start();            
            return true;
        }
        return false;
    }
    
    private void detectAndDrawFaces(Mat frame) {
        Mat grayFrame = new Mat();
        Imgproc.cvtColor(frame, grayFrame, Imgproc.COLOR_BGR2GRAY);

        CascadeClassifier faceCascade = new CascadeClassifier("src\\PreTrainData\\haarcascade_frontalface_default.xml");
        MatOfRect faces = new MatOfRect();
        faceCascade.detectMultiScale(grayFrame, faces);

        for (Rect rect : faces.toArray()) {
            Imgproc.rectangle(frame, rect.tl(), rect.br(), new Scalar(0, 255, 0), 2);
        }
    }
    
    private void saveFace(byte[] image) {
        try {
            if (save) {
                // Convert the byte[] imageData to a Mat object
                Mat frame = Imgcodecs.imdecode(new MatOfByte(image), Imgcodecs.IMREAD_COLOR);
                
                // Convert the frame to grayscale
                Mat grayFrame = new Mat();
                Imgproc.cvtColor(frame, grayFrame, Imgproc.COLOR_BGR2GRAY);
                
                // Load the face cascade classifier
                CascadeClassifier faceCascade = new CascadeClassifier("src\\PreTrainData\\haarcascade_frontalface_default.xml");
                
                // Detect faces in the grayscale frame
                MatOfRect faces = new MatOfRect();
                faceCascade.detectMultiScale(grayFrame, faces);
                
                // Check if a face is detected
                if (faces.toArray().length > 0) {
                    Rect faceRect = faces.toArray()[0]; // Assuming only one face is detected  
                    // Crop the face region from the gray frame
                    Mat faceImage = new Mat(grayFrame, faceRect); // Crop from the grayscale frame
                    MatOfByte faceImageData = new MatOfByte();
                    Size resizedSize = new Size(256, 256); // Adjust the size as needed
                    Imgproc.resize(faceImage, faceImage, resizedSize);
                    // Encode the face image to JPEG
                    Imgcodecs.imencode(".jpg", faceImage, faceImageData);
                    image = faceImageData.toArray();
                    Dispalay(image);
                    Socket socket = new Socket("localhost", 6969);
            
            
                    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                    
                    OperationJson operationJson = new OperationJson();
                    operationJson.setOperation("save-image/"+account.getID_User());
                    operationJson.setData(Base64.getEncoder().encodeToString(image));
                    String sendJson = gson.toJson(operationJson);
                    out.println(sendJson);
                    countImages++;
                    if (countImages == 50) {
                        countImages = 0;
                        save = false;
//                        access.saveImage(account.getID_User(), image);
                        String response = in.readLine();
                        if (response.equals("Success")) 
                        {
                            JOptionPane.showMessageDialog(this, "Thêm ảnh thành công!");
                            imageList.clear();
                        } else 
                        {
                            JOptionPane.showMessageDialog(this, "Có lỗi xảy ra!"+response,"Lỗi",JOptionPane.ERROR_MESSAGE);
                            countImages = 0;
                        }
                        socket.close();
                    }
                    
                }
            } 
        } catch (IOException ex) {
            Logger.getLogger(frmCameraAcess.class.getName()).log(Level.SEVERE, null, ex);
        }
   }
    
    private void Dispalay(byte[] image1) {
        try{           
            InputStream inputStream = new ByteArrayInputStream(image1);
            BufferedImage imageBuffer1 = ImageIO.read(inputStream);
            ImageIcon icon1 = new ImageIcon(imageBuffer1);
            lblAnhChup.setIcon(icon1);
            lblSoAnh.setText(String.valueOf(countImages + 1));
        }
        catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex);
        }
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblCameraDisplay = new javax.swing.JLabel();
        btnMoCamera = new javax.swing.JButton();
        btnBack = new javax.swing.JButton();
        btnLuuKhuonMat = new javax.swing.JButton();
        lblAnhChup = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        lblSoAnh = new javax.swing.JLabel();

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
        jLabel1.setText("/50");

        lblSoAnh.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblSoAnh.setText("0");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(131, 131, 131)
                        .addComponent(btnMoCamera, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(67, 67, 67)
                        .addComponent(btnLuuKhuonMat, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))
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
            .addGroup(layout.createSequentialGroup()
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
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnMoCamera, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLuuKhuonMat, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnMoCameraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMoCameraActionPerformed
        // TODO add your handling code here:
         if (isRecording) {
            // Stop recording
            countImages = 0;
            isRecording = false;
            if(!initializeCamera())
                return;
            btnMoCamera.setText("Record");
        } else {
            // Start recording
            isRecording = true;
            if(!initializeCamera())
                return;
            btnMoCamera.setText("Stop Recording");
        }
    }//GEN-LAST:event_btnMoCameraActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        // TODO add your handling code here:
        isRecording = false;
        initializeCamera();
        if(frm.equals("capNhat")){            
            frmInfo open = new frmInfo(account);
            open.setVisible(true);
        }
        else{
            frmLogin open = new frmLogin();
            open.setVisible(true);
        }
        this.dispose();
    }//GEN-LAST:event_btnBackActionPerformed

    private void btnLuuKhuonMatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuuKhuonMatActionPerformed
        // TODO add your handling code here:
        save = true;
        if(!save){
            save = true;
        }
        
        
    }//GEN-LAST:event_btnLuuKhuonMatActionPerformed

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
    private javax.swing.JButton btnLuuKhuonMat;
    private javax.swing.JButton btnMoCamera;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lblAnhChup;
    private javax.swing.JLabel lblCameraDisplay;
    private javax.swing.JLabel lblSoAnh;
    // End of variables declaration//GEN-END:variables
}
