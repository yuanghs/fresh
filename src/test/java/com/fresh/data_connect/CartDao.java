package com.fresh.data_connect;

import com.fresh.bean.Cart;
import com.fresh.bean.Product;
import com.fresh.bean.User;
import com.fresh.mappers.CartMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * @author ygh
 * @date 2019/7/3
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class CartDao {

    @Autowired
    private CartMapper cartMapper;


    /**
     * 测试 selectProductByUid()
     *             > 成功！
     */
    @Test
    public void test1() {

        User user = new User();
        user.setUid(1);

        Cart cart = new Cart();
        cart.setUser(user);

        List<Cart> cartList = cartMapper.selectProductByUid(cart);

        cartList.forEach(e -> System.out.println(e));
    }

    /**
     * 测试 insertCart()
     *         > 成功！
     */
    @Test
    public void test2() {

        User user = new User();
        user.setUid(5);

        Product product = new Product();
        product.setPid(8);

        Cart cart = new Cart();
        cart.setCount(12);
        cart.setUser(user);
        cart.setProduct(product);

        int a = cartMapper.insertCart(cart);

        System.out.println(a);
    }

    /**
     * 测试 deleteAllProductsByUid()
     *            > 成功！
     */
    @Test
    public void test3() {

        User user = new User();
        user.setUid(5);

        Cart cart = new Cart();
        cart.setUser(user);

        int a = cartMapper.deleteAllProductsByUid(cart);

        System.out.println(a);
    }

    /**
     * 测试 deleteByPrimaryKey()
     *            > 成功！
     */
    @Test
    public void test4() {

        Cart cart = new Cart();
        cart.setCid(8);

        int a = cartMapper.deleteByPrimaryKey(cart);

        System.out.println(a);
    }

    /**
     * 测试 updateCountByPidAndUid()
     *            > 成功！
     */
    @Test
    public void test5() {

        Product product = new Product();
        product.setPid(3);

        User user = new User();
        user.setUid(1);

        Cart cart = new Cart();
        cart.setCount(25);
        cart.setProduct(product);
        cart.setUser(user);

        int a = cartMapper.updateCountByPidAndUid(cart);

        System.out.println(a);
    }





}
