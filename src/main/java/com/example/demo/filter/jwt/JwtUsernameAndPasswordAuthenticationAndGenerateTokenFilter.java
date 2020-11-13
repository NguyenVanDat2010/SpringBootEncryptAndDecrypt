//package com.example.demo.filter.jwt;
//
//import com.example.demo.pojo.dto.UserLoginDTO;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//import javax.annotation.Resource;
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.Collections;
//import java.util.Date;
//import java.util.stream.Collectors;
//
//public class JwtUsernameAndPasswordAuthenticationAndGenerateTokenFilter extends UsernamePasswordAuthenticationFilter {
//    private final static Logger LOGGER = LoggerFactory.getLogger(JwtUsernameAndPasswordAuthenticationAndGenerateTokenFilter.class);
//
//    @Resource
//    private AuthenticationManager authenticationManager;
//
//    @Resource
//    private final JwtUtils jwtUtils;
//
//    public JwtUsernameAndPasswordAuthenticationAndGenerateTokenFilter(AuthenticationManager authenticationManager, JwtUtils jwtUtils) {
//        this.authenticationManager = authenticationManager;
//        this.jwtUtils = jwtUtils;
//    }
//
//    @Override
//    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
//        try {
//            UserLoginDTO userLoginDTO = new ObjectMapper().readValue(request.getInputStream(), UserLoginDTO.class);
//
//            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userLoginDTO.getUsername(), userLoginDTO.getPassword(), Collections.emptyList());
//
//            return authenticationManager.authenticate(authenticationToken);
//        }catch (IOException e){
//            LOGGER.info(e.getMessage());
//            throw new RuntimeException(e);
//        }
//    }
//
//    @Override
//    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
//        long now = System.currentTimeMillis();
//
//        String token = Jwts.builder()
//                .setSubject(authResult.getName())
//                .claim("authorities", authResult.getAuthorities().stream()
//                    .map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
//                .setIssuedAt(new Date(now))
//                .setExpiration(new Date(now + jwtUtils.getExpiration() * 1000))
//                .signWith(SignatureAlgorithm.HS512, jwtUtils.getSecret().getBytes())
//                .compact();
//
//        response.addHeader(jwtUtils.getHeader(), jwtUtils.getPrefix() + token);
//    }
//}
