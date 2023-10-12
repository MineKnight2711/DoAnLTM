/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package facial_recognition;

import com.google.gson.Gson;
import forms.frmCameraAcess;
import java.awt.Label;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.Normalizer.Form;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import models.Account;
import models.OperationJson;
import models.UserImages;
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
import org.opencv.videoio.VideoCapture;
import utils.BaseURL;
import utils.EncodeDecode;

/**
 *
 * @author dell
 */
public class FaceReconigtion { 
    private Gson gson;
    private VideoCapture videoCapture;
    private Mat frame;
    private MatOfByte matOfByte;
    private byte[] imageData;
    private byte[] imageChoose;
    private JLabel displayCapture;
    private JLabel pictureNumber;
    private JLabel faceCapture;
    private JLabel faceData;
    private JTextField tiLe;
    private boolean mode;
    private Account account;
    private boolean check;
    private boolean isRecording;
    private int countImages;
    private List<byte[]> listImages;
    public FaceReconigtion(){
        gson = new Gson(); 
        account = new Account();
        check = false;
        isRecording = false;
        listImages=new ArrayList<>();
    }
    
    
    public boolean isIsRecording() {
        return isRecording;
    }

    public void setIsRecording(boolean isRecording) {
        this.isRecording = isRecording;
    }

    public boolean isCheck() {
        return check;
    }    

    public void setCheck(boolean check) {
        this.check = check;
    }

    public void setImageChoose(byte[] imageChoose) {
        this.imageChoose = imageChoose;
    }
    
    public void setImageData(byte[] imageData) {
        this.imageData = imageData;
    }

    public void setMode(boolean mode) {
        this.mode = mode;
    }
    
    
    
    
    public boolean initializeCamera(String form, JLabel cameraDisplay) {        
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        videoCapture = new VideoCapture(0); // Use the default camera (0) or choose the appropriate camera index        
        frame = new Mat();
        matOfByte = new MatOfByte();
        if (!videoCapture.isOpened() && isRecording){
            JOptionPane.showMessageDialog(null,"Can not access camera" );
            isRecording = false;
            return false;
        } 
        if(!isRecording){  
            videoCapture.release();
            cameraDisplay.setIcon(null);   
            return true;
        }
        if(isRecording){
            if (videoCapture.isOpened()) {
            while (isRecording) {      
                    videoCapture.read(frame);
                    detectAndDrawFaces(frame);
                    // Optionally, perform image processing or face detection here
                    Imgcodecs.imencode(".jpg", frame, matOfByte);
                    imageData = matOfByte.toArray(); 
                    ImageIcon imageIcon = new ImageIcon(imageData);
                    cameraDisplay.setIcon(imageIcon);
                    cameraDisplay.repaint();
                    if(mode){
                        if(check)
                            saveFace(imageData, mode, account);
                    }
                    else{
                        if(check){
                            if(imageChoose != null){                           
                                facialRecognition(imageChoose);
                            }
                            else{
                                facialRecognition(imageData);
                            }
                        }
                    }
                    if(!isRecording){  
                        videoCapture.release();
                        cameraDisplay.setIcon(null); 
                    }
                }
                return true;
            }
        }        
        return false;
    }   

    
    
    public byte[] detctFace(byte[] imageCapture) {
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
    //Phát hiện và vẽ lại khuôn mật thành trắng đen
    public void detectAndDrawFaces(Mat frame) {
        Mat grayFrame = new Mat();
        Imgproc.cvtColor(frame, grayFrame, Imgproc.COLOR_BGR2GRAY);

        CascadeClassifier faceCascade = new CascadeClassifier("src\\PreTrainData\\haarcascade_frontalface_default.xml");
        MatOfRect faces = new MatOfRect();
        faceCascade.detectMultiScale(grayFrame, faces);

        for (Rect rect : faces.toArray()) {
            Imgproc.rectangle(frame, rect.tl(), rect.br(), new Scalar(0, 255, 0), 2);
        }
    }
    private String sendRequestToServer(String operation,String data){
        try {
            Socket socket = new Socket(BaseURL.SERVER_ADDRESS, BaseURL.PORT);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            
            OperationJson operationJson = new OperationJson();
            operationJson.setOperation(operation);
            operationJson.setData(data);
            String sendJson = gson.toJson(operationJson);
            out.println(sendJson);
            String result=EncodeDecode.decodeBase64FromJson(in.readLine());
            System.out.println("Kết quả trả về :"+result);
            socket.close();
            return result;
        } catch (IOException ex) {
            System.out.println("Lỗi"+ex.toString());
            check = false;
            return "Fail";
        }
    }
    public boolean saveFace(byte[] image, boolean save, Account account){       
        byte[] faceImage = detctFace(image);
        if(faceImage != null)
        {
            DispalaySave(faceImage, countImages);
            listImages.add(faceImage);
            countImages++;
            if (countImages == 10) {
                countImages = 0;
                check = false;
                String encodeListImagesToJson=gson.toJson(listImages);
                String response=sendRequestToServer("save-image/"+account.getID_User(),encodeListImagesToJson);
                if (response.equals("Success")) 
                {
                    JOptionPane.showMessageDialog(null, "Thêm ảnh thành công!");
                    check = false;
                    countImages = 0;
                    return true;
                } 
                else 
                {
                    JOptionPane.showMessageDialog(null, "Có lỗi xảy ra! "+response,"Lỗi",JOptionPane.ERROR_MESSAGE);
                    countImages = 0;
                    check = false;
                    return false;
                }
            }
        } 
        return true;
    }

    public boolean saveFaceChoose(byte[] image, Account account){
        try {            
            byte[] faceImage = detctFace(image);
            if(faceImage != null)
            {
                Socket socket = new Socket(BaseURL.SERVER_ADDRESS, BaseURL.PORT);


                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

                OperationJson operationJson = new OperationJson();
                operationJson.setOperation("save-image/"+account.getID_User());
                operationJson.setData(Base64.getEncoder().encodeToString(faceImage));
                String sendJson = gson.toJson(operationJson);
                out.println(sendJson);                
                socket.close();
            } 
            return true;
        }
        catch (IOException ex) {
            Logger.getLogger(frmCameraAcess.class.getName()).log(Level.SEVERE, null, ex);
            check = false;
            return false;
        }   
   }
    
    public String facialRecognition(byte[] imageCapture) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        byte[] faces = detctFace(imageCapture);
        
        String respone=sendRequestToServer("regconition", gson.toJson(faces));
        OperationJson resultJson=gson.fromJson(respone, OperationJson.class);
        
        if (resultJson.getOperation().equals("Detected")) { 
            String[] imagesReceived= resultJson.getData().toString().split("@");
            if(imagesReceived.length==3){
                byte[] image1=gson.fromJson(imagesReceived[0], byte[].class);
                byte[] image2=gson.fromJson(imagesReceived[1], byte[].class);
                double similarity = gson.fromJson(imagesReceived[2], double.class);
                displayDetectedImage(image1,image2,similarity);
                check = false;
                imageChoose = null;
                return "Detected";
            }
            else{
                //Xử lý gói dữ liệu bị mất
                imageChoose = null;
                check = false;
                return "NotDetected";
            }
//            double min = 0;
//            double max = 0;
//            List<UserImages> allUserImages = access.getAllUsers();            
//            // Compare the captured face with all user images
//            for (UserImages userImage : allUserImages) {
//                // Convert the user image to a matrix
//                byte[] image = userImage.getImages();
//                // Compare the similarity of the captured face and user image
//                double similarity = compareImages(faces, image);
//                DispalayDetect(faces, image, similarity);
//                // Set a threshold value for similarity
//                double threshold = 0.88; // Adjust this value as needed
//                if(min == 0)
//                    min = similarity;
//                else if( max == 0)
//                    max = similarity;
//                else if(similarity < min){
//                    max = min;
//                    min = similarity;
//                }
//                else if(similarity > max){
//                    max = similarity;
//                }
//                // Check if the similarity is above the threshold
//                if (similarity >= threshold) {
//                    JOptionPane.showMessageDialog(null, "Có tồn tại: " + userImage.getID_User() + "\n" + min + " - " + max);
//                    
//                }
//            }      
//            JOptionPane.showMessageDialog(null, "Không tìm thấy" + "\n" + min + " - " + max);
//            imageChoose = null;
//            check = false;
            
        }else if(resultJson.getOperation().equals("NoFace")){
            JOptionPane.showMessageDialog(null, "Không tìm thấy khuôn mặt","Thông báo",2);
        } else {
            String[] imagesReceived= resultJson.getData().toString().split("@");
            if(imagesReceived.length == 3) {
                byte[] image1=gson.fromJson(imagesReceived[0], byte[].class);
                byte[] image2=gson.fromJson(imagesReceived[1], byte[].class);
                double similarity = gson.fromJson(imagesReceived[2], double.class);
                displayDetectedImage(image1,image2,similarity);
                check = false;
                imageChoose = null;
                JOptionPane.showMessageDialog(null, "Khuôn mặt chưa khớp","Thông báo",2);
                return resultJson.getOperation();
            }
            else{
                //Xử lý gói dữ liệu bị mất
            }
        }
        
        imageChoose = null;
        check = false;
        return resultJson.getOperation();
    }
    
    public double compareImages(byte[] image1, byte[] image2) {
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
    
    public void DispalaySave(byte[] image1, int countImages) {
        try{           
            InputStream inputStream = new ByteArrayInputStream(image1);
            BufferedImage imageBuffer1 = ImageIO.read(inputStream);
            ImageIcon icon1 = new ImageIcon(imageBuffer1);
            displayCapture.setIcon(icon1);
            pictureNumber.setText(String.valueOf(countImages + 1));
        }
        catch(IOException ex){
            JOptionPane.showMessageDialog(null, ex);
        }
    }
    
    public void displayDetectedImage(byte[] image1, byte[] image2, double simularity) {
        try{           
            InputStream inputStream = new ByteArrayInputStream(image1);
            InputStream inputStream1 = new ByteArrayInputStream(image2);
            BufferedImage imageBuffer1 = ImageIO.read(inputStream);
            BufferedImage imageBuffer2 = ImageIO.read(inputStream1);
            ImageIcon icon1 = new ImageIcon(imageBuffer1);
            ImageIcon icon2 = new ImageIcon(imageBuffer2);
            faceCapture.setIcon(icon1);
            faceData.setIcon(icon2);
            tiLe.setText( String.format("%.2f", simularity*100) + "%");
        }
        catch(IOException ex){
            JOptionPane.showMessageDialog(null, ex);
        }
    }
    
    public void getDisplayDetectValue(JLabel faceCapture, JLabel faceData, JTextField tiLe){
        this.faceCapture = faceCapture;
        this.faceData = faceData;
        this.tiLe = tiLe;
    }
    
    public void getDisplaySaveValue(JLabel displayCapture, JLabel pictureNumber, Account account){
        this.displayCapture = displayCapture;
        this.pictureNumber = pictureNumber;
        this.account = account;
    }
}
