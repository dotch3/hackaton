package br.com.boavizinhanca.cad.utils;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CryptoUtils {

    public static String decrypt(String strToDecr, String password, String algorithm) {
        StandardPBEStringEncryptor crypto = new StandardPBEStringEncryptor();
        crypto.setPassword(password);
        crypto.setAlgorithm(algorithm);
        return crypto.decrypt(strToDecr);
    }

    public static String encryptSHA256(String text) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest algorithm = MessageDigest.getInstance("SHA-256");
        byte messageDigest[] = algorithm.digest(text.getBytes("UTF-8"));

        StringBuilder hexString = new StringBuilder();
        for (byte b : messageDigest) {
            hexString.append(String.format("%02X", 0xFF & b));
        }

        return hexString.toString();
    }
}
