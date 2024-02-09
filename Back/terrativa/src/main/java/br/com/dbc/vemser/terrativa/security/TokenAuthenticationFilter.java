package br.com.dbc.vemser.terrativa.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@RequiredArgsConstructor
public class TokenAuthenticationFilter extends OncePerRequestFilter {
    private final TokenService tokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String tokenFromHeader = getTokenFromHeader(request);

        UsernamePasswordAuthenticationToken user = tokenService.isValid(tokenFromHeader);
        SecurityContextHolder.getContext().setAuthentication(user);

        filterChain.doFilter(request, response);
    }

    private String getTokenFromHeader(HttpServletRequest request) {
        String AUTHORIZATION = "Authorization";
        String header = request.getHeader(AUTHORIZATION);
        String BEARER = "Bearer ";
        if (header != null && header.startsWith(BEARER)) {
            return header.replace(BEARER, "");
        }
        return null;
    }

}
