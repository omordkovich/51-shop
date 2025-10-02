package ait.shop.security.security_config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity //Все входящие HTTP запросы будут проверятся
@EnableMethodSecurity
public class SecurityConfig {


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .httpBasic(Customizer.withDefaults())
                .authorizeHttpRequests(auth -> auth
//        1. Получение всех продуктов - Всем пользователям, включая анонимных
                                .requestMatchers(HttpMethod.GET, "/products").permitAll()
//        2.  Получение продукта по id - аутентифицированным пользователям с любой ролью
                                .requestMatchers(HttpMethod.GET, "/products/{id}").authenticated()
//        3. Сохранение в БД только АДМИНАМ!
                                .requestMatchers(HttpMethod.POST, "/products").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.POST, "/consumers").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.GET, "/consumers").hasAnyRole("ADMIN", "USER")
                                .requestMatchers(HttpMethod.PUT, "/consumers/{consumerId}/add-product/{productId}").hasAnyRole("ADMIN", "USER")
                                .requestMatchers(HttpMethod.DELETE, "/consumers/{consumerId}/remove-product/{productId}").hasAnyRole("ADMIN", "USER")
                                .requestMatchers(HttpMethod.DELETE, "/consumers/{consumerId}/clean-cart").hasAnyRole("ADMIN", "USER")
                                .requestMatchers(HttpMethod.DELETE, "/consumers/{id}").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.DELETE, "/consumers/delete/{name}").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.GET, "/consumers/count").hasAnyRole("ADMIN", "USER")
                                .requestMatchers(HttpMethod.POST, "/consumers/{id}/restore").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.PATCH, "/consumers/{id}/update").hasAnyRole("ADMIN", "USER")
                );
        return http.build();
    }
}
