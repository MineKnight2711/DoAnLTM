/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package routes;

import forms.frmAddFace;
import forms.frmChooseServer;
import forms.frmInfo;
import forms.frmLogin;
import forms.frmRecognition;
import forms.frmRegister;
import forms.frmUserImages;
import javax.swing.ImageIcon;

import javax.swing.JFrame;
import models.Account;

/**
 *
 * @author WitherDragon
 */
public class FormRoute {
    
    public static void openFormInfo(JFrame disposeForm,Account acc){
        frmInfo frm = new frmInfo(acc);
        frm.setVisible(true);
        setImageIcon(frm);
        disposeForm.dispose();
    }
    public static void openFormUserImages(JFrame disposeForm,Account acc){
        frmUserImages frm = new frmUserImages(acc);
        frm.setVisible(true);
        setImageIcon(frm);
        disposeForm.dispose();
    }
    public static void openFormLogin(JFrame disposeForm){
        frmLogin frm = new frmLogin();
        frm.setVisible(true);
        setImageIcon(frm);
        disposeForm.dispose();
    }
    public static void openFormAddFace(JFrame disposeForm,Account acc,String form){
        frmAddFace frm = new frmAddFace(acc,form);
        frm.setVisible(true);
        setImageIcon(frm);
        disposeForm.dispose();
    }
    public static void openFormRecognitionTest(JFrame disposeForm){
        frmRecognition frm = new frmRecognition();
        frm.setVisible(true);
        setImageIcon(frm);
        disposeForm.dispose();
        
    }

    public static void openFormRegister(JFrame disposeForm) {
        frmRegister frm = new frmRegister();
        frm.setVisible(true);
        setImageIcon(frm);
        disposeForm.dispose();
    }

    public static void opFormChoseServer(JFrame disposeForm) {
        frmChooseServer frm = new frmChooseServer();
        frm.setVisible(true);
        setImageIcon(frm);
        disposeForm.dispose();
    }
    public static void setImageIcon(JFrame form){
        ImageIcon icon = new ImageIcon("src\\icons\\face-detection.png");
        form.setIconImage(icon.getImage());
    }
}
