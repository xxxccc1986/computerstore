package top.year21.computerstore.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.year21.computerstore.entity.Product;
import top.year21.computerstore.mapper.ProductMapper;
import top.year21.computerstore.service.IProductService;
import top.year21.computerstore.service.exception.ProductBadStatusException;
import top.year21.computerstore.service.exception.ProductNotExistsException;

import java.util.List;

/**
 * @author hcxs1986
 * @version 1.0
 * @description: 商品业务层接口的实现类
 * @date 2022/7/16 15:01
 */
@Service
public class IProductServiceImpl implements IProductService {
    @Autowired(required = false)
    private ProductMapper productMapper;

    /**
     * Description : 处理查询热销商品的具体逻辑
     * @date 2022/7/16
     * @return java.util.List<top.year21.computerstore.entity.Product>
     **/
    @Override
    public List<Product> queryPriorityProduct() {
        return productMapper.queryPriorityProduct();
    }

    /**
     * Description : 处理查询最新商品的具体逻辑
     * @date 2022/7/16
     * @return java.util.List<top.year21.computerstore.entity.Product>
     **/
    @Override
    public List<Product> queryTheNewProduct() {
        return productMapper.queryTheNewProduct();
    }

    /**
     * Description : 根据商品id查询商品的具体逻辑
     * @date 2022/7/16
     * @param id 商品id
     * @return top.year21.computerstore.entity.Product
     **/
    @Override
    public Product queryProductById(Integer id) {
        Product product = productMapper.queryProductById(id);

        if (product == null){
            throw new ProductNotExistsException("无此商品信息，查询失败");
        }

        if (product.getStatus() == 2){
            throw new ProductBadStatusException("商品已下架");
        }

        if (product.getStatus() == 3){
            throw new ProductBadStatusException("商品已删除");
        }
        //无任何异常则返回数据
        return product;
    }

    /**
     * Description : 根据名称进行模糊查询的具体逻辑
     * @date 2022/7/22
     * @param title 查询的关键字
     * @return com.github.pagehelper.PageInfo<top.year21.computerstore.entity.Product>
     **/
    @Override
    public PageInfo<Product> queryProductByTitle(Integer pageNum, Integer pageSize,String title) {
        //开启分页功能
        PageHelper.startPage(pageNum,pageSize);
        //调用持久层方法进行查询
        List<Product> products = productMapper.queryProductByTitle(title);
        //返回分页数据
        PageInfo<Product> pageInfo = new PageInfo<>(products);
        return pageInfo;
    }
}
