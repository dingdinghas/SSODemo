package com.example.demo.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.util.Date;

public class JWTUtil {
    public static final String key = "YXNkc2FzYeWbvueJhy9CQVNFNjTovazmjaJkaHNkZ+WbvueJhy9CQVNFNjTovazmjaI=";
    public static String generateJWTToken(String userId, long ttlMillis){ // 过期时间
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256; //加密算法
        Long now = System.currentTimeMillis(); // 当前时间毫秒值
        Date nowDate = new Date(now); // 当前日期
        byte[] base64Binary = DatatypeConverter.parseBase64Binary(key);
        SecretKeySpec secretKeySpec = new SecretKeySpec(base64Binary, signatureAlgorithm.getJcaName());
        Claims claims = Jwts.claims();
        claims.put("id", userId);
        JwtBuilder builder = Jwts.builder().setClaims(claims)
                .setIssuedAt(nowDate)
                .setIssuer("www.example.com")
                .signWith(signatureAlgorithm, secretKeySpec);
        if (ttlMillis >= 0) {
            long expMillis = now + ttlMillis * 1000;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);
        }
        return builder.compact();
    }
    public static Claims parseJWT(String jwt) {
        return Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(key))
                .parseClaimsJws(jwt).getBody();
    }
}
