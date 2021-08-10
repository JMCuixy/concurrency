package org.encrypt;

import com.github.javafaker.Faker;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Locale;

/**
 * @Author: xiuyin.cui@joymo.tech
 * @Date: 2021/8/9 18:14
 * @Description:
 */
public class Rsa {
    /**
     * 自己写一个程序实测下对称加密和非对称加密的性能差距。
     * <p/>
     * 加密 10000 个莎士比亚书中的句子，用时在 11s 左右，而采用 AES 对称加密算法加密，用时在 400ms 左右。
     *
     * @param args
     */
    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeyException, InvalidKeySpecException, NoSuchPaddingException, BadPaddingException, IllegalBlockSizeException {
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
        KeyPair pair = keyPairGen.generateKeyPair();
        byte[] privateKey = pair.getPrivate().getEncoded();
        // 支持创建指定长度的随机字符串 https://blog.csdn.net/weixin_35127842/article/details/114969230
        Faker faker = new Faker(new Locale("zh-CN"));
        long start = System.currentTimeMillis();
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(privateKey);
        PrivateKey key = keyFactory.generatePrivate(keySpec);
        for (int i = 0; i < 10000; i++) {
            byte[] randomBytes = faker.shakespeare().asYouLikeItQuote().getBytes();
            Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] resultBytes = cipher.doFinal(randomBytes);
            if (i % 100 == 0) {
                System.out.format("%d/10000 done.\n", i);
            }
        }
        System.out.format("time: %dms\n", System.currentTimeMillis() - start);
    }


}
