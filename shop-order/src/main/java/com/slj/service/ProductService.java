package com.slj.service;

import com.slj.fallback.ProductServiceFallBackFactory;
import com.slj.response.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
//使用feignClient访问product数据
//@FeignClient(value = "service-product")

//使用feignClient访问product数据,实现容错
//@FeignClient(value = "service-product", fallback = ProductServiceFallBack.class)

//使用feignClient访问product数据,实现容错，并且获取错误信息
@FeignClient(value = "service-product", fallbackFactory = ProductServiceFallBackFactory.class)
public interface ProductService {
    @GetMapping(value = "/products/{pid}")
    Result findById(@PathVariable("pid") Integer pid);

    @GetMapping(value = "/products/{pid}/reduceInventory")
    Result reduceInventory(@PathVariable("pid") Integer pid,@RequestParam("num") int num);
}
