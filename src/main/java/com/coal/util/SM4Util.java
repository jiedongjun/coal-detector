package com.coal.util;

import cn.hutool.crypto.symmetric.SymmetricCrypto;
import com.coal.domain.Key;

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
            "Vsc1Zn27Do7FJsLxQetw6mQRmImIQVdKjYB+iAtFqxwbabP8HrPQ7bL/pvUWvdi2VaXDArwMzLRfYWL/E/rplrcwftEbJlZWS3+iEmzg4UtkkkL5YH2cvLV0/iVjZSl03+FSYFtjp7twzNGOuvCW7GB2rpBkNI379jP54V9PCFfptsn+cXoLluaVsTeSoEX3"
        );
        System.out.println(base64);
        Key key1 = ObjectNodeUtil.stringToObj(base64, Key.class);
        key1.setValidTime(System.currentTimeMillis() + 3600 * 1000 * 1000000);
        key1.setAuth(ConstantUtil.AUTH);

        System.out.println(SM4Util.encrypt(ObjectNodeUtil.objToString(key1)));
    }
}
