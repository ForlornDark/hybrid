package com.lfm.test.encrypt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.security.Key;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAmount;
import java.time.temporal.TemporalUnit;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Slf4j
public class JWTUtil {
    public static final String secretKey ="SiHan Technoafdsafdsaadfaslogy SIGNING_KEY";
    public static final Key key = Keys.hmacShaKeyFor(secretKey.getBytes());

    public static final int expire_time = 2;

    public static final TimeUnit unit = TimeUnit.HOURS;

    public static final String ISSUER = "xx Technology";


    public static void main(String[] args){
        String generate = generate(Collections.singletonMap("key", "valuse"));
        log.info(generate);
        Claims parse = parse(generate);
        log.info((String) parse.get("key"));
    }

    public static String generate(Map<String,Object> claims){
        return Jwts.builder()
                .signWith(key,SignatureAlgorithm.HS256)
                .setIssuer(ISSUER)
                .setExpiration(getExpireDate())
                .addClaims(claims).compact();
    }

    public static Date getExpireDate(){
        Instant instant = LocalDateTime.now().plus(expire_time, ChronoUnit.DAYS).atZone(ZoneId.systemDefault()).toInstant();
        return Date.from(instant);
    }

    public static Claims parse(String token){
        Jws<Claims> claimsJws = null;
        Claims body = null;
        try{
            claimsJws = Jwts.parser()
                    .setSigningKey(key) // <----
                    .parseClaimsJws(token);
        }catch (Exception e){
            if (e instanceof ExpiredJwtException){
                body = ((ExpiredJwtException) e).getClaims();
            }
            log.info("",e);
        }
       if (claimsJws != null){
           body = claimsJws.getBody();
       }
        return body;
    }
}
