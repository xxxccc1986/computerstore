package top.year21.computerstore.controller;

import com.google.code.kaptcha.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.year21.computerstore.controller.exception.ValidCodeNotMatchException;
import top.year21.computerstore.entity.User;
import top.year21.computerstore.service.IUserService;
import top.year21.computerstore.utils.JsonResult;
import javax.servlet.http.HttpSession;


/**
 * @author hcxs1986
 * @version 1.0
 * @description: 处理用户请求的控制器
 * @date 2022/7/10 23:44
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController extends BaseController{

    @Autowired
    private IUserService userService;

    //用户注册
    @PostMapping
    public JsonResult<Void> userRegister(User user,HttpSession session,String code) {
        //从session取出验证码
        String validCode = (String) session.getAttribute(Constants.KAPTCHA_SESSION_KEY);
        //判断验证码是否一致
        if (!validCode.equals(code)){
            throw new ValidCodeNotMatchException("验证码错误,请重试！");
        }
        //执行插入操作
        userService.userRegister(user);
        return new JsonResult<>(OK);
    }

    //用户登录
    @GetMapping
    public JsonResult<User> userLogin(User user, HttpSession session,String code){
        //将存储在session的kaptcha所生成的验证码取出
        String validCode = (String) session.getAttribute(Constants.KAPTCHA_SESSION_KEY);
        //判断验证码是否一致
        if (!validCode.equals(code)){
            throw new ValidCodeNotMatchException("验证码错误,请重试！");
        }
        //执行登录操作
        User loginUser = userService.userLogin(user);
        //分别将用户的session保存到服务端
        session.setAttribute("uid",loginUser.getUid());
        session.setAttribute("username",loginUser.getUsername());
        //优化一下传回前端的user数据，有些字段是不需要的。
        //只将用户名和uid进行回传
        User newUser = new User();
        newUser.setUsername(loginUser.getUsername());
        newUser.setUid(loginUser.getUid());
        newUser.setGender(loginUser.getGender());
        newUser.setPhone(loginUser.getPhone());
        newUser.setEmail(loginUser.getEmail());
        newUser.setAvatar(loginUser.getAvatar());

        return new JsonResult<>(OK,newUser);
    }

    //用户重置密码
    @PostMapping("/resetPassword")
    public JsonResult<Void> userResetPwd(@RequestParam("oldPassword") String oldPwd,
                                         @RequestParam("newPassword") String newPwd,
                                         HttpSession session){
        userService.userResetPwd(oldPwd, newPwd, session);

        //在用户修改密码之后清除session中保存的密码
        session.setAttribute("uid",null);
        return new JsonResult<>(OK);
    }

    @GetMapping("/queryUser")
    public JsonResult<User> queryUserByUid(HttpSession session){
        Integer uid = getUserIdFromSession(session);

        User user = userService.queryUserByUid(uid);

        //将用户名、id、电话、邮箱、性别进行回传
        User newUser = new User();
        newUser.setUsername(user.getUsername());
        newUser.setUid(user.getUid());
        newUser.setGender(user.getGender());
        newUser.setPhone(user.getPhone());
        newUser.setEmail(user.getEmail());
        newUser.setAvatar(user.getAvatar());

        return new JsonResult<>(OK,newUser);
    }


    //用户个人信息更新
    @PostMapping("/updateInfo")
    public JsonResult<User> userInfoUpdate(String phone,String email,Integer gender,HttpSession session){
        //从session中取出用户名和uid
        String username = getUsernameFromSession(session);
        Integer uid = getUserIdFromSession(session);

        //更新数据
        userService.userUpdateInfo(phone, email, gender, username, uid);

        User user = userService.queryUserByUid(uid);

        //将用户名、id、电话、邮箱、性别进行回传
        User newUser = new User();
        newUser.setUsername(user.getUsername());
        newUser.setUid(user.getUid());
        newUser.setGender(user.getGender());
        newUser.setPhone(user.getPhone());
        newUser.setEmail(user.getEmail());
        newUser.setAvatar(user.getAvatar());

        return new JsonResult<>(OK,newUser);
    }

    //处理用户退出登录的请求
    @GetMapping("/exit")
    public JsonResult<Void> exitUserLoginStatus(HttpSession session){
        session.removeAttribute("username");
        session.removeAttribute("uid");
        return new JsonResult<>(OK);
    }
}
