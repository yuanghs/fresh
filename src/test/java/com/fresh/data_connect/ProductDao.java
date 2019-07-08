package com.fresh.data_connect;

import com.fresh.bean.Category;
import com.fresh.bean.Product;
import com.fresh.mappers.ProductMapper;
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
public class ProductDao {

    @Autowired
    private ProductMapper productMapper;

    /**
     * 测试 selectRandomProducts()
     *                  > 成功！
     */
    @Test
    public void test1() {

        List<Product> list = productMapper.selectRandomProducts();

        list.forEach(e -> System.out.println(e));

    }

    /**
     * 测试 selectProductByPrimaryKey()
     *                     > 成功！
     */
    @Test
    public void test2() {

//        Category category= new Category();
//        category.setCid(4);

        Product product = new Product();
        product.setPid(2);
//        product.setCategory(category);

        Product product1 = productMapper.selectProductByPrimaryKey(product);

        System.out.println(product1);
    }

    /**
     * 测试 selectProductsByPname()
     *                     > 成功！
     */
    @Test
    public void test3() {

        Product product = new Product();
        product.setPname("果");

        List<Product> product1 = productMapper.selectProductsByPname(product);

        product1.forEach(e -> System.out.println(e));

    }

}
