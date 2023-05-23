package cn.chan.idempotentspringstarter.interceptor;

import cn.chan.idempotentspringstarter.annotation.ApiIdempotent;
import cn.chan.idempotentspringstarter.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author: piaoxue
 * @date: 2023/5/23 - 14:39
 * @description:
 **/
public class IdempotentTokenInterceptor implements HandlerInterceptor {
    @Autowired
    private TokenService tokenService;

    public IdempotentTokenInterceptor() {
    }

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            ApiIdempotent apiIdempotent = handlerMethod.getMethod().getAnnotation(ApiIdempotent.class);
            if (apiIdempotent != null) {
                this.tokenService.checkToken(request);
            }

        }
        return true;
    }

    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
    }

    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
    }
}