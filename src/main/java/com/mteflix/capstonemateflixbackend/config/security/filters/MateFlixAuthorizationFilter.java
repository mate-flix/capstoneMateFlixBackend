package africa.semicolon.gemstube.config.security.filters;

import africa.semicolon.gemstube.config.security.services.JwtService;
import africa.semicolon.gemstube.config.security.utils.SecurityUtils;
import africa.semicolon.gemstube.models.User;
import africa.semicolon.gemstube.services.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@Slf4j
@AllArgsConstructor
public class GemsTubeAuthorizationFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final UserService userService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        boolean isRequestToPublicEndpoint = request.getMethod().equals("POST")&&
                SecurityUtils.getPublicEndpoints().contains(request.getServletPath());

        if (isRequestToPublicEndpoint) filterChain.doFilter(request, response);

        else {
            String authorizationHeader = request.getHeader("Authorization"); //Bearer qhgfdhjhdguy636638...
            log.info("auth header:: {}", authorizationHeader);
            String token = authorizationHeader.substring("Bearer ".length()); //removing "Bearer "
            String username = jwtService.extractUsernameFrom(token);

            log.info("username:: {}", username);
            User user = userService.getUserBy(username);
            var authorities = user.getAuthorities().stream()
                                  .map(authority -> new SimpleGrantedAuthority(authority.name()))
                                  .toList();
            log.info("authorities:: {}", authorities);
            var authentication = new UsernamePasswordAuthenticationToken(user.getEmail(), null, authorities);

            SecurityContextHolder.getContext().setAuthentication(authentication);
            filterChain.doFilter(request, response);
        }

    }
}
