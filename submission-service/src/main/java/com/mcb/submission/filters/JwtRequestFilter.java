package com.mcb.submission.filters;

import com.mcb.submission.exception.InvalidTokenException;
import com.mcb.submission.utils.JwtTokenUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Objects;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    private final UserDetailsService userDetailsService;

    private static final Logger LOGGER = LogManager.getLogger(JwtRequestFilter.class);

    private static final String AUTHORIZATION = "Authorization";
    private static final String BEARER = "Bearer ";

    public JwtRequestFilter(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {
        final String authorizationHeader = request.getHeader(AUTHORIZATION);
        if (!StringUtils.hasText(authorizationHeader) || !authorizationHeader.startsWith(BEARER)) {
            LOGGER.warn("No JWT with bearer type , authorization : {}", authorizationHeader);
            filterChain.doFilter(request, response);
            return;
        }

        final String token = authorizationHeader.substring(BEARER.length());
        final String userName = JwtTokenUtil.extractUserName(token);

        if (Objects.isNull(userName)) {
            LOGGER.warn("Invalid JWT : Could not extract username from it");
            filterChain.doFilter(request, response);
            return;
        }

        if (Objects.nonNull(SecurityContextHolder.getContext().getAuthentication())) {
            LOGGER.warn("User already authenticated");
            filterChain.doFilter(request, response);
            return;
        }

        try {
            UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
            JwtTokenUtil.validateToken(token, userDetails.getUsername());
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                    userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        } catch (InvalidTokenException iat) {
            LOGGER.warn(iat.getMessage());
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
        } catch (Exception e) {
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            LOGGER.warn(e.getMessage());
        } finally {
            filterChain.doFilter(request, response);
        }
    }

}
