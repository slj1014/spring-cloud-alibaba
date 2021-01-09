package com.slj.handler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;


/**
 * sentinel fallback降级类
 */
@Slf4j
public class SentinelBlockHandler {
    //注意这里必须使用static修饰方法，BlockException进入的方法
    public static String blockHandler(BlockException ex) {
        log.error("{}", ex);
        return "接口被限流或者降级了...";
    }

    //注意这里必须使用static修饰方法,Throwable进入的方法
    public static String fallback(Throwable throwable) {
        log.error("{}", throwable);
        return "接口发生异常了...";
    }
}
