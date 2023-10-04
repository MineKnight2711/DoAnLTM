package facial_recognition;

import at.favre.lib.crypto.bcrypt.BCrypt;
import static java.lang.reflect.Array.getByte;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement; 
import java.util.ArrayList;
import java.util.List;
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
                        JOptionPane.showMessageDialog(null, "Mật khẩu không hợp lệ");
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
    
    public boolean ChangePassword(String queryCheck, String query, String oldPass){
        try{
                String pass;
                PreparedStatement statement = con.prepareStatement(queryCheck);
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()){
                    pass = resultSet.getString("Password");
                    BCrypt.Result result = BCrypt.verifyer().verify(oldPass.toCharArray(), pass);
                    if(!result.verified){
                        JOptionPane.showMessageDialog(null, "Mật khẩu cũ không hợp lệ");
                        return false;
                    }
                }
                stmt.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "Đổi mật khẩu thành công");
                return true;
            }
            catch (Exception ex) {
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
    
    public byte[] testDislay(){
        String query = "SELECT * FROM user_image WHERE ID_Image = 'I0000000075'";
         byte[] imageData = null;
         try (PreparedStatement statement = con.prepareStatement(query)){
              try (ResultSet resultSet = statement.executeQuery()) {
                  if (resultSet.next()){
                      imageData = resultSet.getBytes("Image");
                       return imageData;
                  } 
              }
              catch(Exception e){
                  JOptionPane.showMessageDialog(null,e);
                  return null;
              }
         }catch(Exception ex ){
             JOptionPane.showMessageDialog(null,ex);
             return null;
         }
         return null;
    }
    
    public boolean saveImage(String userID,byte[] images){
        try{
            String query = "INSERT INTO user_image VALUES('', ? , ?)";
            try(PreparedStatement statement = con.prepareStatement(query)){                
                statement.setString(1, userID);
                statement.setBytes(2, images);
                statement.executeUpdate();
                return true;
            }
        }
        catch(Exception ex ){
            JOptionPane.showMessageDialog(null, ex);
            return false;
        }
    }
    
      public List<byte[]> getAllImages() {
        List<byte[]> imageList = new ArrayList<>();
        String query = "SELECT Image FROM user_image";
        ResultSet rs = Query(query);
        try{
            while (rs.next()){
                byte[] imageData = rs.getBytes("Image");
                imageList.add(imageData);
            }
            return imageList;
        }
        catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex);
            return null ;
        }        
    }
      
      public List<UserImages> getAllUsers() {
        List<UserImages> allUser = new ArrayList<>();
        String query = "SELECT * FROM user_image ORDER BY ID_User ASC";
        ResultSet rs = Query(query);
        try{
            while (rs.next()){
                UserImages user = new UserImages();
                user.setID_Image(rs.getString("ID_Image"));
                user.setID_User(rs.getString("ID_User"));
                user.setImages(rs.getBytes("Image"));
                allUser.add(user);
            }
            return allUser;
        }
        catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex);
            return null ;
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
    
    public void deleteUser(int idUser) {
        String query = "DELETE FROM user WHERE ID_User = ?";

        try (PreparedStatement statement = con.prepareStatement(query)) {
            statement.setInt(1, idUser);
            statement.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }
    
    public List<UserImages> getUserImage(String userID) {
        List<UserImages> userImages = new ArrayList<>();
        String query = String.format("SELECT * FROM user_image WHERE ID_USER = '%s'", userID);
        ResultSet rs = Query(query);
        try{
            while (rs.next()){
                UserImages user = new UserImages();
                user.setID_Image(rs.getString("ID_Image"));
                user.setID_User(rs.getString("ID_User"));
                user.setImages(rs.getBytes("Image"));
                userImages.add(user);
            }
            return userImages;
        }
        catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex);
            return null ;
        }    
    }
}
