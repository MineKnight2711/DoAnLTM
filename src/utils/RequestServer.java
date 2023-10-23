/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import models.OperationJson;

/**
 *
 * @author WitherDragon
 */
public class RequestServer {
    public static String requestPublicKey(){
        try {
            Socket socket = new Socket(BaseURL.SERVER_ADDRESS, BaseURL.PORT);
            System.out.println("Server address:"+BaseURL.SERVER_ADDRESS+" Port:"+BaseURL.PORT);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            OperationJson publicKeyRequestJson=new OperationJson();
            publicKeyRequestJson.setOperation("GET_PUBLIC_KEY");
            out.println(new Gson().toJson(publicKeyRequestJson));
            return in.readLine();
        } catch (IOException ex) {
            System.out.println("Khong the gui request");
            return "Fail";
        } 
    }
    
    public static String sendRequestToServer(OperationJson operationJson){
        try (Socket socket = new Socket(BaseURL.SERVER_ADDRESS, BaseURL.PORT)){
            System.out.println("Server address:"+BaseURL.SERVER_ADDRESS+" Port:"+BaseURL.PORT);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            
            String sendJson =new Gson().toJson(operationJson);
            out.println(sendJson);
            String result=in.readLine();
            socket.close();
            return result;
        } catch (IOException ex) {
            System.out.println("Lỗi"+ex.toString());
            return "Fail";
        }
    }
}
