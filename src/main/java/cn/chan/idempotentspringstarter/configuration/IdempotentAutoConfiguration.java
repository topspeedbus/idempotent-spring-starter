package cn.chan.idempotentspringstarter.configuration;

import cn.chan.idempotentspringstarter.controller.TokenController;
import cn.chan.idempotentspringstarter.interceptor.IdempotentTokenInterceptor;
import cn.chan.idempotentspringstarter.service.TokenService;
import cn.chan.idempotentspringstarter.service.impl.TokenServiceImpl;
import org.springframework.context.annotation.Bean;

/**
 * @author: piaoxue
 * @date: 2023/5/23 - 15:16
 * @description:
 **/
public class IdempotentAutoConfiguration {

    @Bean
    TokenService tokenService() {
        return new TokenServiceImpl();
    }

    @Bean
    TokenController tokenController() {
        return new TokenController();
    }

    @Bean
    IdempotentTokenInterceptor idempotentTokenInterceptor() {
        return new IdempotentTokenInterceptor();
    }
}
