package com.pts.adm.security;

import com.pts.module.primary.model.AccountModel;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;

@Service
public class JwtService {

    Key secretKey = Jwts.SIG.HS256.key().build();

    public String generateToken(AccountModel account) {
        return Jwts.builder()
                .subject(account.getUsername())
                .claim("fullname", account.getFullname())
                .claim("email", account.getEmail())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 864000000)) // 10 day
                .signWith(secretKey)
                .compact();
    }

    public String extractUsername(String token) {
        return Jwts.parser()
                .verifyWith((SecretKey) secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    public boolean isTokenValid(String token) {
        try {
            Jwts.parser().verifyWith((SecretKey) secretKey).build().parseSignedClaims(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

}
