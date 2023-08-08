package com.gateway.config;

import com.gateway.filter.AuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableEurekaClient
public class GatewayConfig {

    @Autowired
    AuthenticationFilter filter;

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("COMPLIANCE-MASTER-SERVICE", r -> r.path("/master/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://COMPLIANCE-MASTER-SERVICE"))

                .route("COMPLIANCE-SERVICE", r -> r.path("/compliance/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://COMPLIANCE-SERVICE"))

                .route("COMPANY-SERVICE", r -> r.path("/company/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://COMPANY-SERVICE"))

                .route("AUTHENTICATION-SERVICE", r -> r.path("/api/auth/**","/api/user/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://AUTHENTICATION-SERVICE"))
                .build();
    }
}
