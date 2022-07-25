package top.year21.computerstore.mapper;

import org.apache.ibatis.annotations.Param;
import top.year21.computerstore.entity.User;
import java.util.Date;

/**
 * @author hcxs1986
 * @version 1.0
 * @description: User实体类对应的mapper接口
 * @date 2022/7/10 17:03
 */
public interface UserMapper {

    /**
     * Description : 用户注册
     * @date 2022/7/10
     * @param user 用户信息
     * @return int 插入的结果
     **/
    int addUser(User user);

    /**
     * Description : 根据用户名查询用户信息
     * @date 2022/7/10
     * @param username 用户名
     * @return top.year21.computerstore.entity.User
     **/
    User queryUserByUsername(String username);

    /**
     * Description : 用户重置密码
     * @date 2022/7/12
     * @param password 要修改的密码
     * @param modifiedUser 修改人
     * @param modifiedTime 修改时间
     * @param username 用户名
     * @return int
     **/
    int updatePassword(String password,
                       String modifiedUser,
                       Date modifiedTime,
                       String username);


    /**
     * Description : 根据id查询用户信息
     * @date 2022/7/12
     * @param uid 用户id
     * @return top.year21.computerstore.entity.User
     **/
    User queryUserByUid(Integer uid);

    /**
     * Description : 更新用户信息
     * @date 2022/7/12
     * @param phone 电话
     * @param email 邮箱
     * @param gender 性别
     * @param modifiedUser 修改人
     * @param modifiedTime 修改时间
     * @param uid 用户id
     * @return int
     **/
    int UpdateUserInfo(String phone,
                       String email,
                       Integer gender,
                       String modifiedUser,
                       Date modifiedTime,
                       Integer uid);
    /**
     * Description : 处理用户上传头像
     * @date 2022/7/13
     * @param ImgAddress 保存图片的地址
     * @param modifiedUser 修改人
     * @param modifiedTime 修改时间
     * @param uid 用户uid
     * @return int
     **/
    int updateUserAvatar(@Param("file") String ImgAddress,
                         String modifiedUser,
                         Date modifiedTime,
                         Integer uid);


}
