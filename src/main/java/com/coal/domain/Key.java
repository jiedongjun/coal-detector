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
}
