package com.example.demo.utils.crypto;

import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.*;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

@Component
public class EncryptData {
    @Autowired
    private UserService userService;

    @Value("${file.encrypt.publicKey}")
    private String publicKey;

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    public String encryptData(String valueStr) {
        try {
            byte[] bytes = Base64.getDecoder().decode(publicKey);
            // Tạo public key
            X509EncodedKeySpec spec = new X509EncodedKeySpec(bytes);
            KeyFactory factory = KeyFactory.getInstance("RSA");
            PublicKey pubKey = factory.generatePublic(spec);

            // Mã hoá dữ liệu
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(Cipher.ENCRYPT_MODE, pubKey);
            byte encryptOut[] = cipher.doFinal(valueStr.getBytes());
            String strEncrypt = Base64.getEncoder().encodeToString(encryptOut);
            System.out.println("Chuỗi sau khi mã hoá: " + strEncrypt);
            return strEncrypt;
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }
}
