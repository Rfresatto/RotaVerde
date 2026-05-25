package br.com.rota_verde.rota_verde.config.security;

import br.com.rota_verde.rota_verde.user.model.UserModel;
import br.com.rota_verde.rota_verde.user.repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class ValidateToken extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String authorizationHeaders = request.getHeader("Authorization");
        String token = "";

        if (authorizationHeaders == null) {
            token = null;
        } else {
            token = authorizationHeaders.replace("Bearer", "").trim();
            String login = tokenService.validateToken(token);

            UserDetails user = userRepository.findByEmail(login);

            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
        }

        filterChain.doFilter(request, response);
    }
}
