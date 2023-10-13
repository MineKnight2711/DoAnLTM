/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package routes;

import forms.frmChooseServer;
import forms.frmInfo;
import forms.frmLogin;
import forms.frmRecognitionTest;
import forms.frmRegister;

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
        disposeForm.dispose();
    }
    
    public static void openFormRecognitionTest(JFrame disposeForm){
        frmRecognitionTest frm = new frmRecognitionTest();
        frm.setVisible(true);
        disposeForm.dispose();
        
    }

    

    public static void openFormRegister(frmLogin disposeForm) {
        frmRegister frm = new frmRegister();
        frm.setVisible(true);
        disposeForm.dispose();
    }

    public static void opFormChoseServer(frmLogin disposeForm) {
        frmChooseServer frm = new frmChooseServer();
        frm.setVisible(true);
        disposeForm.dispose();
    }
}
