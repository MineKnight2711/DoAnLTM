package facial_recognition;

import at.favre.lib.crypto.bcrypt.BCrypt;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement; 
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Administrator
 */
public class DBAccess {
    private Connection con;
    private Statement stmt;
    public DBAccess(){
        try{
            MyConnection mycon=new MyConnection();
            con=mycon.getConection();
            stmt=con.createStatement();
        }
        catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex);
        }                    
    }
    
    public boolean Login(String account, String password) {
    String query = "SELECT Password FROM user WHERE Account = ?";
    try (PreparedStatement statement = con.prepareStatement(query)) {
        statement.setString(1, account);
        try (ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                String hashedPassword = resultSet.getString("Password");
                BCrypt.Result result = BCrypt.verifyer().verify(password.toCharArray(), hashedPassword);
                if (result.verified) {
                    JOptionPane.showMessageDialog(null, "Đăng nhập thành công");
                    return true;
                } else {
                    JOptionPane.showMessageDialog(null, "Tài khoản hoặc mật khẩu không hợp lệ");
                    return false;
                }
            } else {
                JOptionPane.showMessageDialog(null, "Tài khoản hoặc mật khẩu không hợp lệ");
                return false;
            }
        }
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, ex);
        return false;
    }
}
    
    public boolean Register(String str){
        try{
            stmt.executeUpdate(str);
            JOptionPane.showMessageDialog(null, "Thêm người dùng mới thành công");
            return true;
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null,ex);
            System.out.println(ex.toString());
            return false;
        }
    }
    
    public int Update(String str){
        try {
             int i=stmt.executeUpdate(str);
             return i;
        }catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
            return -1;
        }
    }
    
    public ResultSet Query(String srt){
         try{
             ResultSet rs=stmt.executeQuery(srt);
             return rs;
         }catch (Exception ex) {
             JOptionPane.showMessageDialog(null, ex);
            return null ;
        }
    }
    public ResultSet getAllUsers() {
        String query = "SELECT * FROM user";
        return Query(query);
    }
    public void deleteStudent(int idUser) {
        String query = "DELETE FROM user WHERE ID_User = ?";

        try (PreparedStatement statement = con.prepareStatement(query)) {
            statement.setInt(1, idUser);
            statement.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }
}
