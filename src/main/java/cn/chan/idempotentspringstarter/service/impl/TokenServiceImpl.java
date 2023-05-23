package cn.chan.idempotentspringstarter.service.impl;

import cn.chan.idempotentspringstarter.exception.IdempotentParamNotFoundException;
import cn.chan.idempotentspringstarter.exception.IdempotentRepetitiveOperationException;
import cn.chan.idempotentspringstarter.service.TokenService;
import cn.hutool.crypto.digest.DigestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author: piaoxue
 * @date: 2023/5/23 - 14:41
 * @description:
 **/
@Service
public class TokenServiceImpl implements TokenService {
    private static final String TOKEN_NAME = "idempotentToken";
    @Autowired
    private RedisTemplate redisTemplate;

    public TokenServiceImpl() {
    }

    public String createToken() {
        String tokenValue = "idempotent:token:" + DigestUtil.md5Hex(UUID.randomUUID().toString());
        this.redisTemplate.opsForValue().set(tokenValue, "0", 3L, TimeUnit.MINUTES);
        return tokenValue;
    }

    public void checkToken(HttpServletRequest request) {
        String token = request.getHeader("idempotentToken");
        if (!StringUtils.hasText(token)) {
            token = request.getParameter("idempotentToken");
            if (!StringUtils.hasText(token)) {
                throw new IdempotentParamNotFoundException("缺少idempotentToken参数");
            }
        }

        if (!this.redisTemplate.hasKey(token)) {
            throw new IdempotentRepetitiveOperationException("请勿重复操作");
        } else {
            boolean del = this.redisTemplate.delete(token);
            if (!del) {
                throw new IdempotentRepetitiveOperationException("请勿重复操作");
            }
        }
    }
}
