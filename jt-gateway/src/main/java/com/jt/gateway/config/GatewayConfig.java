//package com.jt.gateway.config;
//
//import com.alibaba.csp.sentinel.adapter.gateway.common.SentinelGatewayConstants;
//import com.alibaba.csp.sentinel.adapter.gateway.common.api.ApiDefinition;
//import com.alibaba.csp.sentinel.adapter.gateway.common.api.ApiPathPredicateItem;
//import com.alibaba.csp.sentinel.adapter.gateway.common.api.ApiPredicateItem;
//import com.alibaba.csp.sentinel.adapter.gateway.common.api.GatewayApiDefinitionManager;
//import com.alibaba.csp.sentinel.adapter.gateway.common.rule.GatewayFlowRule;
//import com.alibaba.csp.sentinel.adapter.gateway.common.rule.GatewayRuleManager;
//import com.jt.gateway.handler.SentinelFallbackHandler;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.Ordered;
//import org.springframework.core.annotation.Order;
//
//import javax.annotation.PostConstruct;
//import java.util.HashSet;
//import java.util.Set;
//
//
///**
// * GatewayConfig
// *
// * @Author: jt
// * @Date: 2022/8/16 11:28
// */
//@Configuration
//public class GatewayConfig {
//
//    @Bean
//    @Order(Ordered.HIGHEST_PRECEDENCE)
//    public SentinelFallbackHandler sentinelGatewayExceptionHandler()
//    {
//        return new SentinelFallbackHandler();
//    }
//
//    /**
//     * 网关限流规则初始化
//     */
//    @PostConstruct
//    public void doInit(){
//        //加载网关分组限流
//        this.initGatewayRules();
//    }
//
//
//    /**
//     * 普通限流--->分组限流
//     */
//    private void initGatewayRules() {
//        //自定义限流规则，GatewayFlowRule是模板
//        Set<GatewayFlowRule> rules = new HashSet<>();
//        rules.add(new GatewayFlowRule("system")
//                .setCount(3)
//                .setIntervalSec(60));
//        rules.add(new GatewayFlowRule("code")
//                .setCount(3)
//                .setIntervalSec(60));
//
//        //加载规则,普通限流写到这里就可以了
//        GatewayRuleManager.loadRules(rules);
//
//        //加载限流分组
//        this.initGroups();
//    }
//
//    /**
//     * 限流分组
//     */
//    private void initGroups() {
//        Set<ApiDefinition> definitions = new HashSet<>();
//        ApiDefinition systemApi = new ApiDefinition("system").setPredicateItems(new HashSet<ApiPredicateItem>(){
//            private static final long serialVersionUID = 1L;
//            {
//                //匹配/user以及子路径的所有请求进行限流，其他system服务下的接口不限流,设置匹配策略
//                add(new ApiPathPredicateItem().setPattern("/system/user/**").setMatchStrategy(SentinelGatewayConstants.URL_MATCH_STRATEGY_PREFIX));
//            }
//        });
//
//        ApiDefinition codeApi = new ApiDefinition("code").setPredicateItems(new HashSet<ApiPredicateItem>()
//        {
//            private static final long serialVersionUID = 1L;
//            {
//                // 只匹配 /gen/list
//                add(new ApiPathPredicateItem().setPattern("/code/gen/list"));
//            }
//        });
//        definitions.add(systemApi);
//        definitions.add(codeApi);
//        // 加载限流分组
//        GatewayApiDefinitionManager.loadApiDefinitions(definitions);
//    }
//}
