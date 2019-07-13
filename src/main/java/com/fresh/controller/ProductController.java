package com.fresh.controller;

import com.alibaba.fastjson.JSONObject;
import com.fresh.bean.Category;
import com.fresh.bean.Product;
import com.fresh.service.ProductService;
import com.fresh.vo.ProductDetailVO;
import com.fresh.vo.ProductVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;

/**
 * @author sdy
 * @date 2019/7/3
 */
@Controller
@RequestMapping("/product")
public class ProductController {


    @Autowired
    private ProductService productService;

    /**
     * 主页随机查询10条商品记录
     * @return
     */
    @RequestMapping(value = "/mainList")
    @ResponseBody
    public JSONObject mainList () {
        JSONObject jsonObject = new JSONObject();
        List<ProductVO> random = productService.random();
        if (random != null) {
            jsonObject.put("list", random);
            jsonObject.put("code", "S000");
        } else {
            jsonObject.put("code", "S001");
        }
        return jsonObject;
    }

    /**
     * 用过商品id获取商品详情
     * @param product
     * @return
     */
    @RequestMapping(value = "/getById")
    @ResponseBody
    public JSONObject getById (@RequestBody Product product) {
        JSONObject jsonObject = new JSONObject();
        ProductDetailVO byId = productService.getVoById(product);
        if (byId != null) {
            jsonObject.put("list", byId);
            jsonObject.put("code", "S000");
        } else {
            jsonObject.put("code", "S001");
        }
        return jsonObject;
    }

    /**
     * 获取分类下的商品列表
     * @param category
     * @return
     */
    @RequestMapping("/sortList")
    @ResponseBody
    public JSONObject sortList(@RequestBody Category category) {
        JSONObject jsonObject = new JSONObject();
        List<ProductVO> byKind = productService.getByKind(category);
        if (byKind != null) {
            jsonObject.put("list", byKind);
            jsonObject.put("code", "S000");
        } else {
            jsonObject.put("code", "S001");
        }
        return jsonObject;
    }

    /**
     * 通过搜索获取商品列表
     * @param product
     * @return
     */
    @RequestMapping("/searchList")
    @ResponseBody
    public JSONObject searchList(@RequestBody Product product) {
        JSONObject jsonObject = new JSONObject();
        List<ProductVO> search = productService.search(product);
        if (search != null) {
            jsonObject.put("list", search);
            jsonObject.put("code", "S000");
        } else {
            jsonObject.put("code", "S001");
        }
        return jsonObject;
    }
}
