package com.alex.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Map;

import java.util.Date;

public class JwtUtils {

    private static String signKey = "M6KmQH+vy8bGj9zNqLpRwXyT3UaVcBdEfHgIjKpLmNqR";
    private static Long expire = 43200000L;

    // 将字符串密钥转换为 Key 对象
    private static SecretKey getSigningKey() {
        // 如果 signKey 是 Base64 编码的，需要先解码
        byte[] keyBytes = Base64.getDecoder().decode(signKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    /**
     * 生成JWT令牌
     * @param claims 需要存储的负载数据
     * @return JWT令牌字符串
     */
    public static String generateJwt(Map<String, Object> claims) {
        // 0.13.5 版本使用 builder() 方法，然后通过 build() 构建
        String jwt = Jwts.builder()
                .claims(claims)                    // 设置负载（0.13.5 使用 claims() 替代 addClaims()）
                .subject("user")                    // 可选：设置主题
                .issuedAt(new Date())                // 可选：设置签发时间
                .expiration(new Date(System.currentTimeMillis() + expire))  // 设置过期时间
                .signWith(getSigningKey())          // 使用 Key 对象签名（0.13.5 不再支持字符串密钥）
                .compact();
        return jwt;
    }

    /**
     * 解析JWT令牌
     * @param jwt JWT令牌字符串
     * @return JWT第二部分负载 payload 中存储的内容
     * @throws ExpiredJwtException 如果令牌已过期
     * @throws SignatureException 如果签名验证失败
     * @throws MalformedJwtException 如果令牌格式错误
     */
    public static Claims parseJWT(String jwt) {
        // 0.13.5 版本使用 parser() 方法获取 JwtParserBuilder，然后 build() 构建 JwtParser
        Claims claims = Jwts.parser()
                .verifyWith(getSigningKey())        // 0.13.5 使用 verifyWith() 替代 setSigningKey()
                .build()                             // 需要调用 build() 创建 JwtParser
                .parseSignedClaims(jwt)              // 使用 parseSignedClaims() 替代 parseClaimsJws()
                .getPayload();                        // 使用 getPayload() 替代 getBody()
        return claims;
    }

    /**
     * 解析JWT令牌（带异常处理版本）
     * @param jwt JWT令牌字符串
     * @return JWT第二部分负载 payload 中存储的内容，解析失败返回 null
     */
    public static Claims parseJWTWithException(String jwt) {
        try {
            return Jwts.parser()
                    .verifyWith(getSigningKey())
                    .build()
                    .parseSignedClaims(jwt)
                    .getPayload();
        } catch (ExpiredJwtException e) {
            System.err.println("JWT令牌已过期: " + e.getMessage());
            return null;
        } catch (SignatureException e) {
            System.err.println("JWT签名验证失败: " + e.getMessage());
            return null;
        } catch (MalformedJwtException e) {
            System.err.println("JWT令牌格式错误: " + e.getMessage());
            return null;
        } catch (Exception e) {
            System.err.println("JWT解析异常: " + e.getMessage());
            return null;
        }
    }

    /**
     * 验证JWT令牌是否有效
     * @param jwt JWT令牌字符串
     * @return true: 有效, false: 无效
     */
    public static boolean validateJwt(String jwt) {
        try {
            Jwts.parser()
                    .verifyWith(getSigningKey())
                    .build()
                    .parseSignedClaims(jwt);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 从JWT令牌中获取指定字段
     * @param jwt JWT令牌字符串
     * @param key 字段名
     * @return 字段值，可能为 null
     */
    public static Object getClaimFromJwt(String jwt, String key) {
        Claims claims = parseJWTWithException(jwt);
        return claims != null ? claims.get(key) : null;
    }
}
