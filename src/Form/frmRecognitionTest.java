/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Form;

import facial_recognition.Account;
import facial_recognition.DBAccess;
import java.util.Arrays;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.core.MatOfFloat;
import org.opencv.core.MatOfInt;
import org.opencv.core.MatOfRect;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;
import org.opencv.videoio.VideoCapture;

/**
 *
 * @author dell
 */
public class frmRecognitionTest extends javax.swing.JFrame {
    private static Account account;
    private VideoCapture videoCapture;
    private Mat frame;
    private MatOfByte matOfByte;
    private boolean isRecording;
    private Thread thread;
    private byte[] imageData;
    private DBAccess access; 
    private boolean check;
    /**
     * Creates new form frmRecognitionTest
     */
    public frmRecognitionTest() {
        initComponents();
        access = new DBAccess();
        check = false;
    }

    
    private boolean facialRecognition(byte[] imageCapture) {
        List<byte[]> allUserImages = access.getAllImages();
        Mat frame = Imgcodecs.imdecode(new MatOfByte(imageCapture), Imgcodecs.IMREAD_COLOR);

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

            // Crop the face region from the frame
            Mat faceImage = new Mat(frame, faceRect); // Crop from the original frame

            // Encode the face image to JPEG
            MatOfByte faceImageData = new MatOfByte();
            Imgcodecs.imencode(".jpg", faceImage, faceImageData);
            imageCapture = faceImageData.toArray();
             // Compare the captured face with all user images
            for (byte[] userImage : allUserImages) {
                // Convert the user image to a matrix
                Mat userMat = Imgcodecs.imdecode(new MatOfByte(userImage), Imgcodecs.IMREAD_GRAYSCALE);

                // Compare the similarity of the captured face and user image
                double similarity = compareImages(grayFrame, userMat);

                // Set a threshold value for similarity
                double threshold = 0.8; // Adjust this value as needed

                // Check if the similarity is above the threshold
                if (similarity >= threshold) {
                    JOptionPane.showMessageDialog(null, "Có tồn tại");
                    check = true;
                    return true;
            }
        }
       
        }
         return false;
    }
    
    private double compareImages(Mat image1, Mat image2) {
        // Calculate histograms for both images
        Mat hist1 = new Mat();
        Mat hist2 = new Mat();

        // Set histogram parameters
        int histSize = 256; // Number of bins
        MatOfFloat histRange = new MatOfFloat(0f, 256f);
        boolean accumulate = false;

        // Compute histograms
        Imgproc.calcHist(Arrays.asList(image1), new MatOfInt(0), new Mat(), hist1, new MatOfInt(histSize), histRange, accumulate);
        Imgproc.calcHist(Arrays.asList(image2), new MatOfInt(0), new Mat(), hist2, new MatOfInt(histSize), histRange, accumulate);

        // Normalize histograms
        Core.normalize(hist1, hist1, 0, hist1.rows(), Core.NORM_MINMAX, -1, new Mat());
        Core.normalize(hist2, hist2, 0, hist2.rows(), Core.NORM_MINMAX, -1, new Mat());

        // Apply histogram comparison method (e.g., correlation)
        double similarity = Imgproc.compareHist(hist1, hist2, Imgproc.HISTCMP_CORREL);

        return similarity;
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
                    if(!check)
                        facialRecognition(imageData);
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
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblDisplayCapture = new javax.swing.JLabel();
        btnMoCamera = new javax.swing.JButton();
        btnNhanDIen = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnMoCamera.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnMoCamera.setText("Mở camera");
        btnMoCamera.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMoCameraActionPerformed(evt);
            }
        });

        btnNhanDIen.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnNhanDIen.setText("Nhận diện");
        btnNhanDIen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNhanDIenActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(80, 80, 80)
                        .addComponent(lblDisplayCapture, javax.swing.GroupLayout.PREFERRED_SIZE, 559, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(161, 161, 161)
                        .addComponent(btnMoCamera)
                        .addGap(140, 140, 140)
                        .addComponent(btnNhanDIen)))
                .addContainerGap(96, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(lblDisplayCapture, javax.swing.GroupLayout.PREFERRED_SIZE, 411, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNhanDIen)
                    .addComponent(btnMoCamera))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnMoCameraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMoCameraActionPerformed
        // TODO add your handling code here:
        if (isRecording) {
            // Stop recording
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

    private void btnNhanDIenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNhanDIenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnNhanDIenActionPerformed

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
    private javax.swing.JButton btnMoCamera;
    private javax.swing.JButton btnNhanDIen;
    private javax.swing.JLabel lblDisplayCapture;
    // End of variables declaration//GEN-END:variables
}
