package com.welfare.lottery.utils;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Base64;

public class AESUtils {

    public static final String CHARSET = "UTF-8";
    public static final String KEY_ALGORITHM = "AES";
    public static final String CIPHER_ALGORITHM = "AES/CBC/PKCS5Padding";

    /**
     * 加密 (UTF-8)
     *
     * @param key     密钥
     * @param content 明文内容
     * @return Base64 编码后的密文字符串
     */
    public static String encrypt(String key, String content) {
        try {
            Key keySpec = new SecretKeySpec(key.getBytes(CHARSET), KEY_ALGORITHM);
            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
            byte[] iv = new byte[cipher.getBlockSize()];
            IvParameterSpec ivParams = new IvParameterSpec(iv);
            cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivParams);
            byte[] encryptByte = cipher.doFinal(content.getBytes(CHARSET));
            return Base64.getEncoder().encodeToString(encryptByte);
        } catch (Exception e) {
            throw new RuntimeException("AES加密失败", e);
        }
    }

    /**
     * 解密 (UTF-8)
     *
     * @param key       密钥
     * @param encrypted 密文（Base64 编码）
     * @return 明文字符串
     */
    public static String decrypt(String key, String encrypted) {
        try {
            byte[] encryptBytes = Base64.getDecoder().decode(encrypted);
            Key keySpec = new SecretKeySpec(key.getBytes(CHARSET), KEY_ALGORITHM);
            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
            byte[] iv = new byte[cipher.getBlockSize()];
            IvParameterSpec ivParams = new IvParameterSpec(iv);
            cipher.init(Cipher.DECRYPT_MODE, keySpec, ivParams);
            byte[] content = cipher.doFinal(encryptBytes);
            return new String(content, CHARSET);
        } catch (Exception e) {
            throw new RuntimeException("AES解密失败", e);
        }
    }

    // 测试
    public static void main(String[] args) {
        String key = "wzzxdysyb@remote";
        String content = "123456";

        String encrypted = encrypt(key, content);
        System.out.println("加密结果: " + encrypted);

        String decrypted = decrypt(key, encrypted);
        System.out.println("解密结果: " + decrypted);
    }
}
