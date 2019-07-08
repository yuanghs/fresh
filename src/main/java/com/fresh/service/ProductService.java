package com.fresh.service;

import com.fresh.bean.Category;
import com.fresh.bean.Product;
import com.fresh.vo.ProductDetailVO;
import com.fresh.vo.ProductVO;

import java.util.List;

/**
 * @author sdy
 * @date 2019/7/3
 */
public interface ProductService {
    /**
     * 查询随机6条商品
     * @return list
     */
    List<ProductVO> random();

    /**
     * 根据id获取商品详情信息
     * @param product pid
     * @return Product
     */
    ProductDetailVO getById(Product product);

    /**
     * 通过分类获取商品列表
     * @param category cid
     * @return list
     */
    List<ProductVO> getByKind(Category category);

    /**
     * 进行模糊查询
     * @param product 关键字
     * @return Product
     */
    List<ProductVO> search(Product product);

}
