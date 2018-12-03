package com.diudiu.applet.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Base64;

public class AESUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(AESUtil.class);

    public static String _KEY = "WWW.AiTao.com)!^";
    private static byte[] _AES_KEYS = {0x41, 0x72, 0x65, 0x79, 0x6F, 0x75, 0x6D, 0x79, 0x53, 0x6E, 0x6F, 0x77, 0x6D, 0x61, 0x6E, 0x3F};


    public static String encode (String password) {

        try {
            // 初始化
            // 转化成JAVA的密钥格式
            Key key = new SecretKeySpec(_KEY.getBytes(), "AES");
            // 初始化cipher
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(_AES_KEYS));
            byte[] encryptedText = cipher.doFinal(password.getBytes());
            return new String(Base64.getEncoder().encode(encryptedText));
        } catch (Exception e) {
            LOGGER.error("AES 加密报错", e);
            throw new RuntimeException(e);
        }
    }

    public static String decode(String password) {
        // 初始化
        // 转化成JAVA的密钥格式
        Key key = new SecretKeySpec(_KEY.getBytes(), "AES");
        // 初始化cipher
        Cipher cipher = null;
        try {
            cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(_AES_KEYS));
            byte[] encryptedText = cipher.doFinal(Base64.getDecoder().decode(password));
            return new String(encryptedText);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
