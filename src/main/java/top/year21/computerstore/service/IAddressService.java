package top.year21.computerstore.service;

import top.year21.computerstore.entity.Address;
import java.util.Date;
import java.util.List;

/**
 * @author hcxs1986
 * @version 1.0
 * @description: 处理地址业务层接口
 * @date 2022/7/13 18:58
 */
public interface IAddressService {

    //添加地址业务抽象方法
    void addAddress(Address address,String username,Integer uid);

    //查询用户地址的抽象方法
    List<Address> queryUserAddress(Integer uid);

    //查询某条地址的抽象方法
    Address queryAddressByAid(Integer aid);

    //设置用户所有地址为非默认地址的抽象方法
    int setNotDefaultAddress(Integer uid);

    //设置某个地址为默认地址的抽象方法
    int setOneAddressDefault(Integer aid,String modifiedUser, Date modifiedTime);

    //删除某个指定地址的抽象方法
    int deleteOneAddress(Integer aid,String modifiedUser, Date modifiedTime);

    //修改某个指定地址的抽象方法
    int updateOneAddress(Address address,String modifiedUser);

}
