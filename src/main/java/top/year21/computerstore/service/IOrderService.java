package top.year21.computerstore.service;


import top.year21.computerstore.entity.Order;
import top.year21.computerstore.entity.OrderItem;
import top.year21.computerstore.vo.OrderVo;

import java.util.List;

/**
 * @author hcxs1986
 * @version 1.0
 * @description: 处理订单相关业务层接口
 * @date 2022/7/18 21:59
 */
public interface IOrderService {

    //处理添加order订单数据的抽象方法
    Order insertOrder(Integer aid, Long totalPrice, Integer uid, String username);

    //处理添加orderItem数据的抽象方法
    int insertOrderItem(Integer oid, Integer cid, Integer num, String username);

    //处理从product页面直接购买添加orderItem数据的抽象方法
    int insertOrderItemFromProductHtml(Integer oid,Integer pid, Integer num, String username);

    //根据oid查询order信息的抽象方法
    Order queryOrderByOid(Integer oid);

    //根据oid修改oid的订单状态的抽象方法
    int updateOrderStatusByOid(Integer oid,Integer uid,Integer status);

    //根据oid能从order_item表中找到对应的OrderItem信息的抽象方法
    List<OrderItem> queryOrderItemByOid(Integer oid);

    //根据订单oid查询订单的抽象方法
    List<OrderVo> queryOrderVoByOid(Integer oid);

    //根据用户uid查询订单的抽象方法
    List<OrderVo> queryOrderVoByUid(Integer uid,Integer status);


}
