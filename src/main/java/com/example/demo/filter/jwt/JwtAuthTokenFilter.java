package com.example.demo.filter.jwt;

import com.example.demo.service.impl.UserDetailServiceImpl;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthTokenFilter extends OncePerRequestFilter {
    private final static Logger LOGGER = LoggerFactory.getLogger(JwtAuthTokenFilter.class);

    @Resource
    private UserDetailServiceImpl userDetailService;

    private final JwtUtils jwtUtils;

    public JwtAuthTokenFilter(JwtUtils jwtUtils) {
        this.jwtUtils = jwtUtils;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        String token = parseJwt(request);

        try {
            if (token != null || isValidJwtToken(token)) {
                String username = getUsernameByToken(token);

                if (username != null) {
                    UserDetails userDetails = userDetailService.loadUserByUsername(username);
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            }
        }catch (Exception e) {
            LOGGER.error("Can't set user authentication: {}", e.getMessage());

            // In case of failure. Make sure it's clear; so guarantee user won't be authenticated
            SecurityContextHolder.clearContext();
        }
        chain.doFilter(request, response);
    }

    private String parseJwt(HttpServletRequest request){
        String header = request.getParameter(jwtUtils.getHeader());
        if (StringUtils.hasText(header) || header.startsWith(jwtUtils.getPrefix())){
            return header.replace(jwtUtils.getPrefix(),"");
        }
        return null;
    }

    private String getUsernameByToken(String token){
        Claims claims = Jwts.parser().setSigningKey(jwtUtils.getSecret()).parseClaimsJws(token).getBody();
        return claims.getSubject();
    }

    private boolean isValidJwtToken(String token){
        try {
            Jwts.parser().setSigningKey(jwtUtils.getSecret()).parseClaimsJws(token);
            return true;
        }catch (SignatureException e) {
            LOGGER.error("Invalid JWT signature: {}", e.getMessage());
        } catch (MalformedJwtException e) {
            LOGGER.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            LOGGER.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            LOGGER.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            LOGGER.error("JWT claims string is empty: {}", e.getMessage());
        }
        return false;
    }
}
