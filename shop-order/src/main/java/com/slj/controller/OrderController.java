package com.slj.controller;

import com.alibaba.fastjson.JSON;
import com.slj.domain.Order;
import com.slj.domain.Product;
import com.slj.response.Result;
import com.slj.response.ResultEnum;
import com.slj.response.ResultUtils;
import com.slj.service.OrderService;
import com.slj.service.ProductService;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@Slf4j
public class OrderController {
    @Autowired
    private OrderService orderService;

 /*   @Autowired
    private RestTemplate restTemplate;
*/
    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private ProductService productService;

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    @PostMapping(value = "/orders")
    @GlobalTransactional//全局事务控制
    public Result createOrder(@RequestBody Order order) {
        log.info("order info=" + JSON.toJSONString(order));

        //使用discoveryClient获取服务器地址
/*        List<ServiceInstance> instances = discoveryClient.getInstances("service-product");
        Result result = restTemplate.getForObject("http://" + instances.get(0).getHost() + ":" + instances.get(0).getPort() + "/products/" + order.getPid(), Result.class);*/

        //使用restTemplate实现负载均衡
        //  Result result = restTemplate.getForObject("http://service-product/products/" + order.getPid(), Result.class);

        //使用fegin
        Result result = productService.findById(order.getPid());
        if (result.getCode().intValue() == ResultEnum.SUCCESS.getCode().intValue()) {
            Product product = ResultUtils.getObjectData(result.getData(), Product.class);
            log.info("product info=" + JSON.toJSONString(product));
            order.setPname(product.getPname());
            order.setPprice(product.getPprice() * order.getNumber());
            Order value = orderService.createOrder(order);
            /**
             * 模拟订单延迟
             */
         /*   try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
            log.info("return order info=" + JSON.toJSONString(value));
            result= productService.reduceInventory(order.getPid(),order.getNumber());
            if(result.getCode().intValue() == ResultEnum.SUCCESS.getCode().intValue()){
                //下单成功之后,将消息放到mq中
                //  rocketMQTemplate.convertAndSend("order-topic", order);
                return ResultUtils.success();
            }else{
                return ResultUtils.failed();
            }
        }
        return ResultUtils.failed();
    }
}
