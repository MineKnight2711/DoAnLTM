/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package facial_recognition;

import forms.frmChooseServer;
import routes.FormRoute;

/**
 *
 * @author dell
 */
public class main {

    
    public static void main(String[] args) {
        frmChooseServer open = new frmChooseServer();
        FormRoute.setImageIcon(open);
        open.setVisible(true);
    }
    
}
