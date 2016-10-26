package com.apptech.base.tools;

import com.google.common.io.BaseEncoding;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class EncryptUtils {
    private static final String DEFAULT_ENCODING = "UTF-8";
    private static BaseEncoding baseEncoding = BaseEncoding.base64();
    private static String ENCRYPT_KEY = "FRAME_ENCRYPT_KEY";

    public static String base64encode(String text) {
        try {
            return baseEncoding.encode(text.getBytes(DEFAULT_ENCODING));
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }

    public static String base64decode(String text) {
        try {
            return new String(baseEncoding.decode(text), DEFAULT_ENCODING);
        } catch (IOException e) {
            return null;
        }
    }

    public static String encryptString(String message) {
        return base64encode(xorMessage(message, ENCRYPT_KEY));
    }

    public static String decryptString(String message) {
        return xorMessage(base64decode(message), ENCRYPT_KEY);
    }

    private static String xorMessage(String message, String key) {
        try {
            if (message == null || key == null) return null;

            char[] keys = key.toCharArray();
            char[] mesg = message.toCharArray();

            int ml = mesg.length;
            int kl = keys.length;
            char[] newmsg = new char[ml];

            for (int i = 0; i < ml; i++) {
                newmsg[i] = (char) (mesg[i] ^ keys[i % kl]);
            }

            return new String(newmsg);
        } catch (Exception e) {
            return null;
        }
    }
}
