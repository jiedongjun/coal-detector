package com.coal.domain;

import com.coal.util.ConstantUtil;
import com.coal.util.ObjectNodeUtil;
import com.coal.util.SM4Util;
import java.util.List;

public class Key {

    private List<String> macList;
    private long validTime;

    private String auth = "";

    public List<String> getMacList() {
        return macList;
    }

    public void setMacList(List<String> macList) {
        this.macList = macList;
    }

    public long getValidTime() {
        return validTime;
    }

    public void setValidTime(long validTime) {
        this.validTime = validTime;
    }

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }

    public static void main(String[] args) {
        String deEncode =
            "Vsc1Zn27Do7FJsLxQetw6mQRmImIQVdKjYB+iAtFqxwbabP8HrPQ7bL/pvUWvdi2VaXDArwMzLRfYWL/E/rplrcwftEbJlZWS3+iEmzg4UtkkkL5YH2cvLV0/iVjZSl0Khq9W9bcBg2IVbmKwqyuqsPDgI2sf1NCyCrZ2YFcKc0=";
        String s = SM4Util.deEncrypt(deEncode);
        Key key = ObjectNodeUtil.stringToObj(s, Key.class);
        key.setAuth(ConstantUtil.AUTH);
        System.out.println(SM4Util.encrypt(ObjectNodeUtil.objToString(key)));
    }
}
