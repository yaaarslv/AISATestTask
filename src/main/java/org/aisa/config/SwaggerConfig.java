package org.aisa.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RequestPredicates;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.RouterFunctions;
import org.springframework.web.servlet.function.ServerResponse;

import java.net.URI;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("AISA API")
                        .version("2.0.0"));
    }

    @Bean
    public GroupedOpenApi customApi() {
        return GroupedOpenApi.builder()
                .group("my-api")
                .pathsToMatch("/api/**")
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> router() {
        return RouterFunctions.route(RequestPredicates.GET("/aisa-api"), req ->
                ServerResponse.temporaryRedirect(URI.create("/swagger-ui.html")).build());
    }
}
