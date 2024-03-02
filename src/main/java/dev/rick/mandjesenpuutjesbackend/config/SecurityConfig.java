package dev.rick.mandjesenpuutjesbackend.config;

import dev.rick.mandjesenpuutjesbackend.jwt.JwtRequestFilter;
import dev.rick.mandjesenpuutjesbackend.jwt.JwtUtility;
import dev.rick.mandjesenpuutjesbackend.user.CustomUserDetailsService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.net.PasswordAuthentication;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtRequestFilter jwtRequestFilter;
    private final CustomUserDetailsService userDetailsService;

    public SecurityConfig(JwtRequestFilter jwtRequestFilter, CustomUserDetailsService userDetailsService) {
        this.jwtRequestFilter = jwtRequestFilter;
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(PasswordEncoder encoder) {
        var auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(userDetailsService);
        auth.setPasswordEncoder(encoder);
        return new ProviderManager(auth);
    }

    @Bean
    protected SecurityFilterChain filter(HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable())
                .httpBasic(basic -> basic.disable())
                .cors(Customizer.withDefaults())
                .authorizeHttpRequests(auth -> auth

//                        PERMITTED TO ADMIN ONLY
                                .requestMatchers(HttpMethod.PUT, "/api/v1/admin/**").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.DELETE, "/api/v1/admin/**").hasRole("ADMIN")

//                        PERMITTED TO AUTHENTICATED USER
                                .requestMatchers(HttpMethod.GET, "/api/v1/user/**").authenticated()
                                .requestMatchers(HttpMethod.POST, "/api/v1/user/**").authenticated()
                                .requestMatchers(HttpMethod.PUT, "/api/v1/user/**").authenticated()
                                .requestMatchers(HttpMethod.DELETE, "/api/v1/user/**").authenticated()

//                        PERMITTED TO ALL USERS
                                .requestMatchers(HttpMethod.POST, "/api/v1/recipes/add-new").hasRole("USER")

//                        OPEN TO ALL
                                .requestMatchers(HttpMethod.POST, "/api/v1/products/open/**").permitAll()
                                .requestMatchers(HttpMethod.GET, "/api/v1/products/open/**").permitAll()
                                .requestMatchers(HttpMethod.POST, "/api/v1/users/register").permitAll()
                                .requestMatchers(HttpMethod.POST, "/api/v1/users/authenticate").permitAll()
                                .requestMatchers(HttpMethod.GET, "/api/v1/recipes/get").permitAll()

                                .anyRequest().denyAll()
                )

                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

}
