package jjj.scm.config;

import jakarta.servlet.Filter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {

    private final String[] PASS_URL_LIST = List.of("/", "/test/**", "/login").toArray(new String[0]);
    private final OncePerRequestFilter oncePerRequestFilter;
    private final Filter loggingFilter;

    public SpringSecurityConfig(@Qualifier("customJwtFilter") OncePerRequestFilter oncePerRequestFilter,
                                @Qualifier("customLoggingFilter") Filter loggingFilter) {
        this.oncePerRequestFilter = oncePerRequestFilter;
        this.loggingFilter = loggingFilter;
    }

    /**
     * Spring Security Password 암호화 처리
     *
     * @return
     */
    @Bean
    public PasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean(name = "customFilterChain")
    public SecurityFilterChain customFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(httpSecurityCorsConfigurer -> Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorizationManagerRequestMatcherRegistry
                        -> authorizationManagerRequestMatcherRegistry.requestMatchers(PASS_URL_LIST).permitAll()
                        .anyRequest().authenticated()
                )
                .addFilterBefore(oncePerRequestFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(loggingFilter, UsernamePasswordAuthenticationFilter.class);
        // 등록되는데 Component 로 사용 안함 -> Bean 등록이 안되니까 Spring container -> 서블릿호출할때 안불러
        // Spring Security Filter Chain에서 1번만 되는거
        //.addFilterBefore(new CustomLoggingFilter() , UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
