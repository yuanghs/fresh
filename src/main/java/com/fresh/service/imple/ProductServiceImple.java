package com.fresh.service.imple;

import com.fresh.bean.Category;
import com.fresh.bean.Product;
import com.fresh.mappers.CategoryMapper;
import com.fresh.mappers.ProductMapper;
import com.fresh.service.ProductService;
import com.fresh.vo.ProductDetailVO;
import com.fresh.vo.ProductVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;

/**
 * @author sdy
 * @date 2019/7/3
 */
@Service("productService")
public class ProductServiceImple implements ProductService {
    @Resource
    private ProductMapper productMapper;

    @Resource
    private CategoryMapper categoryMapper;

    /**
     * 随机获取6条
     * @return
     */
    @Override
    public List<ProductVO> random() {
        List<Product> products = productMapper.selectRandomProducts();
        List<ProductVO> pList = new LinkedList<>();
        ProductVO product = new ProductVO();
        for (Product p : products) {
            product.setPid(p.getPid());
            product.setPname(p.getPname());
            product.setPrice(p.getPrice());
            product.setPlink(p.getPlink());
            pList.add(product);
        }
        return pList;
    }

    /**
     * 根据pid获取商品
     * @param product pid
     * @return
     */
    @Override
    public ProductDetailVO getVoById(Product product) {
        Product pro = productMapper.selectProductByPrimaryKey(product);
        ProductDetailVO detailVO = new ProductDetailVO(pro.getPid(), pro.getPname(), pro.getPrice(),
                pro.getPinfo(), pro.getPlink(), pro.getInventory(), pro.getCategory().getCid(),
                pro.getCategory().getCname());
        return detailVO;
    }

    @Override
    public Product getProductById(Product product) {
        return productMapper.selectProductByPrimaryKey(product);
    }

    /**
     * 通过分类获取商品
     * @param category cid
     * @return
     */
    @Override
    public List<ProductVO> getByKind(Category category) {
        List<Category> categories = categoryMapper.selectAllCategory();
        ProductVO productVO = new ProductVO();
        List<Product> productList = new LinkedList<>();
        for (Category cat: categories) {
            if (category.getCid().equals(cat.getCid())) {
                productList = cat.getProductList();
                break;
            }
        }
        List<ProductVO> productVOS = new LinkedList<>();
        setVO(productList, productVOS, productVO);
        return productVOS;
    }

    /**
     * 通过搜索获取商品列表
     * @param product 关键字
     * @return
     */
    @Override
    public List<ProductVO> search(Product product) {
        List<Product> productList = productMapper.selectProductsByPname(product);
        List<ProductVO> productVOS = new LinkedList<>();
        ProductVO productVO = new ProductVO();
        setVO(productList, productVOS, productVO);
        return productVOS;
    }

    /**
     * Product类和ProductVO转换的通用方法
     * @param productList
     * @param productVOS
     * @param productVO
     */
    private void setVO(List<Product> productList, List<ProductVO> productVOS, ProductVO productVO) {
        for (Product pro: productList) {
            productVO.setPid(pro.getPid());
            productVO.setPlink(pro.getPlink());
            productVO.setPname(pro.getPname());
            productVO.setPrice(pro.getPrice());
            productVOS.add(productVO);
        }
    }

}
