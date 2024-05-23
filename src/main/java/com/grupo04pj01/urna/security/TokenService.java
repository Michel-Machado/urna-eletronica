//package com.grupo04pj01.urna.security;
//
//import com.auth0.jwt.JWT;
//import com.auth0.jwt.algorithms.Algorithm;
//import com.auth0.jwt.exceptions.JWTCreationException;
//import com.auth0.jwt.exceptions.JWTVerificationException;
//import com.grupo04pj01.urna.models.UserModel;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//
//import java.security.AlgorithmConstraints;
//import java.time.Instant;
//import java.time.LocalDateTime;
//import java.time.ZoneOffset;
//
//@Service
//public class TokenService  {
//    @Value("${api.secret.token.secret}")
//    private String secret;
//
//    public String generateToken(UserModel userModel) {
//        try {
//            Algorithm algorithm = Algorithm.HMAC256(secret);
//            String token= JWT.create()
//                    .withIssuer("urna-api")
//                    .withSubject(userModel.getUsuario())
//                    .withExpiresAt(genExpirationDate())
//                    .sign(algorithm);
//
//            return token;
//        }catch (JWTCreationException e){
//            throw new RuntimeException("Erro ao gerar token:", e);
//
//        }
//    }
//
//    public String validateToken(String token){
//        try {
//            Algorithm algorithm=  Algorithm.HMAC256(secret);
//            return JWT.require(algorithm)
//                    .withIssuer("urna-api")
//                    .build()
//                    .verify(token)
//                    .getSubject();
//
//        }catch (JWTVerificationException e){
//            return "";
//
//        }
//    }
//
//    private Instant genExpirationDate(){
//        return LocalDateTime.now().plusHours(15).toInstant(ZoneOffset.of("-03:00"));
//    }
//}
