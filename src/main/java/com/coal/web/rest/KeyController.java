package com.coal.web.rest;

import com.coal.config.ApplicationProperties;
import com.coal.domain.Key;
import com.coal.service.KeyService;
import com.coal.util.ConstantUtil;
import com.coal.util.ObjectNodeUtil;
import com.coal.util.SM4Util;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import org.apache.commons.io.FileUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/key")
public class KeyController {

    private final KeyService keyService;
    private final ApplicationProperties applicationProperties;

    public KeyController(KeyService keyService, ApplicationProperties applicationProperties) {
        this.keyService = keyService;
        this.applicationProperties = applicationProperties;
    }

    /**
     * 判断用户是否为管理权限以及是否过期
     *
     * @return
     */
    @GetMapping("/auth")
    public ObjectNode getAuth() {
        ObjectNode result = ObjectNodeUtil.createObjectNode().put("auth", false).put("valid", false);
        String keyPath = applicationProperties.getKeyPath();
        try {
            File file = ResourceUtils.getFile(keyPath);
            if (!file.exists()) {
                return result;
            }
            String keyStr = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
            Key key = ObjectNodeUtil.stringToObj(SM4Util.deEncrypt(keyStr), Key.class);
            if (key != null) {
                AtomicBoolean auth = new AtomicBoolean(false);
                result.put("auth", key.getAuth().equals(ConstantUtil.AUTH));
                List<String> macList = keyService.getMac();
                macList.forEach(
                    mac -> {
                        if (key.getMacList().contains(mac)) {
                            auth.set(true);
                        }
                    }
                );

                if (auth.get() && key.getValidTime() > System.currentTimeMillis()) {
                    result.put("valid", true);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    @GetMapping("/update_auth")
    public ObjectNode updateKey(@RequestParam String encode, @RequestParam long validTime) {
        String keyStr = SM4Util.deEncrypt(encode);
        Key key = ObjectNodeUtil.stringToObj(keyStr, Key.class);
        key.setValidTime(validTime);
        return ObjectNodeUtil.createObjectNode().put("key", SM4Util.encrypt(ObjectNodeUtil.objToString(key)));
    }
}
