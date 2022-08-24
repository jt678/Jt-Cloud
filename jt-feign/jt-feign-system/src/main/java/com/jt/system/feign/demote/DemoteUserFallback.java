package com.jt.system.feign.demote;

import org.springframework.stereotype.Component;

/**
 * DemoteUserFallback
 *
 * @Author: jt
 * @Date: 2022/8/23 15:06
 */
@Component
public class DemoteUserFallback {
    public String DemoteUserFallback(){
        String msg = "调用失败，降级信息";
        return msg;
    }
}
