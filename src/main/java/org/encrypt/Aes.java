package org.encrypt;

import com.github.javafaker.Faker;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Locale;

/**
 * @Author: xiuyin.cui@joymo.tech
 * @Date: 2021/8/9 18:18
 * @Description:
 */
public class Aes {

    /**
     * 自己写一个程序实测下对称加密和非对称加密的性能差距。
     * <p/>
     * 加密 10000 个莎士比亚书中的句子，用时在 11s 左右，而采用 AES 对称加密算法加密，用时在 400ms 左右。
     *
     * @param args
     */
    public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(128);
        SecretKey key = keyGenerator.generateKey();
        // 支持创建指定长度的随机字符串 https://blog.csdn.net/weixin_35127842/article/details/114969230
        Faker faker = new Faker(new Locale("zh-CN"));
        byte[] ivBytes = new byte[16];
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            byte[] randomBytes = faker.shakespeare().asYouLikeItQuote().getBytes();
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            new SecureRandom().nextBytes(ivBytes);
            IvParameterSpec iv = new IvParameterSpec(ivBytes);
            cipher.init(Cipher.ENCRYPT_MODE, key, iv);
            byte[] result = cipher.doFinal(randomBytes);
        }
        System.out.format("time: %dms\n", System.currentTimeMillis() - start);
    }

}
