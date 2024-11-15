package microservices_book.gateway.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebCorsConfiguration {

//     public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) throws Exception {
//     http.authorizeExchange(exchanges -> exchanges
//         .pathMatchers(HttpMethod.OPTIONS, "/**").permitAll()
//         .pathMatchers("/api/**").permitAll()
//     ).cors(corsCustomizer -> corsCustomizer.disable()).csrf(csrfCustomizer -> csrfCustomizer.disable());
    

//     return http.build();
// }
    
    @Bean
    public WebMvcConfigurer corsConfigurer() {

        return new WebMvcConfigurer() {
            
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")  // Apply CORS to /api/** endpoints
                .allowedOrigins("*")  // Allow the frontend at port 9090
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")  // Allowed HTTP methods
                .allowedHeaders("*");  // Allow credentials like cookies or HTTP authentication
            }
        };
    }
}
