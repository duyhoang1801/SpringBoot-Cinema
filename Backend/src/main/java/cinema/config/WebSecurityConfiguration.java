package cinema.config;

import cinema.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;


@Configuration // kết hợp với @Bean để tạo thành 1 bean trong spring IOC
@EnableWebSecurity
public class WebSecurityConfiguration {
    @Autowired
    private AccountService accountService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("*"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
    @Bean
    public AuthenticationManager authenticationManager() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(accountService);
        authenticationProvider.setPasswordEncoder(passwordEncoder);

        return new ProviderManager(Collections.singletonList(authenticationProvider));
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.cors(Customizer.withDefaults()).authorizeHttpRequests(authorizeRequests ->
                authorizeRequests
                .requestMatchers("/api/not-authenticated", "/api/v1/account/create", "/api/v1/auth/**"
                        , "/api/v1/room/get-all", "/api/v1/seat/**"
                        ,"/api/v1/film/all/**"
                        ,"/api/v1/food/get-all", "/api/v1/food/{id}", "/api/v1/food/search"
                        ,"/api/v1/foodtype/get-all","/api/v1/movieshowtime/all/**"
                        ,"/api/v1/promotion/all/**","/api/v1/review/all/**"
                        ,"/api/v1/theater/get-all").permitAll()
                .requestMatchers("api/admin", "/api/v1/account/get-all", "/api/v1/room/create"
                        ,"/api/account/delete/**", "/api/v1/account/search", "/api/v1/account/find-all-by-phone-number"
                        ,"/api/v1/room/update", "/api/v1/room/{id}"
                        ,"/api/v1/film/admin/**"
                        ,"/api/v1/food/admin/**", "/api/v1/foodtype/admin/**"
                        ,"/api/v1/movieshowtime/admin/**", "/api/v1/promotion/admin/**"
                        ,"/api/v1/theater/admin/**").hasAnyAuthority("ADMIN")
                .requestMatchers("/api/v1/account/{id}", "/api/v1/account/update", "/api/v1/foodTicket/**"
                        ,"/api/v1/reserveSeat/**", "/api/v1/review/user/**"
                        ,"/api/v1/ticket/**").hasAnyAuthority("ADMIN", "USER")
                .anyRequest().authenticated())
                .userDetailsService(accountService);
        http.httpBasic(config -> {})
                .csrf(AbstractHttpConfigurer::disable)
            .sessionManagement(sessionManagement ->
                sessionManagement
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS));


        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }


    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers("/swagger-ui/**", "/swagger-resources/**", "/v3/api-docs/**");
    }
}
