package com.fresh.mappers;

import com.fresh.bean.Cart;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 购车接口
 */
@Repository
public interface CartMapper {

    /**
     * 根据主键删除购物车中的项
     * @param cart
     * @return
     */
    int deleteByPrimaryKey(Cart cart);

    /**
     * 根据用户主键清除购物车中的所有商品
     * @param cart
     * @return
     */
    int deleteAllProductsByUid(Cart cart);

    /**
     * 把商品添加到购物车
     *          > 传入：商品数量，pid，uid
     * @param cart
     * @return
     */
    int insertCart(Cart cart);

    /**
     * 根据uid查询出pid
     * @param cart
     * @return
     */
    List<Cart> selectProductByUid(Cart cart);

    /**
     * 根据商品主键和用户主键修改购物车中商品的数量
     * @param cart
     * @return
     */
    int updateCountByPidAndUid(Cart cart);
}