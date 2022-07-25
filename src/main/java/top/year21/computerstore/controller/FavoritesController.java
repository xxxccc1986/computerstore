package top.year21.computerstore.controller;

import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.year21.computerstore.entity.Favorites;
import top.year21.computerstore.service.IFavoritesService;
import top.year21.computerstore.utils.JsonResult;

import javax.servlet.http.HttpSession;

/**
 * @author hcxs1986
 * @version 1.0
 * @description: 处理收藏相关请求的控制器
 * @date 2022/7/21 21:37
 */
@RestController
@RequestMapping("/favorites")
public class FavoritesController extends BaseController{
    @Autowired
    private IFavoritesService favoritesService;

    /**
     * Description : 处理查询收藏商品的请求
     * @date 2022/7/21
     * @param session 项目启动自动生成session对象
     * @param status 查询收藏商品的状态
     * @return top.year21.computerstore.utils.JsonResult<java.util.List<top.year21.computerstore.entity.Favorites>>
     **/
    @GetMapping("/queryFavorites")
    public JsonResult<PageInfo<Favorites>> queryFavorites(HttpSession session, Integer pageNum,Integer pageSize,Integer status){
        Integer uid = getUserIdFromSession(session);
        PageInfo<Favorites> favorites = favoritesService.queryFavorites(uid, pageNum,pageSize,status);

        return new JsonResult<>(OK,favorites);

    }

    /**
     * Description : 处理添加收藏商品的请求
     * @date 2022/7/21
     * @param session 项目启动自动生成session对象
     * @param pid 当前商品的pid
     * @return top.year21.computerstore.utils.JsonResult<java.lang.Void>
     **/
    @PostMapping("/addFavorites")
    public JsonResult<Integer> addFavorites(HttpSession session,Integer pid){
        //从session中取出uid
        Integer uid = getUserIdFromSession(session);
        //执行插入操作并返回fid
        int fid = favoritesService.addFavorites(uid, pid);
        return new JsonResult<>(OK,fid);
    }

    /**
     * Description : 处理取消收藏的请求
     * @date 2022/7/22
     * @param session session 项目启动自动生成session对象
     * @param status 更新的状态
     * @param fid 收藏品的id
     * @return top.year21.computerstore.utils.JsonResult<java.lang.Void>
     **/
    @PostMapping("/updateStatus")
    public JsonResult<Void> cancelFavorites(HttpSession session,Integer status,Integer fid){
        Integer uid = getUserIdFromSession(session);
        favoritesService.updateFavoritesStatus(status,fid,uid);
        return new JsonResult<>(OK);
    }
}
