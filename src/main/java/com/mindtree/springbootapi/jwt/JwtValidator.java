package com.mindtree.springbootapi.jwt;

import com.google.common.base.Strings;
import com.mindtree.springbootapi.exceptions.ApiException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class JwtValidator extends OncePerRequestFilter {

    private JwtConfig jwtConfig;

    public JwtValidator(JwtConfig jwtConfig) {
        this.jwtConfig = jwtConfig;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        if(Strings.isNullOrEmpty(authorizationHeader) || !authorizationHeader.startsWith(jwtConfig.getPrefix())){
            filterChain.doFilter(request,response);
            return;
        }

        String jwtToken = authorizationHeader.replace(jwtConfig.getPrefix(),"");
        try {
            final Jws<Claims> claimsJws = Jwts.parserBuilder().
                    setSigningKey(jwtConfig.getSecretKey())
                    .build()
                    .parseClaimsJws(jwtToken);

            Claims body = claimsJws.getBody();
            String userName = body.getSubject();
            List<Map<String,String>> authoritiesList =(List<Map<String,String>>) body.get("authorities");
            Set<GrantedAuthority> authorities = authoritiesList
                                                          .stream()
                                                          .map(map->map.get("authority"))
                                                          .map(SimpleGrantedAuthority::new)
                                                          .collect(Collectors.toSet());

            Authentication authentication = new UsernamePasswordAuthenticationToken(userName, null, authorities);

            SecurityContextHolder.getContext().setAuthentication(authentication);
            filterChain.doFilter(request,response);
        }catch (JwtException e){
            throw new ApiException("Invalid jwt token", HttpStatus.UNAUTHORIZED);
        }
    }
}
