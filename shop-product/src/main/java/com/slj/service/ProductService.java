package com.slj.service;

import com.slj.domain.Product;

public interface ProductService {
    //根据pid查询商品信息
    Product findByPid(Integer pid);

    //减库存
    Product reduceInventory(Integer pid,Integer num);
}
