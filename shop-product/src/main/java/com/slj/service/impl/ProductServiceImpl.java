package com.slj.service.impl;

import com.slj.dao.ProductDao;
import com.slj.domain.Product;
import com.slj.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Override
    public Product findByPid(Integer pid) {
        return productDao.findById(pid).get();
    }

    @Override
    public Product reduceInventory(Integer pid, Integer num) {
       Product product= productDao.findById(pid).get();
       //模拟减库存失败
        int i = 1 / 0;
       product.setStock(product.getStock()-num);
       return productDao.save(product);
    }
}
