package top.year21.computerstore.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.year21.computerstore.entity.Favorites;
import top.year21.computerstore.entity.Product;
import top.year21.computerstore.mapper.FavoritesMapper;
import top.year21.computerstore.service.IFavoritesService;
import top.year21.computerstore.service.IProductService;
import top.year21.computerstore.service.exception.InsertException;
import top.year21.computerstore.service.exception.UpdateException;

import java.util.List;

/**
 * @author hcxs1986
 * @version 1.0
 * @description: 处理收藏业务的业务层接口的实现类
 * @date 2022/7/21 21:32
 */
@Service
public class IFavoritesServiceImpl implements IFavoritesService {
    @Autowired
    private FavoritesMapper favoritesMapper;
    @Autowired
    private IProductService productService;

    /**
     * Description :
     * @date 2022/7/21
     * @param favorites 实体类对象
     * @return int
     **/
    /**
     * Description : 新增收藏商品的具体逻辑
     * @date 2022/7/21
     * @param uid 用户uid
     * @param pid 商品pid
     * @return int
     **/
    @Override
    public int addFavorites(Integer uid,Integer pid) {
        Favorites favorites = new Favorites();

        //根据pid查询商品信息
        Product product = productService.queryProductById(pid);

        //填充favorites对象空白字段
        favorites.setUid(uid);
        favorites.setPid(pid);
        favorites.setImage(product.getImage());
        favorites.setPrice(product.getPrice());
        favorites.setTitle(product.getTitle());
        favorites.setSellPoint(product.getSellPoint());
        favorites.setStatus(1);

        int result = favoritesMapper.addFavorites(favorites);
        if (result == 0){
            throw new InsertException("服务器异常，收藏商品失败");
        }

        //取出fid返回给前端页面，以便在搜索界面取消收藏使用
        return favorites.getFid();
    }

    /**
     * Description : 根据uid和商品收藏状态查询收藏数据的具体逻辑
     * @date 2022/7/21
     * @param uid 用户uid
     * @param status 查询商品状态
     * @return java.util.List<top.year21.computerstore.entity.Favorites>
     **/
    @Override
    public PageInfo<Favorites> queryFavorites(Integer uid, Integer pageNum,Integer pageSize,Integer status) {
        //开启分页功能，pageNum是当前页，pageSize是每页显示的数据量，这两个值都可以选择让前端传或者自己调整
        PageHelper.startPage(pageNum,pageSize);
        List<Favorites> favorites = favoritesMapper.queryFavoritesByUidAndStatus(uid, status);
        PageInfo<Favorites> pageInfo = new PageInfo<>(favorites);
        return pageInfo;
    }

    /**
     * Description : 根据收藏商品pid和用户uid取消对应商品收藏的具体逻辑
     * @date 2022/7/22
     * @param status 取消收藏的状态
     * @param fid 收藏的fid
     * @param uid 用户uid
     * @return int
     **/
    @Override
    public int updateFavoritesStatus(Integer status, Integer fid, Integer uid) {
        int result = favoritesMapper.updateFavoritesStatus(status, fid, uid);

        if (result == 0){
            throw new UpdateException("服务器异常，取消收藏失败");
        }
        return result;
    }
}
