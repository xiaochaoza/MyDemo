package com.fzzz.framework.utils;

import android.annotation.TargetApi;
import android.os.Build;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * description: 测试 AESUtil 对AES加密算法的封装
 * author: ShenChao
 * time: 2019-06-03
 * update:
 */
public class AESUtilTest {
    public static void main(String[] args) throws Exception {
        String sSrc ="{\"sysCode\":\"nxgovapp\",\"mobileNo\":\"13712348810\",\"latitude\":\"39.130945\",\"longitude\":\"106.707405\",\"name\":\"张三\",\"avatar\":\"http://abc.com/123.jpg\"}";
        String sKey = "AxsePck21Ab12345";
            byte[] raw = sKey.getBytes("utf-8");
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");//"算法/模式/补码方式"
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
            byte[] encrypted = cipher.doFinal(sSrc.getBytes("utf-8"));

//            return new Base64().encodeToString(encrypted);//此处使用BASE64做转码功能，同时能起到2次加密的作用。
        Base64.getEncoder().encodeToString(encrypted);

    }
}