package com.slj.service.impl;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.slj.handler.SentinelBlockHandler;
import com.slj.service.SentinelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SentinelServiceImpl implements SentinelService {
    int i = 0;

    @Override
    @SentinelResource(value = "sentinelService",
            blockHandlerClass = SentinelBlockHandler.class,
            blockHandler = "blockHandler",
            fallbackClass = SentinelBlockHandler.class,
            fallback = "fallback")
    public String testFallBack() {
        i++;
        if (i % 3 == 0) {
            throw new RuntimeException();
        }
        return "SentinelServiceImpl";
    }
}
