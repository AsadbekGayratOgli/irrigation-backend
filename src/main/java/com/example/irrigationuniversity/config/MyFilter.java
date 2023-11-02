package com.example.irrigationuniversity.config;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import io.jsonwebtoken.security.WeakKeyException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class MyFilter extends OncePerRequestFilter {
    private final UserDetailsService userDetailsService;
    public MyFilter(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }


    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException, UnsupportedJwtException, MalformedJwtException, SignatureException, IllegalArgumentException, WeakKeyException, UsernameNotFoundException {
            String token = request.getHeader("token");
        try {
                Claims body = Jwts.parserBuilder()
                        .setSigningKey(Keys.hmacShaKeyFor("adgffsagdafhadghsf24524jbk4htku24bgkw4g3g34h4kj3htjk".getBytes()))
                        .build()
                        .parseClaimsJws(token)
                        .getBody();
                String username = body.get("iss").toString();
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

        } catch (Exception e){
            e.printStackTrace();
        }
                filterChain.doFilter(request,response);
    }
}
