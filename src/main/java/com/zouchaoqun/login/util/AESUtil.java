package com.zouchaoqun.login.util;


import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * AES 加密工具类
 * @author zouchaoqun
 */
public class AESUtil {

    public static void main(String[] args) throws Exception {
        AESUtil aesUtil = new AESUtil();

        String key = aesUtil.generateKey(256, "UTF-8");
        String initVector = key.substring(0, 16);
        String encType = "AES/CBC/PKCS5PADDING";
        String target = "root";
        System.out.println("target : " + target);

        String encrypted = aesUtil.encrypt(key, initVector, encType, target);
        System.out.println("encrypted : " + encrypted);

        String decrypted = aesUtil.decrypt(key, initVector, encType, encrypted);
        System.out.println("decrypted : " + decrypted);


        System.out.println(aesUtil.generateKey(111));
    }

    public String generateKey(int keySize) throws UnsupportedEncodingException,
            NoSuchAlgorithmException {
        return _generateKey(keySize, "UTF-8");
    }

    public static String generateKey(int keySize, String strEncodingType) throws UnsupportedEncodingException,
            NoSuchAlgorithmException {
        return _generateKey(keySize, strEncodingType);
    }

    private static String _generateKey(int keySize, String strEncodingType) throws NoSuchAlgorithmException,
            UnsupportedEncodingException {
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(keySize);
        SecretKey key = keyGen.generateKey();
        return new String(Base64.getEncoder().encode(key.getEncoded()), strEncodingType);
    }

    public static String encrypt(String key, String initVector, String encType, String target) throws UnsupportedEncodingException, NoSuchPaddingException,
            NoSuchAlgorithmException, InvalidAlgorithmParameterException,
            InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
        SecretKeySpec skeySpec = new SecretKeySpec(Base64.getDecoder().decode(key.getBytes("UTF-8")), "AES");

        Cipher cipher = Cipher.getInstance(encType);
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);

        byte[] encrypted = cipher.doFinal(target.getBytes());
        return Base64.getEncoder().encodeToString(encrypted);
    }

    public static String decrypt(String key, String initVector, String encType, String encrypted) throws UnsupportedEncodingException, NoSuchPaddingException,
            NoSuchAlgorithmException, InvalidAlgorithmParameterException,
            InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
        SecretKeySpec skeySpec = new SecretKeySpec(Base64.getDecoder().decode(key.getBytes("UTF-8")), "AES");

        Cipher cipher = Cipher.getInstance(encType);
        cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);

        byte[] original = cipher.doFinal(Base64.getDecoder().decode(encrypted));

        return new String(original);
    }

}