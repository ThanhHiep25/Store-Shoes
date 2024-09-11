// PasswordUtil.java
package com.example.shoestore.config;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.SecureRandom;
import java.util.Base64;

//public class PasswordUtil {
//
//    private static final int ITERATIONS = 10000;
//    private static final int KEY_LENGTH = 255; // in bits
//
//    public static String hashPassword(String password) throws Exception {
//        byte[] salt = getSalt();
//        PBEKeySpec spec = new PBEKeySpec(password.toCharArray(), salt, ITERATIONS, KEY_LENGTH);
//        SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
//        byte[] hash = skf.generateSecret(spec).getEncoded();
//        return Base64.getEncoder().encodeToString(salt) + ":" + Base64.getEncoder().encodeToString(hash);
//    }
//
//    public static boolean checkPassword(String password, String stored) throws Exception {
//        String[] parts = stored.split(":");
//        byte[] salt = Base64.getDecoder().decode(parts[0]);
//        byte[] hash = Base64.getDecoder().decode(parts[1]);
//        PBEKeySpec spec = new PBEKeySpec(password.toCharArray(), salt, ITERATIONS, KEY_LENGTH);
//        SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
//        byte[] testHash = skf.generateSecret(spec).getEncoded();
//        return java.util.Arrays.equals(hash, testHash);
//    }
//
//    private static byte[] getSalt() throws Exception {
//        SecureRandom sr = new SecureRandom();
//        byte[] salt = new byte[16];
//        sr.nextBytes(salt);
//        return salt;
//    }
//}
public class PasswordUtil {

    // Hàm băm mật khẩu không sử dụng trong trường hợp này
    public static String hashPassword(String password) {
        return password; // Trả về mật khẩu như văn bản thuần túy
    }

    // Kiểm tra mật khẩu không sử dụng trong trường hợp này
    public static boolean checkPassword(String password, String stored) {
        return password.equals(stored); // So sánh mật khẩu thuần túy
    }
}

