package com.fresh.mappers;

import com.fresh.bean.Category;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * 分类接口
 */
@Repository
public interface CategoryMapper {

    /**
     * 根据分类主键查询分类
     * @param category
     * @return
     */
    Category selectByPrimaryKey(Category category);

    /**
     * 查询出所有的分类
     * @return
     */
    List<Category> selectAllCategory();

    /**
     * 根据分类主键查询出对应分类下的商品
     * @param category
     * @return
     */
    Category selectProductsByCid(Category category);
}