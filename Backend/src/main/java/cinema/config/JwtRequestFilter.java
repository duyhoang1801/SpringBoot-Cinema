package cinema.config;

import cinema.dto.LoginDto;
import cinema.utils.JWTTokenUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.validation.constraints.NotNull;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {
    private static final String AUTHORIZATION = "Authorization";

    @Autowired
    private JWTTokenUtils jwtTokenUtils;


    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, @NotNull HttpServletResponse httpServletResponse,
                                    @NotNull FilterChain filterChain) throws ServletException, IOException {
        // Lấy token từ api (request)
        String token = httpServletRequest.getHeader(AUTHORIZATION);
        String request = httpServletRequest.getRequestURI();

        if (StringUtils.containsAnyIgnoreCase(request, "/api/v1/auth")
                || StringUtils.containsAnyIgnoreCase(request, "/api/v1/account/create")
                || StringUtils.containsAnyIgnoreCase(request, "/api/v1/seat")
                || StringUtils.containsAnyIgnoreCase(request, "/api/v1/room/get-all")
                || StringUtils.containsAnyIgnoreCase(request, "/api/v1/film/all")
                || StringUtils.containsAnyIgnoreCase(request, "/api/v1/food/get-all")
                || StringUtils.containsAnyIgnoreCase(request, "/api/v1/food/{id}")
                || StringUtils.containsAnyIgnoreCase(request, "/api/v1/food/search")
                || StringUtils.containsAnyIgnoreCase(request, "/api/v1/foodtype/get-all")
                || StringUtils.containsAnyIgnoreCase(request, "/api/v1/movieshowtime/all")
                || StringUtils.containsAnyIgnoreCase(request, "/api/v1/promotion/all")
                || StringUtils.containsAnyIgnoreCase(request, "/api/v1/review/all")
                || StringUtils.containsAnyIgnoreCase(request, "/api/v1/theater/get-all")
                || StringUtils.containsAnyIgnoreCase(request, "/swagger-ui")
                || StringUtils.containsAnyIgnoreCase(request, "/swagger-resources")
                || StringUtils.containsAnyIgnoreCase(request, "/v3/api-docs")) {
            // Những api public ko cần check token -> doFilter
            filterChain.doFilter(httpServletRequest, httpServletResponse);
        } else {
            if (jwtTokenUtils.checkToken(token, httpServletResponse, httpServletRequest)) {
                // Giải mã token -> lấy thông tin user -> authen
                LoginDto loginDto = jwtTokenUtils.parseAccessToken(token);
                // Lấy danh sách quyền của user
                List<GrantedAuthority> authorities = new ArrayList<>();
                authorities.add(loginDto.getRole());
                // Tạo đối tượng để Authen vào hệ thống
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        loginDto.getPhoneNumber(), null, authorities);
                SecurityContextHolder.getContext().setAuthentication(authentication);
                filterChain.doFilter(httpServletRequest, httpServletResponse);
            }
        }
    }
}
