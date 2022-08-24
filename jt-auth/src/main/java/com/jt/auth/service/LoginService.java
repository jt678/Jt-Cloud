package com.jt.auth.service;

import com.jt.common.core.constant.SecurityConstants;
import com.jt.common.core.domain.R;
import com.jt.common.core.enums.ErrorEnum;
import com.jt.common.core.enums.UserStatus;
import com.jt.common.core.exception.BusinessException;
import com.jt.common.core.exception.ServiceException;
import com.jt.common.core.utils.StringUtils;
import com.jt.system.feign.client.UserClient;
import com.jt.system.feign.domain.SysUser;
import com.jt.system.feign.model.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * LoginService
 *
 * @Author: jt
 * @Date: 2022/8/22 16:19
 */
@Service
public class LoginService {

    @Autowired
    private UserClient userClient;

    public LoginUser login(String username, String password) {

        //1.没输账号或者密码，接口出来试一下这个anyBlank和anyEmpty的区别
        if (StringUtils.isAnyBlank()) {
            throw new BusinessException(ErrorEnum.LOGIN_USERNAME_OR_PSW_NULL);
        }
        //2.账号不符合规范,假设必须为4到10位，且必须是大小写字母加数字
        String regExp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])[A-Za-z0-9]{4,10}$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(username);
        if (m.matches() == false) {
            throw new BusinessException(ErrorEnum.LOGIN_USERNAME_FORMAT_ERROR, "错误账号为：" + username);
        }
        //调用feign模块里的方法，feign再去调用具体模块下的接口
        R<LoginUser> userInfo = userClient.getUserInfo(username, SecurityConstants.INNER);

        int code = userInfo.getCode();
        String msg = userInfo.getMsg();
        LoginUser data = userInfo.getData();
        SysUser sysUser = data.getSysUser();

        if (R.FAIL == code) {
            throw new ServiceException(msg);
        }

        if (StringUtils.isNull(data) || StringUtils.isNull(userInfo)) {
//            throw new BusinessException();
        }
        if (UserStatus.DELETED.getCode().equals(sysUser.getDelFlag())) {
//            recordLogService.recordLogininfor(username, Constants.LOGIN_FAIL, "对不起，您的账号已被删除");
            throw new ServiceException("对不起，您的账号：" + username + " 已被删除");
        }
        if (UserStatus.DISABLE.getCode().equals(sysUser.getStatus())) {
//            recordLogService.recordLogininfor(username, Constants.LOGIN_FAIL, "用户已停用，请联系管理员");
            throw new ServiceException("对不起，您的账号：" + username + " 已停用");
        }

        //校验账号密码，错误超过输入次数，锁定登录
        
        //返回得到的实体
        return data;

    }
}
