package top.year21.computerstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.year21.computerstore.entity.Address;
import top.year21.computerstore.service.IAddressService;
import top.year21.computerstore.service.exception.AddressNotExistsException;
import top.year21.computerstore.service.exception.InsertException;
import top.year21.computerstore.utils.JsonResult;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * @author hcxs1986
 * @version 1.0
 * @description: 处理地址管理相关请求的控制器
 * @date 2022/7/13 19:28
 */
@RestController
@RequestMapping("/address")
public class AddressController extends BaseController{
    @Autowired
    private IAddressService addressService;

    /**
     * Description : 处理用户新增地址
     * @date 2022/7/13
     * @param address 用户地址信息
     * @param session 项目启动自动生成的HttpSession对象
     * @return void
     **/
    @PostMapping
    public JsonResult<Void> addAddress(Address address,HttpSession session){

        //从session中取出uid和用户名
        Integer uid = getUserIdFromSession(session);
        String username = getUsernameFromSession(session);

        //调用业务层方法，新增地址
        addressService.addAddress(address,username,uid);
        return new JsonResult<>(OK);
    }

    /**
     * Description : 处理用户查询收货地址
     * @date 2022/7/13
     * @param session 项目启动生成的session
     * @return void
     **/
    @GetMapping
    public JsonResult<List<Address>> queryAllAddress(HttpSession session){
        //获取uid
        Integer uid = getUserIdFromSession(session);
        //查询数据
        List<Address> list = addressService.queryUserAddress(uid);

        for (Address address: list) {
            address.setUid(null);
            address.setProvinceCode(null);
            address.setCityCode(null);
            address.setAreaCode(null);
            address.setZip(null);
            address.setTel(null);
            address.setModifiedTime(null);
            address.setModifiedUser(null);
            address.setCreatedTime(null);
            address.setCreatedUser(null);
        }
        //返回数据
        return new JsonResult<>(OK,list);
    }

    /**
     * Description : 处理设置默认地址的请求
     * @date 2022/7/15
     * @param aid 地址aid
     * @param session 项目启动生成的session
     * @return top.year21.computerstore.utils.JsonResult<java.lang.Void>
     **/
    @PostMapping("/setAddress")
    public JsonResult<Void> setUserDefaultAddress(Integer aid,HttpSession session){
        //查询要修改的地址是否存在
        Address address = addressService.queryAddressByAid(aid);

        if (address == null){
            throw new AddressNotExistsException("该地址不存在，设置失败");
        }
        //从session中取出用户的uid和名字
        Integer uid = getUserIdFromSession(session);
        String modifiedUser = getUsernameFromSession(session);

        Date date = new Date();

        //将该用户的所有地址设为非默认值
        addressService.setNotDefaultAddress(uid);

        //将需要修改的地址设为默认值
        int result = addressService.setOneAddressDefault(aid, modifiedUser, date);

        if (result == 0){
            throw new InsertException("设置地址时服务器或数据库产生未知异常");
        }

        return new JsonResult<>(OK);
    }

    /**
     * Description : 处理删除某个地址的请求
     * @date 2022/7/15
     * @param aid 地址aid
     * @param session 项目启动生成的session
     * @return top.year21.computerstore.utils.JsonResult<java.lang.Void>
     **/
    @PostMapping("/deleteAddress")
    public JsonResult<Void> deleteAddress(Integer aid,HttpSession session){
        //从session中取出用户的名字
        String modifiedUser = getUsernameFromSession(session);

        Date date = new Date();

        //将指定地址删除
        addressService.deleteOneAddress(aid,modifiedUser,date);

        return new JsonResult<>(OK);
    }

    /**
     * Description : 处理查询某个指定aid地址的请求
     * @date 2022/7/15
     * @param aid 地址aid
     * @return top.year21.computerstore.utils.JsonResult<top.year21.computerstore.entity.Address>
     **/
    @GetMapping("/queryOneAddress")
    public JsonResult<Address> queryOneAddress(Integer aid){
        Address address = addressService.queryAddressByAid(aid);
        //过滤部分不需要的字段
        address.setModifiedTime(null);
        address.setModifiedUser(null);
        address.setCreatedTime(null);
        address.setCreatedUser(null);
        address.setIsDelete(0);

        return new JsonResult<>(OK,address);
    }

    /**
     * Description : 处理指定地址更新的请求
     * @date 2022/7/15
     * @param address 实体类对象
     * @param session 项目启动生成的session
     * @return top.year21.computerstore.utils.JsonResult<java.lang.Void>
     **/
    @PostMapping("/updateAddress")
    public JsonResult<Void> updateOneAddress(Address address,HttpSession session){

        //取出session中用户名
        String modifiedUser = getUsernameFromSession(session);

        int result = addressService.updateOneAddress(address, modifiedUser);

        if (result == 0){
            throw new InsertException("修改地址时，服务器或数据库异常");
        }
        return new JsonResult<>(OK);
    }
}
