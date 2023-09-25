/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Form;

import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

/**
 *
 * @author dell
 */
public class CheckInput {  
    
    public boolean CheckPassword(String matKhau, String nhapLai){
        String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$";
        Pattern pattern = Pattern.compile(regex);
         Matcher matcher = pattern.matcher(matKhau);
         if(!matcher.matches()){
             JOptionPane.showMessageDialog(null, "Mật khẩu nhập vào phải có ít nhất 8 ký tự, 1 ký tụ hoa, 1 ký tự thường, 1 ký tự đặc biệt, 1 ký tự số");
            return false;
         }
        if(!matKhau.equals(nhapLai) ){
            JOptionPane.showMessageDialog(null, "Mật khẩu nhập lại không hợp lệ");
            return false;
        }
        return true;
    }
    public boolean CheckBrithday(Date date){
        Calendar calendar = Calendar.getInstance();
        Date currentDate = calendar.getTime();       
        if(date.compareTo(currentDate) > 0){
              JOptionPane.showMessageDialog(null, "Ngày sinh phải nhỏ hơn hoặc bằng ngày hiện tại");
              return false;
        }
        return true;
    }  
    
    public boolean CheckSDT(String sdt){
        String regex = "^(\\+?84|0)(3[2-9]|5[2689]|7[06789]|8[1-689]|9[0-46-8])[0-9]{7}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(sdt);
        if (!matcher.matches()){
            JOptionPane.showMessageDialog(null, "Số điện thoại không đúng định dạng");
            return false;
        }
        return true;
    }
    
    public boolean CheckEmail(String email){
        String regex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        if (!matcher.matches()) {
            JOptionPane.showMessageDialog(null, "Email không đúng định dạng");
            return false;
        }
        return true;
    }
}
