package com.jt.system.business;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.jt.system.domain.User;
import com.jt.system.feign.domain.SysUser;
import com.jt.system.feign.model.LoginUser;
import com.jt.system.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * UserBusiness
 *
 * @Author: jt
 * @Date: 2022/8/23 15:40
 */
@Service
public class UserBusiness {

    @Autowired
    private UserService userService;

    public LoginUser getUserInfo(String username) {
        LoginUser loginUser = new LoginUser();
        //在用户注册的时候一定要保证userName唯一
        User user = userService.getOne(new LambdaQueryWrapper<User>().eq(User::getUserName, username).eq(User::getDelFlag,"0"));
        SysUser sysUser = new SysUser();
        BeanUtils.copyProperties(user,sysUser);
        //获取到基础信息
        loginUser.setSysUser(sysUser);
        //获取角色和权限
//        loginUser.setRoles();
//        loginUser.setPermissions();

        return loginUser;
    }
}
