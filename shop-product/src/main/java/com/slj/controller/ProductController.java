package com.slj.controller;

import com.slj.response.Result;
import com.slj.response.ResultUtils;
import com.slj.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping(value = "/products/{pid}")
    public Result findByPid(@PathVariable("pid") Integer pid) {
        log.info("find by pid="+pid);
        return ResultUtils.success(productService.findByPid(pid));
    }

    @GetMapping(value = "/products/{pid}/reduceInventory")
    public Result reduceInventory(@PathVariable("pid") Integer pid,@RequestParam("num") int num){
        log.info("reduceInventory by pid="+pid+";num="+num);
        return ResultUtils.success(productService.reduceInventory(pid,num));
    }
}
