package com.example.zhouyunlong.pintuan;

import com.alicp.jetcache.anno.config.EnableCreateCacheAnnotation;
import com.alicp.jetcache.anno.config.EnableMethodCache;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author zhouyunlong
 */

@SpringBootApplication
@MapperScan("com.example.zhouyunlong.pintuan.dao")
@EnableMethodCache(basePackages = "com.example.zhouyunlong.pintuan")
@EnableCreateCacheAnnotation
@EnableScheduling
@EnableSwagger2
@EnableDiscoveryClient
public class PintuanApplication {

    public static void main(String[] args) {
        SpringApplication.run(PintuanApplication.class, args);
    }

}
