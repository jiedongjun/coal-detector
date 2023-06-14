package com.coal.service;

import com.coal.config.ApplicationProperties;
import com.coal.domain.Key;
import com.coal.util.ObjectNodeUtil;
import com.coal.util.SM4Util;
import java.io.File;
import java.io.IOException;
import java.net.NetworkInterface;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Optional;
import javax.annotation.PostConstruct;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

@Service
public class KeyService {

    private final Logger log = LoggerFactory.getLogger(KeyService.class);
    private final ApplicationProperties applicationProperties;

    public KeyService(ApplicationProperties applicationProperties) {
        this.applicationProperties = applicationProperties;
    }

    @PostConstruct
    private void initKey() {
        String keyPath = applicationProperties.getKeyPath();
        try {
            File keyFile = ResourceUtils.getFile(keyPath);
            if (!keyFile.exists()) {
                boolean isCreate = keyFile.createNewFile();
                log.info("创建密钥文件结果为{}", isCreate);
                Key key = new Key();
                key.setMacList(getMac());
                key.setValidTime(0);
                FileUtils.writeByteArrayToFile(keyFile, SM4Util.encrypt(ObjectNodeUtil.objToString(key)).getBytes(StandardCharsets.UTF_8));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getLocalMac(byte[] mac) {
        StringBuilder sb = new StringBuilder("");
        for (int i = 0; i < mac.length; i++) {
            if (i != 0) {
                sb.append("-");
            }
            int temp = mac[i] & 0xff;
            String str = Integer.toHexString(temp);
            if (str.length() == 1) {
                sb.append("0").append(str);
            } else {
                sb.append(str);
            }
        }
        return sb.toString();
    }

    public List<String> getMac() {
        List<String> list = new ArrayList<>();
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                NetworkInterface networkInterface = networkInterfaces.nextElement();
                Optional.ofNullable(networkInterface.getHardwareAddress()).ifPresent(mac -> list.add(getLocalMac(mac).toLowerCase()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
