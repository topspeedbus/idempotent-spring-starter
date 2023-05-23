package cn.chan.idempotentspringstarter.service;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: piaoxue
 * @date: 2023/5/23 - 14:42
 * @description:
 **/
public interface TokenService {
    String createToken();

    void checkToken(HttpServletRequest request);
}
