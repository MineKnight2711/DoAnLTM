/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author WitherDragon
 */
public class AES {
    private final PrivateKey privateKey;
    private final PublicKey publicKey;

    private static final String RSA_ALGORITHM = "RSA";
    private static final String AES_ALGORITHM = "AES";
    //Ở đây em sử dụng chế độ hoạt động cơ bản của thuật toán AES là ECB 
    //PKCS5Padding là một trong các chuẩn padding (điền dữ liệu) được sử dụng trong quá trình mã hóa bằng thuật toán AES (Advanced Encryption Standard) và các thuật toán mã hóa khác.
    //Padding là một kỹ thuật được sử dụng để điền vào dữ liệu gốc sao cho chúng có đủ kích thước để mã hóa thành các khối cùng kích thước.

    //Trong trường hợp của PKCS5Padding, dữ liệu được điền bằng các byte có giá trị bằng với số lượng byte cần phải điền. Ví dụ, nếu bạn cần phải điền thêm 4 byte cho dữ liệu cuối cùng, thì PKCS5Padding sẽ điền bằng 4 byte có giá trị là 0x04. Nếu bạn cần điền 2 byte, thì giá trị điền sẽ là 0x02.
    private static final String AES_TRANSFORMATION = "AES/ECB/PKCS5Padding";

    public PrivateKey getPrivateKey() {
        return privateKey;
    }

    public PublicKey getPublicKey() {
        return publicKey;
    }

    public AES() {
        //Khởi tạo cặp khoá khi khởi tạo đối tượng AES
        KeyPair keyPair = generateKeyPair();
        this.privateKey = keyPair.getPrivate();
        this.publicKey = keyPair.getPublic();
    }
    //Phương thức tạo cặp khoá
    private KeyPair generateKeyPair() {
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(RSA_ALGORITHM);
            keyPairGenerator.initialize(2048);
            return keyPairGenerator.generateKeyPair();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Failed to generate RSA key pair", e);
        }
    }
    //Khởi tạo khoá AES secret
    private byte[] generateAESKey() throws NoSuchAlgorithmException {
        //Tạo khoá sử dụng thuật toán AES
        KeyGenerator keyGenerator = KeyGenerator.getInstance(AES_ALGORITHM);
        keyGenerator.init(256);  
        SecretKey secretKey = keyGenerator.generateKey();
        return secretKey.getEncoded();
    }
    //Phương thức mã hoá kết hợp AES và RSA
    public String encrypt(String message, PublicKey publicKey) throws Exception {
        //Tạo khoá AES
        byte[] secretKey = generateAESKey();
        //Mã hoá thông điệp bằng AES
        String encryptedMessage = encryptAES(message, secretKey);
         //Mã hoá khoá secret bằng RSA
        byte[] encryptedSecretKey = encryptRSA(secretKey, publicKey);
        //Trả về chuỗi mã hoá base64 của khoá secret key đã mã hoá bằng RSA và chuỗi thông điệp đã mã hoá ngăn cách bởi ký tự |
        return Base64.getEncoder().encodeToString(encryptedSecretKey) + "|" + encryptedMessage;
    }
    //Phương thức giải mã kết hợp AES và RSA
    public String decrypt(String encryptedMessage, PrivateKey privateKey) throws Exception {
        //Tách khoá secret key và thông điệp cần giãi mã
        String[] parts = encryptedMessage.split("\\|");
        //Mã hoá khoá secret key thành chuỗi base64
        byte[] encryptedSecretKey = Base64.getDecoder().decode(parts[0]);
        String encryptedData = parts[1];
        //Giải mã khoá secret key bằng phương thức giải mã RSA
        byte[] secretKey = decryptRSA(encryptedSecretKey, privateKey);
        //Sau đó giải mã khoá thông điệp bằng phương thức giải mã AES cùng khoá đã mã hoá ở trên
        return decryptAES(encryptedData, secretKey);
    }
    // Phướng thức mã hoá AES
    private String encryptAES(String message, byte[] key) throws Exception {
        SecretKeySpec secretKeySpec = new SecretKeySpec(key, AES_ALGORITHM);
        //Thiết lập thuật toán và chế độ hoạt động cho quá trình mã hóa.
        Cipher cipher = Cipher.getInstance(AES_TRANSFORMATION);
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
        byte[] encryptedBytes = cipher.doFinal(message.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    private String decryptAES(String encryptedMessage, byte[] key) throws Exception {
        SecretKeySpec secretKeySpec = new SecretKeySpec(key, AES_ALGORITHM);
        //Thiết lập thuật toán và chế độ hoạt động cho quá trình mã hóa.
        Cipher cipher = Cipher.getInstance(AES_TRANSFORMATION);
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
        byte[] encryptedBytes = Base64.getDecoder().decode(encryptedMessage);
        byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
        return new String(decryptedBytes, StandardCharsets.UTF_8);
    }
    //Phương thức mã hoá RSA bằng publicKey
    private byte[] encryptRSA(byte[] data, PublicKey publicKey) throws Exception {
        Cipher cipher = Cipher.getInstance(RSA_ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        return cipher.doFinal(data);
    }
    //Phương thức giải mã RSA bằng privateKey
    private byte[] decryptRSA(byte[] encryptedData, PrivateKey privateKey) throws Exception {
        Cipher cipher = Cipher.getInstance(RSA_ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        return cipher.doFinal(encryptedData);
    }
    //Phương thức lấy public key từ chuỗi base64
    public PublicKey getPublicKeyFromString(String publicKeyString) throws NoSuchAlgorithmException, InvalidKeySpecException {
        byte[] publicKeyBytes = Base64.getDecoder().decode(publicKeyString);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicKeyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(RSA_ALGORITHM);
        return keyFactory.generatePublic(keySpec);
    }
    //Phương thức mã hoá Public Key
    public String encodePublicKey(PublicKey publicKey) {
        return Base64.getEncoder().encodeToString(publicKey.getEncoded());
    }
    //Phương thức mã hoá Private Key
    public String encodePrivateKey(PrivateKey privateKey) {
        return Base64.getEncoder().encodeToString(privateKey.getEncoded());
    }
    //Phương thức lấy private key từ chuỗi base64
    public PrivateKey getPrivateKeyFromString(String privateKeyString) throws NoSuchAlgorithmException, InvalidKeySpecException {
        byte[] privateKeyBytes = Base64.getDecoder().decode(privateKeyString);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(privateKeyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(RSA_ALGORITHM);
        return keyFactory.generatePrivate(keySpec);
    }
}
