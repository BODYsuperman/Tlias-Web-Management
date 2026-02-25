package com.alex;

import com.alex.util.JwtUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.Test;


import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtTest {
    @Test
public void testGenJwt() {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", 10);
        claims.put("username", "itheima");

        // 使用 SecretKey 而不是字符串密钥
        String secret = "aXRjYXN0aXRjYXN0aXRjYXN0aXRjYXN0"; // 需要足够长的密钥
        Key key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));

        String jwt = Jwts.builder()
                .setClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis() + 12 * 3600 * 1000))
                .signWith(key)  // 使用 Key 对象而不是字符串
                .compact();

        System.out.println(jwt);



}

    public static void main(String[] args) {
        // 1. 创建 claims
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", 10);
        claims.put("username", "itheima");
        claims.put("role", "admin");

        // 2. 生成 JWT
        String jwt = JwtUtils.generateJwt(claims);
        System.out.println("生成的 JWT: " + jwt);

        // 3. 解析 JWT
        Claims parsedClaims = JwtUtils.parseJWT(jwt);
        System.out.println("解析结果: " + parsedClaims);

        // 4. 获取特定字段
        Integer id = (Integer) JwtUtils.getClaimFromJwt(jwt, "id");
        String username = (String) JwtUtils.getClaimFromJwt(jwt, "username");
        System.out.println("id: " + id + ", username: " + username);

        // 5. 验证 JWT
        boolean isValid = JwtUtils.validateJwt(jwt);
        System.out.println("JWT是否有效: " + isValid);
    }


}
