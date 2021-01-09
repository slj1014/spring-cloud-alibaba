package com.slj.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.slj.service.SentinelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * sentinel 测试类
 * <p>
 * #启动jar脚本  java -Dserver.port=8080 -Dcsp.sentinel.dashboard.server=localhost:8080 -Dproject.name=sentinel-dashboard -jar sentinel-dashboard-1.7.0.jar
 */
@RestController
public class SentinelController {


    int i = 0;

    @Autowired
    private SentinelService sentinelService;

    @GetMapping("/sentinel1")
    public String test1() {
        return "hello sentinel";
    }

    @GetMapping("/sentinel2")
    public String test2() {
        i++;
        if (i % 3 == 0) {
            throw new RuntimeException();
        }
        return "hello sentinel2";
    }

    @GetMapping("/sentinel3")
    @SentinelResource("sentinel")//注意这里必须使用这个注解标识,热点规则不生效
    public String test3(String name, Integer age) {
        return name + ":" + age;
    }

    @GetMapping("/sentinel4")
    public String test4() {
        return sentinelService.testFallBack();
    }
}
