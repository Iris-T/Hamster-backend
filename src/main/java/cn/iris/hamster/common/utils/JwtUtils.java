package cn.iris.hamster.common.utils;

import cn.iris.hamster.bean.entity.LoginUser;
import cn.iris.hamster.common.exception.BaseException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

/**
 * Jwt工具类
 *
 * @author Iris
 * @ClassName JwtUtils
 * @date 2022/12/28 13:49
 */
public class JwtUtils {
    /**
     * 密钥校对A/foxiris?P/hamster?D/20221228
     */
    private static final String SECRET_KEY = "A/foxiris?P/hamster?D/20221228";
    /**
     * 12小时登录时间
     */
    private static final Long JWT_TTL = 1000 * 60 * 60 * 12L;

    /**
     * 生成jwt
     * @param loginUser
     * @return
     */
    public static String createToken(LoginUser loginUser) {
        return createToken(loginUser, null);
    }

    /**
     * 生成jwt
     * @param loginUser
     * @param ttlMillis token超时时间
     * @return
     */
    public static String createToken(LoginUser loginUser, Long ttlMillis) {
        JwtBuilder builder = getJwtBuilder(
                loginUser.getUsername(),
                ttlMillis,
                String.valueOf(loginUser.getUser().getId()));
        return builder.compact();
    }

    private static JwtBuilder getJwtBuilder(String subject, Long ttlMillis, String id) {
        long nowMillis = System.currentTimeMillis();
        if(ttlMillis==null) {
            ttlMillis = JWT_TTL;
        }
        Date now = new Date(nowMillis);
        Date expDate = new Date(nowMillis + ttlMillis);
        return Jwts.builder()
                //唯一的ID
                .setId(id)
                // 主题  可以是JSON数据
                .setSubject(subject)
                // 签发者
                .setIssuer("hamster")
                // 签发时间
                .setIssuedAt(now)
                //使用HS256对称加密算法签名, 第二个参数为秘钥
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .setExpiration(expDate);
    }

    /**
     * 解析
     *
     * @param jwt
     * @return
     */
    public static Claims parseToken(String jwt) {
        try {
            return Jwts.parser()
                    .setSigningKey(SECRET_KEY)
                    .parseClaimsJws(jwt)
                    .getBody();
        } catch (Exception e) {
            throw new BaseException(e);
        }
    }
}
