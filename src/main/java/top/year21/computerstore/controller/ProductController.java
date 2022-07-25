package top.year21.computerstore.controller;

import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.year21.computerstore.entity.Product;
import top.year21.computerstore.service.IProductService;
import top.year21.computerstore.utils.JsonResult;

import java.util.List;

/**
 * @author hcxs1986
 * @version 1.0
 * @description: 处理商品相关请求的控制器
 * @date 2022/7/16 15:07
 */
@RestController
@RequestMapping("/product")
public class ProductController extends BaseController{
    @Autowired
    private IProductService productService;

    /**
     * Description : 处理热销商品的请求
     * @date 2022/7/16
     **/
    @GetMapping("/hotProduct")
    public JsonResult<List<Product>> queryBestProduct(){
        //查询对应商品
        List<Product> products = productService.queryPriorityProduct();

        return new JsonResult<>(OK,products);
    }

    /**
     * Description : 处理展示最新商品的请求
     * @date 2022/7/16
     * @return top.year21.computerstore.utils.JsonResult<java.util.List<top.year21.computerstore.entity.Product>>
     **/
    @GetMapping("/newProduct")
    public JsonResult<List<Product>> queryNewProduct(){
        //查询对应商品
        List<Product> products = productService.queryTheNewProduct();

        return new JsonResult<>(OK,products);
    }

    /**
     * Description : 处理商品id查询该商品信息的请求
     * @date 2022/7/16
     * @param id 商品id
     * @return top.year21.computerstore.utils.JsonResult<top.year21.computerstore.entity.Product>
     **/
    @GetMapping("/{id}")
    public JsonResult<Product> queryProductById(@PathVariable(value = "id",required = false) Integer id){
        Product product = productService.queryProductById(id);
        return new JsonResult<>(OK,product);
    }

    /**
     * Description : 处理根据产品关键字进行模糊查询的请求
     * @date 2022/7/22
     * @param pageNum 当前页
     * @param pageSize 每页显示数
     * @param title 查询的关键字
     * @return top.year21.computerstore.utils.JsonResult<com.github.pagehelper.PageInfo<top.year21.computerstore.entity.Product>>
     **/
    @GetMapping("/{pageNum}/{pageSize}/{title}")
    public JsonResult<PageInfo<Product>> quertByTitle(@PathVariable("pageNum") Integer pageNum,
                                                      @PathVariable("pageSize") Integer pageSize,
                                                      @PathVariable("title") String title){
        PageInfo<Product> lists = productService.queryProductByTitle(pageNum, pageSize, title);
        return new JsonResult<>(OK,lists);
    }
}
