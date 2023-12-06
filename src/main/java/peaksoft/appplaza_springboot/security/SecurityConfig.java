package peaksoft.appplaza_springboot.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import peaksoft.appplaza_springboot.security.jwt.JwtFilter;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtFilter jwtFilter;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl();
    }


    @Bean
    public AuthenticationManager daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(bCryptPasswordEncoder());
        provider.setUserDetailsService(userDetailsService());
        return new ProviderManager(provider);
    }




//    @Bean
//    public DaoAuthenticationProvider daoAuthenticationProvider() {
//        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
//        provider.setPasswordEncoder(bCryptPasswordEncoder());
//        provider.setUserDetailsService(userDetailsService());
//        return provider;
//    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .cors(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> {
                    authorize
                            .requestMatchers("/api/users/sign-up",
                                    "/api/users/sign-in").permitAll()
                            .requestMatchers("/api/users").hasAuthority("ADMIN")
                            .requestMatchers("/api/users/{id}").hasAuthority("ADMIN")
                            .requestMatchers("/api/users/update/{id}").hasAnyAuthority("ADMIN", "USER")
                            .requestMatchers("/api/users/profile").hasAnyAuthority("ADMIN", "USER")

                            .requestMatchers("/api/applications/sign-up").hasAuthority("ADMIN")
                            .requestMatchers("/api/applications").hasAnyAuthority("ADMIN", "USER")
                            .requestMatchers("/api/applications/{id}").hasAuthority("ADMIN")
                            .requestMatchers("/api/applications/update/{id}").hasAuthority("ADMIN")

                            .requestMatchers("/api/genres/sign-up").hasAuthority("ADMIN")
                            .requestMatchers("/api/genres").hasAnyAuthority("ADMIN", "USER")
                            .requestMatchers("/api/genres/{id}").hasAuthority("ADMIN")
                            .requestMatchers("/api/genres/update/{id}").hasAuthority("ADMIN")

                            .requestMatchers("/api/mailSenders/sign-up").permitAll()
                            .requestMatchers("/api/mailSenders").permitAll()
                            .requestMatchers("/api/mailSenders/{id}").permitAll()
                            .requestMatchers("/api/mailSenders/update/{id}").permitAll()

                            .anyRequest().authenticated();
                })
//                .httpBasic(Customizer.withDefaults())
//                .authenticationProvider(provider)
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

}
