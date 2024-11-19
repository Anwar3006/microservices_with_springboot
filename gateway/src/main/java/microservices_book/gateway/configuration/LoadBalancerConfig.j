// package microservices_book.gateway.configuration;

// import java.util.function.BiFunction;

// import org.springframework.cloud.client.ServiceInstance;
// import org.springframework.cloud.loadbalancer.core.HealthCheckServiceInstanceListSupplier;
// import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.util.function.SingletonSupplier;

// import reactor.core.publisher.Mono;

// @Configuration
// public class LoadBalancerConfig {
    
//     @Bean
//     public ServiceInstanceListSupplier healthCheckServiceInstanceListSupplier(ServiceInstanceListSupplier delegate) {
//         // Factory that creates ServiceInstance, could be a lambda or a more concrete factory if needed
//         SingletonSupplier<ServiceInstance> factory = SingletonSupplier.of(() -> null); // Replace with a meaningful factory as necessary

//         // Health check function that checks a `ServiceInstance` for health; modify the check logic accordingly
//         BiFunction<ServiceInstance, String, Mono<Boolean>> healthCheckFunction = (instance, healthCheckPath) -> {
//             // Here, you can implement logic to make an HTTP request to the instance's health endpoint, etc.
//             return Mono.just(true); // Modify with your health-checking logic
//         };

//         return new HealthCheckServiceInstanceListSupplier(delegate, null, healthCheckFunction);
//         // return new HealthCheckServiceInstanceListSupplier(delegate, factory, healthCheckFunction);
//     }

// }
