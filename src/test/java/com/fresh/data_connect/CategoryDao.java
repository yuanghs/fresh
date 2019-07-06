package com.fresh.data_connect;

import com.fresh.bean.Category;
import com.fresh.mappers.CategoryMapper;
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
public class CategoryDao {

    @Autowired
    CategoryMapper categoryMapper;

    /**
     * 测试 selectAllCategory()
     *            > 成功！
     */
    @Test
    public void test1() {

        List<Category> categories = categoryMapper.selectAllCategory();

        categories.forEach(e -> System.out.println(e));

    }

    /**
     * 测试 selectByPrimaryKey()
     *            > 成功！
     */
    @Test
    public void test2() {

        Category category = new Category();
        category.setCid(2);

        Category categorie1 = categoryMapper.selectByPrimaryKey(category);

        System.out.println(categorie1.getCname());

    }

    /**
     * 测试 selectCategoryByCid()
     *            > 成功！
     */
    @Test
    public void test3() {

        Category category = new Category();
        category.setCid(2);

        Category categorie1 = categoryMapper.selectCategoryByCid(category);

        System.out.println(categorie1);

    }


}
