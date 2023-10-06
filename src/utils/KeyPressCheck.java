/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author dell
 */
public class KeyPressCheck {
    public static KeyListener OnlyCharTextField(){
        KeyListener keyListener = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                 if (!Character.isLetter(c) && c != ' ') {
                    e.consume(); 
                }
            }
            @Override
            public void keyPressed(KeyEvent e) {
                // Not used in this example
            }
            @Override
            public void keyReleased(KeyEvent e) {
                // Not used in this example
            }
        };
        return keyListener;
    }    
    
    public static KeyListener OnlyNumberTextField(){
        KeyListener keyListener = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c)) {
                    e.consume();  // Ignore non-character keys
                }
            }
            @Override
            public void keyPressed(KeyEvent e) {
                // Not used in this example
            }
            @Override
            public void keyReleased(KeyEvent e) {
                // Not used in this example
            }
        };
        return keyListener;
    }   
}
