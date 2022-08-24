package com.jt.system.controller;

import com.jt.common.core.domain.R;
import com.jt.system.business.UserBusiness;
import com.jt.system.feign.model.LoginUser;
import com.jt.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.jt.common.security.annotation.InnerAuth;

/**
 * TestController
 *
 * @Author: jt
 * @Date: 2022/8/23 11:45
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserBusiness userBusiness;

    @GetMapping("/info")
    @InnerAuth
    public R<LoginUser> getUserInfo(String username) {
        LoginUser loginUser = userBusiness.getUserInfo(username);
        return R.ok(loginUser);
    }
}
