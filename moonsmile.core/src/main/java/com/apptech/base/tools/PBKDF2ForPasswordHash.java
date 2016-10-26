package com.apptech.base.tools;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

public class PBKDF2ForPasswordHash {
    private static final String PBKDF_ALGORITHM = "PBKDF2WithHmacSHA1";
    private static final int ITERATION_COUNT = 10_000;
    // should be less than the size of the underlying hash
    private static final int PASSWORD_HASH_SIZE_BYTES = 16;
    private static final int SALT_SIZE_BYTES = 16;

    public static byte[] generateRandomSalt() {
        return generateRandomSalt(SALT_SIZE_BYTES);
    }

    public static byte[] generateRandomSalt(final int saltSizeBytes) {
        final SecureRandom rng = new SecureRandom();
        final byte[] salt = new byte[saltSizeBytes];
        rng.nextBytes(salt);
        return salt;
    }

    public static byte[] generatePasswordHash(final char[] password,
                                              final byte[] salt) {
        SecretKeyFactory f;
        try {
            f = SecretKeyFactory.getInstance(PBKDF_ALGORITHM);
        } catch (final NoSuchAlgorithmException e) {
            throw new IllegalStateException("PBKDF algorithm "
                    + PBKDF_ALGORITHM + " not available", e);
        }
        final KeySpec ks = new PBEKeySpec(password, salt, ITERATION_COUNT,
                PASSWORD_HASH_SIZE_BYTES * Byte.SIZE);
        SecretKey s;
        try {
            s = f.generateSecret(ks);
        } catch (final InvalidKeySpecException e) {
            throw new IllegalArgumentException(
                    "PBEKeySpec should always be valid for " + PBKDF_ALGORITHM,
                    e);
        }
        return s.getEncoded();
    }

    public static String toHex(final byte[] data) {
        final StringBuilder sb = new StringBuilder(data.length * 2);
        for (int i = 0; i < data.length; i++) {
            sb.append(String.format("%02x", data[i]));
        }
        return sb.toString();
    }

    public static byte[] toByte(final String hexString) {
        int len = hexString.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(hexString.charAt(i), 16) << 4)
                    + Character.digit(hexString.charAt(i + 1), 16));
        }
        return data;
    }

    public static String toMD5(String input) {
        String md5 = null;

        try {
            java.security.MessageDigest digest = java.security.MessageDigest.getInstance("MD5");
            //Update input string in message digest
            digest.update(input.getBytes(), 0, input.length());
            //Converts message digest value in base 16 (hex)
            md5 = new java.math.BigInteger(1, digest.digest()).toString(16);
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return md5;
    }

    public static boolean ValidPasswordHash(final String pwdString,final String saltString,final String passwordHash){
        final char[] password = pwdString.toCharArray();
        final byte[] salt = PBKDF2ForPasswordHash.toByte(saltString);
        final byte[] pwdBytes = PBKDF2ForPasswordHash.generatePasswordHash(password, salt);
        final String pwdHashCompare = PBKDF2ForPasswordHash.toHex(pwdBytes);
        return pwdHashCompare.equals(passwordHash);
    }
}
