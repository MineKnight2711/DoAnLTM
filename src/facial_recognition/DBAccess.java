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
    
    public boolean UpdateInfo(String query){
        try{
            stmt.executeUpdate(query);
            JOptionPane.showMessageDialog(null, "Cập nhật thông tin thành công");
            return true;
        }catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
            return false;
        }
    }
    
    public Account getUser(String account) {
        String query = "SELECT * FROM user WHERE Account = ?";
        try (PreparedStatement statement = con.prepareStatement(query)) {
            statement.setString(1, account);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Account user = new Account();
                    user.setID_User(resultSet.getString("ID_User"));
                    user.setAccount(resultSet.getString("Account"));
                    user.setPassword(resultSet.getString("Password"));
                    user.setFrist_Name(resultSet.getString("First_Name"));
                    user.setLast_Name(resultSet.getString("Last_Name"));
                    user.setBrithday(resultSet.getDate("Brithday"));
                    user.setGender(resultSet.getString("Gender"));
                    user.setPhone(resultSet.getString("Phone"));
                    user.setAddress(resultSet.getString("Address"));
                    user.setEmail(resultSet.getString("Email"));

                    return user;
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        return null; // Return null if the user doesn't exist or an error occurs
    }
    
    public boolean Register(String str){
        try{
            stmt.executeUpdate(str);
            JOptionPane.showMessageDialog(null, "Thêm người dùng mới thành công");
            return true;
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null,ex);
            System.out.println(ex.toString());
            return false;
        }
    }
    
    public int Update(String str){
        try {
             int i=stmt.executeUpdate(str);
             return i;
        }catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
            return -1;
        }
    }
    
    public ResultSet Query(String srt){
         try{
             ResultSet rs=stmt.executeQuery(srt);
             return rs;
         }catch (SQLException ex) {
             JOptionPane.showMessageDialog(null, ex);
            return null ;
        }
    }
    public ResultSet getAllUsers() {
        String query = "SELECT * FROM user";
        return Query(query);
    }
    public void deleteUser(int idUser) {
        String query = "DELETE FROM user WHERE ID_User = ?";

        try (PreparedStatement statement = con.prepareStatement(query)) {
            statement.setInt(1, idUser);
            statement.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }
}
