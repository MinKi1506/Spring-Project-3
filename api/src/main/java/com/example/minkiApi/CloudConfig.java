package com.example.minkiApi;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

@Component
@RefreshScope // Config.yml 파일 변경 시, 변경된 내용을 actuator을 통해 변경값을 갱신
public class CloudConfig {

    @Value("${spring.datasource.username}")
    private String userName;

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${test.minki}")
    private String test;



    public String getUserName() {
        return userName;
    }

    public String getUrl() {
        return url;
    }

    public String getTest() {
        return test;
    }

    @Override
    public String toString() {
        return "가져온 db의 userName: "+ userName + "/" + "url" + url + "/" + "이번주 결말:" + test;  }
}
