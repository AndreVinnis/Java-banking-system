package com.andre.projetobanco.Infra.Security;

import com.andre.projetobanco.Repository.UserRepository;
import com.andre.projetobanco.Services.UserService;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    TokenService tokenService;

    @Autowired
    UserRepository userRepository;

    /*
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var token = this.recoverToken(request);
        if(token != null){
            var cpf = tokenService.validateToken(token);
            UserDetails user = userRepository.findUserDetailsByCpf(cpf);

            var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);
    }
    */

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var token = this.recoverToken(request);

        if (token != null) {

            // 1. Validar e Decodificar o Token (usando o método modificado no TokenService)
            DecodedJWT decodedToken = tokenService.validateToken(token);

            if (decodedToken != null) {
                String cpf = decodedToken.getSubject();

                // 2. EXTRAIR AS ROLES DO PAYLOAD DO JWT
                // O método getClaim("roles") deve buscar a claim que você inseriu na criação do token
                var roles = decodedToken.getClaim("roles").asList(String.class);

                // 3. Criar as autoridades do Spring Security a partir das roles do token
                var authorities = roles.stream()
                        .map(SimpleGrantedAuthority::new)
                        .toList();

                // OBS: Embora não seja estritamente necessário carregar o UserDetails,
                // é bom para ter o objeto principal completo no contexto.
                // Se o seu UserRepository tiver um método que retorne SÓ o UserDetails
                // sem o overhead de todos os relacionamentos, você pode usá-lo:
                UserDetails userDetails = userRepository.findUserDetailsByCpf(cpf);

                // 4. Injetar a autenticação com as autoridades do token
                var authentication = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        authorities // <-- Agora o Spring sabe que é ROLE_EMPLOYEE
                );

                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        filterChain.doFilter(request, response);
    }

    private String recoverToken(HttpServletRequest request){
        var authHeader = request.getHeader("Authorization");
        if (authHeader == null) return null;
        return authHeader.replace("Bearer ", "");
    }
}
