package com.jt.auth.controller;

import com.jt.auth.form.LoginBody;
import com.jt.auth.service.LoginService;
import com.jt.common.core.domain.R;
import com.jt.system.feign.model.LoginUser;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * TokenController
 *
 * @Author: jt
 * @Date: 2022/8/22 16:16
 */
@RestController
public class TokenController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public R<T> login(@RequestBody LoginBody bo){
        //用户登录
        LoginUser loginUserInfoVo =loginService.login(bo.getUsername(),bo.getPassword());

        //生成Token

        return R.ok();
    }

}
