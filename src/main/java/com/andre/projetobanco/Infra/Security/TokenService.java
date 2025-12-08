package com.andre.projetobanco.Infra.Security;

import com.andre.projetobanco.Domain.Account;
import com.andre.projetobanco.Domain.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    private Instant genExpirationDate(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }

    public String generateUserToken(User user) {
        try{
            Algorithm algorithm = Algorithm.HMAC256(secret);

            List<String> roles = user.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .toList();

            return JWT.create()
                    .withIssuer("auth-api")
                    .withSubject(user.getCpf())
                    .withClaim("roles", roles)
                    .withExpiresAt(genExpirationDate())
                    .sign(algorithm);
        }
        catch (JWTCreationException e){
            throw new RuntimeException("Error while generating token");
        }
    }

    public String generateAccountToken(Account account) {
        try{
            Algorithm algorithm = Algorithm.HMAC256(secret);

            return JWT.create()
                    .withIssuer("auth-api")
                    .withSubject(account.getAccountNumber())
                    .withExpiresAt(genExpirationDate())
                    .sign(algorithm);
        }
        catch (JWTCreationException e){
            throw new RuntimeException("Error while generating token");
        }
    }

    public DecodedJWT validateToken(String token){
        try{
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("auth-api")
                    .build()
                    .verify(token);
        }
        catch (JWTVerificationException e){
            return null;
        }
    }
}
