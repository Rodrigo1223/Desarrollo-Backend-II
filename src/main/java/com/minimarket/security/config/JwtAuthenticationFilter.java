package com.minimarket.security.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String path = request.getRequestURI();

        // 1. IGNORAR RUTAS PÚBLICAS: 
        // Si la ruta es pública, dejamos que Spring Security (en SecurityConfiguration) decida
        if (path.startsWith("/api/auth/") || path.startsWith("/h2-console/") || path.startsWith("/error")) {
            filterChain.doFilter(request, response);
            return;
        }

        // 2. EXTRAER TOKEN
        String authHeader = request.getHeader("Authorization");
        
        // Si no hay header o no empieza con "Bearer ", simplemente dejamos pasar
        // para que Spring Security maneje el acceso (401 o 403 según corresponda)
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        // 3. LÓGICA DE VALIDACIÓN (Aquí iría tu validación de JWT real)
        // Ejemplo simplificado:
        try {
            String jwt = authHeader.substring(7);
            // AQUÍ VALIDARÍAS TU TOKEN:
            // String username = jwtUtils.extractUsername(jwt);
            
            // Si el token es válido, estableces el contexto de seguridad:
            /*
            UsernamePasswordAuthenticationToken authToken = 
                new UsernamePasswordAuthenticationToken(username, null, Collections.emptyList());
            authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authToken);
            */
            
        } catch (Exception e) {
            // Si falla la validación, no bloqueamos con 404, dejamos que siga el filtro
        }

        filterChain.doFilter(request, response);
    }
}