package com.slj.controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GwLimitController {
    @GetMapping(value = "/product/api1/demo1")
    public String demo1() {
        return "demo1";
    }

    @GetMapping(value = "/product/api1/demo2")
    public String demo2() {
        return "demo2";
    }

    @GetMapping(value = "/product/api2/demo1")
    public String demo3() {
        return "demo3";
    }

    @GetMapping(value = "/product/api2/demo2")
    public String demo4() {
        System.out.printf("aaa");
        return "demo4";
    }
}
