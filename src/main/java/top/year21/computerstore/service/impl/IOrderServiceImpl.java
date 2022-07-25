package top.year21.computerstore.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.year21.computerstore.entity.*;
import top.year21.computerstore.mapper.OrderMapper;
import top.year21.computerstore.service.IAddressService;
import top.year21.computerstore.service.ICartService;
import top.year21.computerstore.service.IOrderService;
import top.year21.computerstore.service.IProductService;
import top.year21.computerstore.service.exception.InsertException;
import top.year21.computerstore.service.exception.OrderNotExistsException;
import top.year21.computerstore.service.exception.UpdateException;
import top.year21.computerstore.vo.OrderVo;

import java.util.Date;
import java.util.List;


/**
 * @author hcxs1986
 * @version 1.0
 * @description: 处理订单相关业务层接口的实现类
 * @date 2022/7/18 21:59
 */
@Service
public class IOrderServiceImpl implements IOrderService {
    @Autowired(required = false)
    private OrderMapper orderMapper;
    @Autowired(required = false)
    private IAddressService addressService;
    @Autowired(required = false)
    private ICartService cartService;
    @Autowired(required = false)
    private IProductService productService;

    /**
     * Description : 处理根据创建order订单的具体逻辑
     * @date 2022/7/19
     * @param aid 用户地址aid
     * @param totalPrice 订单商品总价格
     * @param uid 用户uid
     * @param username 用户的名字
     * @return top.year21.computerstore.entity.Order
     **/
    @Override
    public Order insertOrder(Integer aid,Long totalPrice,Integer uid,String username) {
       //根据控制层传入的aid进行查询
        Address address = addressService.queryAddressByAid(aid);

        //创建一个用于向持久层传输的Order实体类对象
        Order order = new Order();

        //补全order对象的空白字段
        order.setUid(uid);
        order.setAid(aid);
        order.setRecvName(address.getName());
        order.setRecvPhone(address.getPhone());
        order.setRecvProvince(address.getProvinceName());
        order.setRecvCity(address.getCityName());
        order.setRecvArea(address.getAreaName());
        order.setRecvAddress(address.getAddress());
        order.setTotalPrice(totalPrice);
        order.setStatus(0); //表示未支付
        Date createdTime = new Date();
        order.setOrderTime(createdTime);
        order.setPayTime(null);
        order.setCreatedUser(username);
        order.setModifiedUser(username);
        order.setCreatedTime(createdTime);
        order.setModifiedTime(createdTime);

        //调用持久层进行插入
        int result = orderMapper.insertOneOrder(order);

        if (result == 0){
            throw new InsertException("服务器出现错误，创建订单失败");
        }
        //根据oid查询指定的订单，并返回给控制层
        return  orderMapper.queryOrderByOid(order.getOid());
    }

    /**
     * Description : 处理根据oid创建orderItem订单的具体逻辑
     * @date 2022/7/19
     * @param oid 订单的oid
     * @param cid 购物车商品cid
     * @param num 添加的数量
     * @param username 用户名
     * @return int
     **/
    @Override
    public int insertOrderItem(Integer oid, Integer cid, Integer num, String username) {
        //根据cid查询订单获取pid
        Cart cart = cartService.queryCartByCid(cid);

        //取出pid的值
        Integer pid = cart.getPid();

        //根据pid查询商品信息
        Product product = productService.queryProductById(pid);

        //创建一个用于向持久层传输的OrderItem实体类对象
        OrderItem orderItem = new OrderItem();

        //补全orderItem对象的空白字段
        orderItem.setOid(oid);
        orderItem.setPid(pid);
        orderItem.setTitle(product.getTitle());
        orderItem.setImage(product.getImage());
        orderItem.setPrice(product.getPrice());
        orderItem.setNum(num);
        Date createdTime = new Date();
        orderItem.setCreatedUser(username);
        orderItem.setCreatedTime(createdTime);
        orderItem.setModifiedUser(username);
        orderItem.setModifiedTime(createdTime);

        //调用持久层进行插入
        int result = orderMapper.insertOneOrderItem(orderItem);
        if (result == 0){
            throw new InsertException("服务器出现错误，创建订单失败");
        }
        return result;
    }


    /**
     * Description : 处理根据从商品界面进入创建orderItem订单的具体逻辑
     * @date 2022/7/21
     * @param oid 订单的oid
     * @param num 添加的数量
     * @param pid 当前商品的pid
     * @param username 用户名
     * @return int
     **/
    @Override
    public int insertOrderItemFromProductHtml(Integer oid,Integer pid, Integer num, String username) {
        //根据pid查询商品信息
        Product product = productService.queryProductById(pid);

        //创建一个用于向持久层传输的OrderItem实体类对象
        OrderItem orderItem = new OrderItem();

        //补全orderItem对象的空白字段
        orderItem.setOid(oid);
        orderItem.setPid(pid);
        orderItem.setTitle(product.getTitle());
        orderItem.setImage(product.getImage());
        orderItem.setPrice(product.getPrice());
        orderItem.setNum(num);
        Date createdTime = new Date();
        orderItem.setCreatedUser(username);
        orderItem.setCreatedTime(createdTime);
        orderItem.setModifiedUser(username);
        orderItem.setModifiedTime(createdTime);

        //调用持久层进行插入
        int result = orderMapper.insertOneOrderItem(orderItem);
        if (result == 0){
            throw new InsertException("服务器出现错误，创建订单失败");
        }
        return result;
    }

    /**
     * Description : 根据订单oid查询订单的具体逻辑
     * @date 2022/7/20
     * @param oid 订单oid
     * @return top.year21.computerstore.entity.Order
     **/
    @Override
    public Order queryOrderByOid(Integer oid) {
        Order order = orderMapper.queryOrderByOid(oid);
        if (order == null){
            throw new OrderNotExistsException("订单不存在！！！");
        }
        return order;
    }

    /**
     * Description : 根据订单oid修改订单状态的具体逻辑
     * @date 2022/7/20
     * @param oid 订单oid
     * @param uid 用户uid
     * @param status 要修改成的状态
     * @return int
     **/
    @Override
    public int updateOrderStatusByOid(Integer oid, Integer uid, Integer status) {
        //先查询一下订单信息
        Order order = orderMapper.queryOrderByOid(oid);
        if(order == null){
            throw new OrderNotExistsException("无订单信息！！！");
        }

        int result = 0;
        //status == 0代表刚刚创建
        if (order.getStatus() == 0){
            //修改支付时间
            Date payTime = new Date();
            result = orderMapper.updateStatusByOidInt(oid, status,payTime);

            //根据oid查找具体的OrderItem信息
            List<OrderItem> orderItems = orderMapper.queryOrderItemByOid(oid);
            for (OrderItem o: orderItems) {
                //从OrderItem中取得pid
                Integer pid = o.getPid();
                //根据pid和uid删除购物车中的商品
                cartService.deleteCartByUidAndPid(uid, pid);
            }
        }else {
            //除了status == 0的状况其他的都可以直接修改其状态
            //修改订单状态
            result = orderMapper.updateStatusByOidInt(oid,status,order.getPayTime());
        }

        if (result == 0){
            throw new UpdateException("服务器异常，修改订单状态失败");
        }

        return result;
    }

    /**
     * Description : 根据订单oid查询orderItem信息的具体逻辑
     * @date 2022/7/20
     * @param oid 订单oid
     * @return java.util.List<top.year21.computerstore.entity.OrderItem>
     **/
    @Override
    public List<OrderItem> queryOrderItemByOid(Integer oid) {
        List<OrderItem> orderItems = orderMapper.queryOrderItemByOid(oid);

        if (orderItems.size() == 0){
            throw new OrderNotExistsException("订单不存在！！！");
        }

        return orderItems;
    }

    /**
     * Description : 根据订单oid查询订单的具体逻辑
     * @date 2022/7/20
     * @param oid 订单oid
     * @return java.util.List<top.year21.computerstore.vo.OrderVo>
     **/
    @Override
    public List<OrderVo> queryOrderVoByOid(Integer oid) {

        List<OrderVo> orderVos = orderMapper.queryOrderVoByOid(oid);
        for (OrderVo vo: orderVos) {
            //根据每个订单的oid查询地址信息
            Address address = addressService.queryAddressByAid(vo.getAid());
            //补全OrderVo值对象中的空白字段
            vo.setZip(address.getZip());
            vo.setPhone(address.getPhone());
            vo.setProvinceName(address.getProvinceName());
            vo.setCityName(address.getCityName());
            vo.setAreaName(address.getAreaName());
            vo.setAddress(address.getAddress());
        }

        return orderVos;
    }

    /**
     * Description : 根据订单oid查询订单的具体逻辑
     * @date 2022/7/20
     * @param uid 用户uid
     * @return java.util.List<top.year21.computerstore.vo.OrderVo>
     **/
    @Override
    public List<OrderVo> queryOrderVoByUid(Integer uid,Integer status) {
        List<OrderVo> orderVos = orderMapper.queryOrderVoByUid(uid,status);
        if (orderVos.size() == 0){
            throw new OrderNotExistsException("查询订单为空");
        }
        return orderVos;
    }


}
