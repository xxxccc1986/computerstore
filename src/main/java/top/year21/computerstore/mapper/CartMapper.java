package top.year21.computerstore.mapper;

import org.apache.ibatis.annotations.Param;
import top.year21.computerstore.entity.Cart;
import top.year21.computerstore.vo.CartVo;

import java.util.Date;
import java.util.List;

/**
 * @author hcxs1986
 * @version 1.0
 * @description: 实体类Cart对应的mapper接口
 * @date 2022/7/16 23:45
 */
public interface CartMapper {

    //根据用户uid和商品pid查询某条购物车cid数据
    Cart queryCartByUidAndPid(Integer uid, @Param("pid") Integer pid);

    //更新cart数据
    int updateCartInfo(Cart cart);

    //新增cart数据
    int addCart(Cart cart);

    //根据uid查询用户购物车信息
    List<CartVo> queryAllCartsByUid(Integer uid);

    //根据cid查询用户cart信息
    Cart queryCartByCid(Integer cid);

    //根据cid查询用户cart的num数量
    int UpdateCartNumByCid(Integer num,String modifiedUser, Date modifiedTime,Integer cid);

    //根据cid查询返回CartVo对象
    CartVo queryCartVoByCid(Integer cid);

    //根据cid删除指定商品
    int deleteCartByCid(Integer cid);

    //根据uid和pid删除对应的t_cart表中的数据
    int deleteCartByUidAndPid(Integer uid,Integer pid);
}
