/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Form;

import facial_recognition.Account;
import facial_recognition.DBAccess;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.core.MatOfRect;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
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
    private MatOfByte matOfByte;
    private boolean isRecording;
    private Thread thread;
    private DBAccess access; 
    private int countImages;
    private boolean save;
    private byte[] imageData;
    private static String frm;
    /**
     * Creates new form frmCameraAcess
     */
    public frmCameraAcess(Account account, String frm) {
        initComponents();
        this.frm = frm;
        this.account = account; 
        access = new DBAccess();
        save = false;
        // Load the OpenCV native library        

        // Create JFrame and JLabel for displaying the camera feed
       
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
    
    private void saveFace() {
        if (save) {
            // Convert the byte[] imageData to a Mat object
            Mat frame = Imgcodecs.imdecode(new MatOfByte(imageData), Imgcodecs.IMREAD_COLOR);

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

                
                // Encode the face image to JPEG
                MatOfByte faceImageData = new MatOfByte();
                Imgcodecs.imencode(".jpg", faceImage, faceImageData);
                imageData = faceImageData.toArray();

                // Save the face image to the database
                access.saveImage(account.getID_User(), imageData);
                countImages++;
                if (countImages == 50) {
                    JOptionPane.showMessageDialog(null, "Đã lưu khuôn mặt");
                }
            } 
//            else {
//                // No face detected, stop the function
//                return;
//            }
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(btnBack)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(30, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(lblCameraDisplay, javax.swing.GroupLayout.PREFERRED_SIZE, 652, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnMoCamera, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(67, 67, 67)
                        .addComponent(btnLuuKhuonMat, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(135, 135, 135))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnBack)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addComponent(lblCameraDisplay, javax.swing.GroupLayout.PREFERRED_SIZE, 388, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnMoCamera, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLuuKhuonMat, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
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
            FrmInfo open = new FrmInfo(account);
            open.setVisible(true);
        }
        else{
            FrmLogin open = new FrmLogin();
            open.setVisible(true);
        }
        this.dispose();
    }//GEN-LAST:event_btnBackActionPerformed

    private void btnLuuKhuonMatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuuKhuonMatActionPerformed
        // TODO add your handling code here:
        save = true;
        while(countImages < 50){            
            saveFace();              
        }
        countImages = 0;
        save = false;
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
    private javax.swing.JLabel lblCameraDisplay;
    // End of variables declaration//GEN-END:variables
}
