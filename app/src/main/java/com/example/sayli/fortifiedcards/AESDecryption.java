package com.example.sayli.fortifiedcards;

import android.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by Sayli on 2/12/2017.
 */
public class AESDecryption {
    static  String decryptedText;
    static Cipher cipher;

    public static String decrypt(String encryptedText)
            throws Exception {

        String key = "gkdieywbdkeeuloi";
        byte [] keys = key.getBytes();
        SecretKey secretKey = new SecretKeySpec(keys, 0, keys.length, "AES");
        cipher = Cipher.getInstance("AES");

//        Base64.Decoder decoder = Base64.getDecoder();
        byte[] encryptedTextByte = Base64.decode(encryptedText, Base64.DEFAULT);
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decryptedByte = cipher.doFinal(encryptedTextByte);
        decryptedText = new String(decryptedByte);
        return decryptedText;
    }

}
