package com.coal.util;

import static java.time.temporal.ChronoUnit.DAYS;
import static java.time.temporal.ChronoUnit.HOURS;

import cn.hutool.crypto.symmetric.SymmetricCrypto;
import com.coal.domain.Key;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.Date;

public class SM4Util {

    static final String key = "sm4demo123456789";
    //指明加密算法和秘钥
    static SymmetricCrypto sm4 = new SymmetricCrypto("SM4/ECB/PKCS5Padding", key.getBytes());

    //加密
    public static String encrypt(String data) {
        return sm4.encryptBase64(data);
    }

    //解密
    public static String deEncrypt(String data) {
        return sm4.decryptStr(data);
    }

    public static void main(String[] args) {
        String base64 = SM4Util.deEncrypt(
            "Vsc1Zn27Do7FJsLxQetw6mQRmImIQVdKjYB+iAtFqxwbabP8HrPQ7bL/pvUWvdi2VaXDArwMzLRfYWL/E/rplrcwftEbJlZWS3+iEmzg4UtkkkL5YH2cvLV0/iVjZSl0v/RwK0ObFRW/3Ry01XYKxc6Vj28GZ+IzsIqAybn8jPLAyZ/myMLL5ACzLTiu21ow"
        );
        System.out.println(base64);
        Key key1 = ObjectNodeUtil.stringToObj(base64, Key.class);
        Instant now = Instant.now();
        System.out.println(now);
        Instant plus = now.plus(365, DAYS);
        System.out.println(plus);
        key1.setValidTime(plus.toEpochMilli());

        Key key2 = new Key();
        key2.setValidTime(key1.getValidTime());
        key2.setMacList(key1.getMacList());
        key2.setAuth(ConstantUtil.AUTH);

        System.out.println(SM4Util.encrypt(ObjectNodeUtil.objToString(key2)));
    }
}
