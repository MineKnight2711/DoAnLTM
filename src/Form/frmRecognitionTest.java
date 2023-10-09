/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Form;


import db_connection.DBAccess;
import models.UserImages;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.core.MatOfRect;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;
import org.opencv.objdetect.Objdetect;
import org.opencv.videoio.VideoCapture;

/**
 *
 * @author dell
 */
public class frmRecognitionTest extends javax.swing.JFrame {
    private VideoCapture videoCapture;
    private Mat frame;
    private MatOfByte matOfByte;
    private boolean isRecording;
    private Thread thread;
    private byte[] imageData;
    private byte[] imageChoose;
    private DBAccess access; 
    private boolean check;
    /**
     * Creates new form frmRecognitionTest
     */
    public frmRecognitionTest() {
        
        initComponents();       
        access = new DBAccess();
        check = false;
        txtTiLe.setEnabled(false);
        runableThread();
    }
    
    private byte[] detctFace(byte[] imageCapture) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        // Convert the byte[] imageData to a Mat object
        Mat frame = Imgcodecs.imdecode(new MatOfByte(imageCapture), Imgcodecs.IMREAD_COLOR);

        // Convert the frame to grayscale
        Mat grayFrame = new Mat();
        Imgproc.cvtColor(frame, grayFrame, Imgproc.COLOR_BGR2GRAY);

        // Load the face cascade classifier
        CascadeClassifier faceCascade = new CascadeClassifier("src\\PreTrainData\\haarcascade_frontalface_default.xml");
        // Detect faces in the grayscale frame
        MatOfRect faces = new MatOfRect();
        faceCascade.detectMultiScale(grayFrame, faces);     

        MatOfByte faceImageData = new MatOfByte();
        Imgcodecs.imencode(".jpg", grayFrame, faceImageData);
        imageCapture = faceImageData.toArray();
        // Check if a face is detected
        if (faces.toArray().length > 0) {

            // Encode the face image to JPEG
            Rect faceRect = faces.toArray()[0]; // Assuming only one face is detected

            // Crop the face region from the gray frame
            Mat faceImage = new Mat(grayFrame, faceRect); // Crop from the grayscale frame   
            Size resizedSize = new Size(256, 256); // Adjust the size as needed
            Imgproc.resize(faceImage, faceImage, resizedSize);
            // Encode the face image to JPEG
            Imgcodecs.imencode(".jpg", faceImage, faceImageData);
            imageCapture = faceImageData.toArray();
            return imageCapture;

        }
        return null;
    }
    
    private boolean facialRecognition(byte[] imageCapture) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        byte[] faces = detctFace(imageCapture);
        // Check if a face is detected
        if (faces != null) {
            List<UserImages> allUserImages = access.getAllUsers();
            // Compare the captured face with all user images
            for (UserImages userImage : allUserImages) {
                // Convert the user image to a matrix
                byte[] image = userImage.getImages();
                // Compare the similarity of the captured face and user image
                double similarity = compareImages(faces, image);
                Dispalay(faces, image, similarity);
                // Set a threshold value for similarity
                double threshold = 0.90; // Adjust this value as needed

                // Check if the similarity is above the threshold
                if (similarity >= threshold) {
                    JOptionPane.showMessageDialog(null, "Có tồn tại: " + userImage.getID_User());
                    check = false;
                    return true;
                }
            }      
            JOptionPane.showMessageDialog(null, "Không tìm thấy");
        }
        check = false;
        return false;
    }
    
    private void Dispalay(byte[] image1, byte[] image2, double simularity) {
        try{           
            InputStream inputStream = new ByteArrayInputStream(image1);
            InputStream inputStream1 = new ByteArrayInputStream(image2);
            BufferedImage imageBuffer1 = ImageIO.read(inputStream);
            BufferedImage imageBuffer2 = ImageIO.read(inputStream1);
            ImageIcon icon1 = new ImageIcon(imageBuffer1);
            ImageIcon icon2 = new ImageIcon(imageBuffer2);
            lblFaceCapture.setIcon(icon1);
            lblDataFace.setIcon(icon2);
            txtTiLe.setText( String.format("%.2f", simularity*100) + "%");
        }
        catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex);
        }
    }
    
    private double compareImages(byte[] image1, byte[] image2) {
        // Load OpenCV library
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        // Convert image byte arrays to OpenCV Mat objects
        Mat mat1 = Imgcodecs.imdecode(new MatOfByte(image1), Imgcodecs.IMREAD_UNCHANGED);
        Mat mat2 = Imgcodecs.imdecode(new MatOfByte(image2), Imgcodecs.IMREAD_UNCHANGED);
       
        // Calculate the Mean Squared Error (MSE) as a similarity measure
        double mse = Core.norm(mat1, mat2, Core.NORM_L2) / (mat1.rows() * mat1.cols());

        // Convert the MSE to a similarity score (1 - MSE)
        double similarity = 1.0 - mse;

        return similarity;
    }
    
    private void runableThread(){
        Runnable frameGrabber = () -> {
                while (true) {
                        initializeCamera();
                    if(!isRecording){  
                        videoCapture.release();
                        if(check && imageChoose != null)
                            facialRecognition(imageChoose);
                    }
                }
            };
            // Create a new thread for grabbing frames from the camera
            thread = new Thread(frameGrabber); 
            thread.setDaemon(true);  
            thread.start();   
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
            while (isRecording) {      
                videoCapture.read(frame);
                detectAndDrawFaces(frame);
                // Optionally, perform image processing or face detection here
                Imgcodecs.imencode(".jpg", frame, matOfByte);
                imageData = matOfByte.toArray(); 
                if(check)
                    facialRecognition(imageData);
                check = false;
                // Display the image on the JLabel
                ImageIcon imageIcon = new ImageIcon(imageData);
                lblDisplayCapture.setIcon(imageIcon);
                lblDisplayCapture.repaint();
                // Record video if enabled
                 if (!isRecording){
                    videoCapture.release();
                    lblDisplayCapture.setIcon(null);
                    break;
                }                        
            }
            return true;
        }
        return false;
    }
    
    private void detectAndDrawFaces(Mat frame) {
        Mat grayFrame = new Mat();
        Imgproc.cvtColor(frame, grayFrame, Imgproc.COLOR_BGR2GRAY);
        MatOfRect faces = new MatOfRect();
        CascadeClassifier faceCascade = new CascadeClassifier("src\\PreTrainData\\haarcascade_frontalface_default.xml");
        faceCascade.detectMultiScale(grayFrame, faces);

        for (Rect rect : faces.toArray()) {
            Imgproc.rectangle(frame, rect.tl(), rect.br(), new Scalar(0, 255, 0), 2);
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

        fcChooseImage = new javax.swing.JFileChooser();
        lblDisplayCapture = new javax.swing.JLabel();
        btnMoCamera = new javax.swing.JButton();
        btnNhanDien = new javax.swing.JButton();
        lblDataFace = new javax.swing.JLabel();
        lblFaceCapture = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtTiLe = new javax.swing.JTextField();
        btnChonAnh = new javax.swing.JButton();
        btnMoCamera1 = new javax.swing.JButton();
        btnRefresh = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnMoCamera.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnMoCamera.setText("Mở camera");
        btnMoCamera.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMoCameraActionPerformed(evt);
            }
        });

        btnNhanDien.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnNhanDien.setText("Nhận diện");
        btnNhanDien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNhanDienActionPerformed(evt);
            }
        });

        jLabel1.setText("Tỉ lệ giống: ");

        btnChonAnh.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnChonAnh.setText("Chọn ảnh");
        btnChonAnh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChonAnhActionPerformed(evt);
            }
        });

        btnMoCamera1.setText("Trở về");
        btnMoCamera1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMoCamera1ActionPerformed(evt);
            }
        });

        btnRefresh.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnRefresh.setText("Refresh");
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
                .addContainerGap(13, Short.MAX_VALUE)
                .addComponent(btnMoCamera1, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnMoCamera)
                        .addGap(18, 18, 18)
                        .addComponent(btnNhanDien)
                        .addGap(18, 18, 18)
                        .addComponent(btnChonAnh)
                        .addGap(29, 29, 29)
                        .addComponent(btnRefresh))
                    .addComponent(lblDisplayCapture, javax.swing.GroupLayout.PREFERRED_SIZE, 559, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblFaceCapture, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDataFace, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(txtTiLe, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(49, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblDisplayCapture, javax.swing.GroupLayout.PREFERRED_SIZE, 411, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnMoCamera1))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnMoCamera, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnNhanDien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnChonAnh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnRefresh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblFaceCapture, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtTiLe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addComponent(lblDataFace, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnMoCameraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMoCameraActionPerformed
        // TODO add your handling code here:
        if (isRecording) {
            // Stop recording
            isRecording = false;
            btnMoCamera.setText("Record");
        } else {
            // Start recording
            isRecording = true;
            btnMoCamera.setText("Stop Recording");
        }
    }//GEN-LAST:event_btnMoCameraActionPerformed

    private void btnNhanDienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNhanDienActionPerformed
        // TODO add your handling code here:
        if(!isRecording && imageChoose == null){
            JOptionPane.showMessageDialog(null, "Chưa chọn ảnh hoặc bật camera");
            return;
        }
        if(check){
            check = false;   
        }
        else{
            check = true;    
        }
    }//GEN-LAST:event_btnNhanDienActionPerformed

    private void btnChonAnhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChonAnhActionPerformed
        // TODO add your handling code here:
        JFileChooser fcChooseImage = new JFileChooser();
        fcChooseImage.setVisible(true);
        fcChooseImage.setFileSelectionMode(JFileChooser.FILES_ONLY);

        // Set file filter to allow only image files
        FileNameExtensionFilter imageFilter = new FileNameExtensionFilter("Image files", "jpg", "jpeg", "png", "gif");
        fcChooseImage.setFileFilter(imageFilter);

        if (fcChooseImage.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            try {
                File selectedFile = fcChooseImage.getSelectedFile();
                Path imagePath = selectedFile.toPath();
                byte[] imageBytes = Files.readAllBytes(imagePath);  
                byte[] faceImage = detctFace(imageBytes);
                if(faceImage != null){                    
                    isRecording = false;
                    InputStream inputStream = new ByteArrayInputStream(faceImage );
                    BufferedImage imageBuffer1 = ImageIO.read(inputStream);
                    ImageIcon icon1 = new ImageIcon(imageBuffer1);
                    lblFaceCapture.setIcon(icon1);    
                    imageChoose = imageBytes;   
                }
                else{
                    JOptionPane.showMessageDialog(null, "Không tìm thấy khuôn mặt trong ảnh chọn");
                }
            } catch (Exception ex) {
                JOptionPane.showConfirmDialog(null, ex);
            }
        }        
    }//GEN-LAST:event_btnChonAnhActionPerformed

    private void btnMoCamera1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMoCamera1ActionPerformed
        // TODO add your handling code here:
        frmLogin open = new frmLogin();
        open.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnMoCamera1ActionPerformed

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
        // TODO add your handling code here:
        isRecording = false;
        frmRecognitionTest open = new frmRecognitionTest();
        open.setVisible(true );
        this.dispose();
    }//GEN-LAST:event_btnRefreshActionPerformed

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
            java.util.logging.Logger.getLogger(frmRecognitionTest.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmRecognitionTest.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmRecognitionTest.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmRecognitionTest.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmRecognitionTest().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnChonAnh;
    private javax.swing.JButton btnMoCamera;
    private javax.swing.JButton btnMoCamera1;
    private javax.swing.JButton btnNhanDien;
    private javax.swing.JButton btnRefresh;
    private javax.swing.JFileChooser fcChooseImage;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lblDataFace;
    private javax.swing.JLabel lblDisplayCapture;
    private javax.swing.JLabel lblFaceCapture;
    private javax.swing.JTextField txtTiLe;
    // End of variables declaration//GEN-END:variables
}
