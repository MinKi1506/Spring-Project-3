package com.example.minkiApi;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CloudConfigController {

    //Configuration 주입
    private final CloudConfig cloudConfig;

    // 생성자 주입
    public CloudConfigController(CloudConfig cloudConfig){
        this.cloudConfig = cloudConfig;
    }

    // "config-local.yml에 정의한 내용 불러옴
    @GetMapping("/cloudConfig")
    public String loadCloudConfig() {
        return cloudConfig.toString();
    }
}
