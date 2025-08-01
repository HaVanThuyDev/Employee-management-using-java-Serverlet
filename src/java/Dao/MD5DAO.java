package Dao;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5DAO {

    public String hashWithMD5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(input.getBytes());
            byte[] digest = md.digest();

            // Chuyển đổi byte[] thành chuỗi hexa
            StringBuilder hexString = new StringBuilder();
            for (byte b : digest) {
                String hex = Integer.toHexString(0xFF & b);
                if (hex.length() == 1) {
                    hexString.append('0'); // Đảm bảo có 2 ký tự cho mỗi byte
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error hashing with MD5", e);
        }
    }
}
