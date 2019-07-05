package com.fresh.mappers;

import com.fresh.bean.Cart;
import org.springframework.stereotype.Repository;

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
     * @param cart
     * @return
     */
    int insertCart(Cart cart);

    /**
     * 根据用户主键查询出某用户购物车中的所有商品
     * @param cart
     * @return
     */
    Cart selectByUid(Cart cart);

    /**
     * 根据商品主键修改购物车中商品的数量
     * @param cart
     * @return
     */
    int updateCountByPid(Cart cart);
}