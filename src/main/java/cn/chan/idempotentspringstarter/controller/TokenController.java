package cn.chan.idempotentspringstarter.controller;

import cn.chan.idempotentspringstarter.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: piaoxue
 * @date: 2023/5/23 - 15:17
 * @description:
 **/
@RestController
public class TokenController {
    @Autowired
    private TokenService tokenService;

    public TokenController() {
    }

    @GetMapping
    @RequestMapping({"/idempotent/token"})
    public Map<String, Object> token() {
        String token = this.tokenService.createToken();
        Map<String, Object> resultMap = new HashMap();
        resultMap.put("code", 0);
        resultMap.put("data", token);
        resultMap.put("message", null);
        return resultMap;
    }
}
