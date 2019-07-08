package com.fresh.service.imple;

import com.fresh.bean.Cart;
import com.fresh.mappers.CartMapper;
import com.fresh.service.CartService;
import com.fresh.vo.CartVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;

/**
 * @author sdy
 * @date 2019/7/3
 */
@Service("cartService")
public class CartServiceImple implements CartService {
    @Resource
    private CartMapper cartMapper;

    /**
     * 通过用户id获取购物车列表
     * @param cart
     * @return
     */
    @Override
    public List<CartVO> getByUid(Cart cart) {
        List<Cart> carts = cartMapper.selectProductByUid(cart);
        CartVO cartVO = new CartVO();
        List<CartVO> cartVOS = new LinkedList<>();
        for (Cart c : carts) {
            cartVO.setCid(c.getCid());
            cartVO.setCount(c.getCount());
            cartVO.setPid(c.getProduct().getPid());
            cartVO.setPname(c.getProduct().getPname());
            cartVO.setPlink(c.getProduct().getPlink());
            cartVO.setPrice(c.getProduct().getPrice());
            cartVOS.add(cartVO);
        }
        return cartVOS;
    }

    /**
     * 插入购物车
     * @param cart
     * @return
     */
    @Override
    public int insertCart(Cart cart) {
        return cartMapper.insertCart(cart);
    }

    /**
     * 更新购物车中商品属性
     * @param cart
     * @return
     */
    @Override
    public int updateCart(Cart cart) {
        return cartMapper.updateCountByPid(cart);
    }

    /**
     * 删除购物车中的项
     * @param cart
     * @return
     */
    @Override
    public int delete(Cart cart) {
        return cartMapper.deleteByPrimaryKey(cart);
    }

    /**
     * 删除用户购物车中所有的项
     * @param cart
     * @return
     */
    @Override
    public int deleteAll(Cart cart) {
        return cartMapper.deleteAllProductsByUid(cart);
    }
}
