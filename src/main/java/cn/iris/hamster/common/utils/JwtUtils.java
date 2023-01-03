package cn.iris.hamster.common.utils;

import cn.iris.hamster.bean.entity.LoginUser;
import io.jsonwebtoken.*;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import cn.iris.hamster.common.constants.CommonConstants;

import java.util.Date;
/**
 * @author Iris
 * @date 2022/12/26
 */
@Data
@Component
@ConfigurationProperties(prefix = "jwt.data")
public class JwtUtils {

    private String secret;
    private String header;


    /**生产token
     * @param userName
     * @return
     */
    public String createToken(String userName, String id){
        Date now = new Date();
        Date expireDate = new Date(now.getTime() + CommonConstants.TOKEN_TTL_MILLISECONDS);

        return Jwts.builder()
                .setId(id)
                .setHeaderParam("type","JWT")
                .setSubject(userName)
                .setIssuer("Hamster")
                .setIssuedAt(now)
                // 7天过期时间
                .setExpiration(expireDate)
                // 设置512加密算法
                .signWith(SignatureAlgorithm.HS512, secret)
                //合成
                .compact();
    }

    public String createToken(LoginUser loginUser) {
        return createToken(loginUser.getUsername(), String.valueOf(loginUser.getUser().getId()));
    }

    /**解析token
     * @param token
     * @return
     */
    public Claims parseToken(String token){
        try {
            return Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        }catch (Exception e){
            return null;
        }
    }

    /**判断token是否过期
     * @param claims
     * @return
     */
    public boolean isExpire(Claims claims){
        return claims.getExpiration().before(new Date());
    }

    public boolean isExpire(String token) {
        Claims claims = parseToken(token);
        return isExpire(claims);
    }
}