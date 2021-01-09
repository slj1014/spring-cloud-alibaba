package com.slj.fallback;

import com.slj.response.Result;
import com.slj.response.ResultUtils;
import com.slj.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

//容错类要求必须实现被容错的接口,并为每个方法实现容错方案
@Component
@Slf4j
public class ProductServiceFallBack implements ProductService {
    @Override
    public Result findById(Integer pid) {
        log.info("商品服务调用失败");
        return ResultUtils.failed();
    }

    @Override
    public Result reduceInventory(Integer pid, int num) {
        log.info("扣减库存失败");
        return ResultUtils.failed();
    }
}
