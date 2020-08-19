package br.com.boavizinhanca.users.utils;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

public class Decrypt {

    public String decrypt(String strToDecr, String password, String algorithm) {
        StandardPBEStringEncryptor crypto = new StandardPBEStringEncryptor();
        crypto.setPassword(password);
        crypto.setAlgorithm(algorithm);
        return crypto.decrypt(strToDecr);
    }
}
