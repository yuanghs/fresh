package com.fresh.service;

import com.fresh.bean.Cart;
import com.fresh.vo.CartVO;

import java.util.List;

/**
 * @author sdy
 * @date 2019/7/3
 */

public interface CartService {
    /**
     * 通过uid获取用户购物车
     * @param cart
     * @return
     */
    public List<CartVO> getByUid(Cart cart);

    /**
     * 添加购物车
     * @param cart
     * @return
     */
    public int insertCart(Cart cart);

    /**
     * 更新购物车中的数量
     * @param cart
     * @return
     */
    public int updateCart(Cart cart);

    /**
     * 根据cid删除购物车中的项
     * @param cart
     * @return
     */
    public int delete(Cart cart);

    /**
     * 删除购物车中所有的项
     * @param cart
     * @return
     */
    public int deleteAll(Cart cart);
}
