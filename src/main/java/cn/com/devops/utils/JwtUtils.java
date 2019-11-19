package cn.com.devops.utils;

import cn.com.devops.base.App;
import cn.com.devops.entity.User;
import io.jsonwebtoken.*;
import org.springframework.util.StringUtils;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.util.Date;

/**
 * 使用JWT实现Token认证
 */
public class JwtUtils {

    private static SecretKey generalKey() {
        byte[] encodedKey = Base64.decodeBase64(App.JWT_SECERT);
        SecretKey key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
        return key;
    }

    /**
     * 签发JWT
     * @param user
     * @param ttlMillis
     * @return
     */
    public static String createJWT(User user, long ttlMillis){
        String userId = String.valueOf(user.getId());
        if(StringUtils.isEmpty(userId)){
            return null;
        }
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        SecretKey secretKey = generalKey();
        JwtBuilder builder = Jwts.builder()
                .setId(userId)
                .setSubject(user.getUsername())
                .setIssuedAt(now)
                .signWith(signatureAlgorithm, secretKey);
        if (ttlMillis >= 0) {
            long expMillis = nowMillis + ttlMillis;
            Date expDate = new Date(expMillis);
            builder.setExpiration(expDate);
        }
        return builder.compact();
    }

    /**
     * 解析JWT字符串
     * @param jwt
     * @return
     * @throws Exception
     */
    public static Claims parseJWT(String jwt) throws Exception {
        SecretKey secretKey = generalKey();
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(jwt)
                .getBody();
    }

    /**
     * 获取用户id
     * @param jwt
     * @return Integer userId
     * @throws Exception
     */
    public static Integer getUserIdByJwt(String jwt){
        Claims claims = JwtUtils.validateJWT(jwt);
        if(!StringUtils.isEmpty(claims)){
            return Integer.valueOf(claims.getId());
        }
        return null;
    }

    /**
     * 验证JWT
     * @param jwt
     * @return
     */
    private static Claims validateJWT(String jwt){
        Claims claims = null;
        try {
            claims = JwtUtils.parseJWT(jwt);
        } catch (ExpiredJwtException e) {
            //jwt过期
        	e.printStackTrace();
        } catch (SignatureException e) {
            //验证失败,解密失败,被篡改
        	e.printStackTrace();
        } catch (Exception e) {
        	e.printStackTrace();
        }
        return claims;
    }

}
