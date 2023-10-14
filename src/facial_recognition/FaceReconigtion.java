/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package facial_recognition;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import forms.frmCameraAcess;
import forms.frmRecognitionTest;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import models.Account;
import models.OperationJson;
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
import spinner_progress.SpinnerProgress;
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
    private SpinnerProgress saveimageProgress;
    private JLabel displayCapture;
    private JLabel pictureNumber;
    private JLabel faceCapture;
    private JLabel faceData;
    private JLabel lbName,lbAddress,lbPhone,lbEmail,lbGender,lbBirthDay;
    private JTextField tiLe;
    private boolean mode,check,isRecording=false;
    private boolean isExtended=false;
    private Account account;
    private int countImages;
     private int originalWidth;
     private int panelGap=16;
    private JFrame frmRegconition;
    private JPanel pnAccountInfo;
    private List<byte[]> listImages;
    public FaceReconigtion(){
        gson =  new GsonBuilder().setDateFormat("MMM d, yyyy").create(); 
        account = new Account();
        listImages=new ArrayList<>();
    }
    public void storeOriginalWidth() {
        originalWidth = frmRegconition.getWidth();
        int newWidth = originalWidth - pnAccountInfo.getWidth()-panelGap;
        frmRegconition.setSize(newWidth, frmRegconition.getHeight());
        pnAccountInfo.setVisible(false);
        frmRegconition.setLocationRelativeTo(null);
    }
    private void extendForm(boolean extend) {
//        int currentWidth = getWidth();
        int panelWidth = pnAccountInfo.getWidth();
        if (extend) {
            int newWidth = originalWidth + panelWidth+panelGap;
            frmRegconition.setSize(newWidth, frmRegconition.getHeight());
            pnAccountInfo.setVisible(true);
        } else {
            int newWidth = originalWidth - panelWidth-panelGap;
            frmRegconition.setSize(newWidth, frmRegconition.getHeight());
            pnAccountInfo.setVisible(false);
        }

        frmRegconition.setLocationRelativeTo(null);
    }
    
    public void getAccountInfoLabel(JFrame frmReg,JLabel lbName, JLabel lbBirthDay, JLabel lbAddress,JLabel lbPhone, JLabel lbGender, JLabel lbEmail,JPanel pnAccountInfo){
        this.lbName=lbName;
        this.lbBirthDay=lbBirthDay;
        this.lbAddress=lbAddress;
        this.lbEmail=lbEmail;
        this.lbGender=lbGender;
        this.lbPhone=lbPhone;
        this.frmRegconition=frmReg;
        this.pnAccountInfo=pnAccountInfo;
    }
    private void loadAccount(Account acc)
    {
        lbName.setText(acc.getFrist_Name()+" "+acc.getLast_Name());
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String formattedBirthday = sdf.format(acc.getBrithday());
        lbBirthDay.setText(formattedBirthday);
        lbAddress.setText(acc.getAddress());
        lbPhone.setText(acc.getPhone());
        lbEmail.setText(acc.getEmail());
        lbGender.setText(acc.getGender());
    }


    public void setAccount(Account account) {
        this.account = account;
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
                    saveimageProgress.setVisible(false);
                    return true;
                } 
                else 
                {
                    JOptionPane.showMessageDialog(null, "Có lỗi xảy ra! "+response,"Lỗi",JOptionPane.ERROR_MESSAGE);
                    countImages = 0;
                    check = false;
                    saveimageProgress.setVisible(false);
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
        if(isExtended){
            isExtended = !isExtended;
            extendForm(isExtended);
        }       
        
        if (resultJson.getOperation().equals("Detected")) { 
            String[] imagesReceived= resultJson.getData().toString().split("@");
            switch (imagesReceived.length) {
                case 4 -> {
                    byte[] image1=gson.fromJson(imagesReceived[0], byte[].class);
                    byte[] image2=gson.fromJson(imagesReceived[1], byte[].class);
                    double similarity = gson.fromJson(imagesReceived[2], double.class);
                    String idUser=imagesReceived[3];
                    displayDetectedImage(image1,image2,similarity);
                    check = false;
                    imageChoose = null;
                    String responeAccount=sendRequestToServer("get-account",idUser);
                    OperationJson responeJson=gson.fromJson(responeAccount, OperationJson.class);
                    if(responeJson.getOperation().equals("Success")){
                        System.out.println("Account nhận ::"+responeJson.getData().toString());
                        String decodeAccount=EncodeDecode.decodeBase64FromJson(responeJson.getData().toString());
                        loadAccount(gson.fromJson(decodeAccount, Account.class));
                        isExtended = !isExtended;
                        extendForm(isExtended);
                        JOptionPane.showMessageDialog(frmRegconition, "Tìm thấy khuôn mặt","Thông báo",1);
                        return "Detected";
                    }
                    JOptionPane.showMessageDialog(frmRegconition, "Không tìm thấy khuôn mặt","Thông báo",2);
                    return "NotDetected";
                }
                case 3 -> {
                        byte[] image1=gson.fromJson(imagesReceived[0], byte[].class);
                        byte[] image2=gson.fromJson(imagesReceived[1], byte[].class);
                        
                        double similarity = gson.fromJson(imagesReceived[2], double.class);
                        displayDetectedImage(image1,image2,similarity);
                        check = false;
                        imageChoose = null;
                        return "NotDetected";
                }
                default -> {
                    //Xử lý không nhận diện được không mặt
                    JOptionPane.showMessageDialog(null, "Vui lòng nhìn thẳng vào camera","Thông báo",2);
                    imageChoose = null;
                    check = false;
                    return "NoFace";
                }
            }
        } else if(resultJson.getOperation().equals("NotDetected")) {
            String[] imagesReceived= resultJson.getData().toString().split("@");
            if(imagesReceived.length == 3) {
                byte[] image1=gson.fromJson(imagesReceived[0], byte[].class);
                byte[] image2=gson.fromJson(imagesReceived[1], byte[].class);
                double similarity = gson.fromJson(imagesReceived[2], double.class);
                displayDetectedImage(image1,image2,similarity);
                check = false;
                imageChoose = null;
                JOptionPane.showMessageDialog(null, "Không tìm thấy khuôn mặt","Thông báo",2);
                return resultJson.getOperation();
            }
            else if(resultJson.getOperation().equals("NoFace")){
                JOptionPane.showMessageDialog(null, "Vui lòng nhìn thẳng vào camera","Thông báo",2);
                imageChoose = null;
                check = false;
                return resultJson.getOperation();
            }
        }
        
        imageChoose = null;
        check = false;
        return resultJson.getOperation();
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

    public void getProgress(SpinnerProgress progressSaveImage) {
       this.saveimageProgress=progressSaveImage;
    }
}
