//package com.example.demo.filter.jwt;
//
//import com.example.demo.pojo.vo.UserVO;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import org.mybatis.logging.Logger;
//import org.mybatis.logging.LoggerFactory;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.Resource;
//import java.util.Date;
//import java.util.stream.Collectors;
//
//@Component
//public class JwtGenerateToken {
//    private final static Logger LOGGER = LoggerFactory.getLogger(JwtGenerateToken.class);
//
//    @Resource
//    private AuthenticationManager authenticationManager;
//
//    @Resource
//    private final JwtUtils jwtUtils;
//
//    public JwtGenerateToken(AuthenticationManager authenticationManager, JwtUtils jwtUtils) {
//        this.authenticationManager = authenticationManager;
//        this.jwtUtils = jwtUtils;
//    }
//
//    public String generateJwtToken(Authentication authentication) {
//        UserVO userVOPrincipal = (UserVO) authentication.getPrincipal();
//
//        long now = System.currentTimeMillis();
//
//        String token = Jwts.builder()
//                .setSubject(userVOPrincipal.getUsername())
////                .claim("authorites", authResult.getAuthorities().stream()
////                        .map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
//                .setIssuedAt(new Date(now))
//                .setExpiration(new Date(now + jwtUtils.getExpiration() * 1000))
//                .signWith(SignatureAlgorithm.HS512, jwtUtils.getSecret().getBytes())
//                .compact();
//
//        return token;
//
////        response.addHeader(jwtUtils.getHeader(), jwtUtils.getPrefix() + token);
//    }
//}
