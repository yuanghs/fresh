package com.fresh.mappers;

import com.fresh.bean.Product;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * 商品接口
 */
@Repository
public interface ProductMapper {
    /**
     * 从数据库中随机查询出6条商品数据
     * @return
     */
    List<Product> selectRandomProducts();

    /**
     * 根据主键查询商品：返回商品的详情
     * @param product
     * @return
     */
    Product selectProductByPrimaryKey(Product product);
}