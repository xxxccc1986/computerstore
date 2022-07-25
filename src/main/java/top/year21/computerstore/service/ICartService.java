package top.year21.computerstore.service;


import top.year21.computerstore.entity.Cart;
import top.year21.computerstore.vo.CartVo;

import java.util.Date;
import java.util.List;

/**
 * @author hcxs1986
 * @version 1.0
 * @description: 处理购物车管理的接口
 * @date 2022/7/17 0:05
 */
public interface ICartService  {

    //根据uid和pid查询Cart信息的抽象方法
    Cart queryCartByUidAndPid(Integer uid,Integer pid);

    //更新数据的抽象方法
    int UpdateCartInfo(Cart cart,String modifiedUser, Date modifiedTime);

    //新增数据的抽象方法
    int addCart(Cart cart,String createdUser,Date createdTime,String modifiedUser, Date modifiedTime);

    //根据uid查询用户CartVo信息的抽象方法
    List<CartVo> queryCartByUid(Integer uid);

    //根据cid查询用户cart信息的抽象方法
    Cart queryCartByCid(Integer cid);

    //根据cid更新用户cart数量的抽象方法
    int updateCartNumByCid(Integer num,String modifiedUser, Date modifiedTime,Integer cid);

    //根据cid返回cartVo对象的抽象方法
    CartVo queryCartVoByCid(Integer cid);

    //根据cids数组批量查询对应订单信息
    List<CartVo> queryCartByCids(Integer[] cids);

    //根据cid删除指定的购物车商品的抽象方法
    int deleteCartByCid(Integer cid);

    //根据uid和pid删除对应的t_cart表中的数据的抽象方法
    int deleteCartByUidAndPid(Integer uid,Integer pid);

}
