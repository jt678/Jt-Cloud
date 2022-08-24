package com.jt.system.feign.client;

import com.jt.common.core.constant.SecurityConstants;
import com.jt.common.core.domain.R;
import com.jt.system.feign.demote.DemoteUserFallback;
import com.jt.system.feign.model.LoginUser;
import org.springframework.cloud.openfeign.FeignClient;
import com.jt.common.core.constant.ServiceNameConstants;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

/**
 * UserClient
 * 这里是一个feign客户端转发
 * @Author: jt
 * @Date: 2022/8/23 14:57
 */
//标名容器Id，指定服务提供方，此处是jt-system，并且指定了熔断降级的方法(推荐fallbackFactory，不推荐fallback)
@FeignClient(contextId = "userClient",value = ServiceNameConstants.SYSTEM_SERVICE,fallbackFactory = DemoteUserFallback.class)
public interface UserClient {
    /**
     * 通过用户名查询用户信息
     *
     * @param username 用户名
     * @param type 请求来源
     * @return 结果
     */
    @GetMapping("/user/info")
    R<LoginUser> getUserInfo(String username, @RequestHeader(SecurityConstants.FROM_SOURCE) String type);
}
