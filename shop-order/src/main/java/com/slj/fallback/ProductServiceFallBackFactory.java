package com.slj.fallback;

import com.slj.response.ResultUtils;
import com.slj.service.ProductService;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ProductServiceFallBackFactory implements FallbackFactory<ProductService> {
    @Override
    public ProductService create(Throwable throwable) {
        /*return pid -> {
            throwable.printStackTrace();
            log.info("商品服务调用失败");
            return ResultUtils.failed();
        };*/
        return null;
    }
}
