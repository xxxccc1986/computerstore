package top.year21.computerstore.mapper;

import top.year21.computerstore.entity.Order;
import top.year21.computerstore.entity.OrderItem;
import top.year21.computerstore.vo.OrderVo;

import java.util.Date;
import java.util.List;

/**
 * @author hcxs1986
 * @version 1.0
 * @description: 实体类Order对应的mapper接口
 * @date 2022/7/18 21:44
 */
public interface OrderMapper {
    //插入一条order订单数据
    int insertOneOrder(Order order);

    //向order_item表中插入一条orderItem数据
    int insertOneOrderItem(OrderItem orderItem);

    //根据订单号查询订单
    Order queryOrderByOid(Integer oid);

    //根据订单号修改支付状态和支付时间
    int updateStatusByOidInt(Integer oid, Integer status, Date payTime);

    //根据oid能从order_item表中找到对应的pid信息
    List<OrderItem> queryOrderItemByOid(Integer oid);

    //根据oid查询值对象
    List<OrderVo> queryOrderVoByOid(Integer oid);

    //根据uid查询值对象
    List<OrderVo> queryOrderVoByUid(Integer uid,Integer status);
}
