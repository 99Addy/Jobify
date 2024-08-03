package com.aditya.jobify.config;

import com.aditya.jobify.service.JwtService;
import com.aditya.jobify.service.MyUserDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtService;

    @Autowired
    ApplicationContext context;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");
        String token = null;
        String userName = null;
        if(authHeader != null && authHeader.startsWith("Bearer ")){
            token = authHeader.substring(7);
            userName = jwtService.extractUsername(token);
        }

        //username and authentication is already present no need to validate again
        if(userName != null && SecurityContextHolder.getContext().getAuthentication() == null){

            UserDetails userDetails= context.getBean(MyUserDetailsService.class).loadUserByUsername(userName);

            if(jwtService.validateToken(token, userDetails)){
                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                //This is a new auth token which have no idea of request just the userDetails so setting request to this
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                //Setting our new token into the context
                SecurityContextHolder.getContext().setAuthentication(authToken);

                // Add the user's email to the request attributes
                request.setAttribute("email", userDetails.getUsername());
            }
        }
        //Calling next filters in chain
        filterChain.doFilter(request, response);
    }
}
