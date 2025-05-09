package jjj.scm.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                //.addSecurityItem(new SecurityRequirement().addList("Bearer Authorization"))
                //.components(new Components().addSecuritySchemes("" ,createAPIKeyScheme()))
                .info(new Info().title("JJJ SCM API")
                        .version("1.0")
                );
    }

    @Bean
    public GroupedOpenApi testApi() {
        return GroupedOpenApi.builder().group("test api").pathsToMatch("/test/**").packagesToScan("jjj.scm.test").build();
    }

    private SecurityScheme createAPIKeyScheme() {
        return new SecurityScheme().type(SecurityScheme.Type.HTTP)
                .bearerFormat("JWT")
                .scheme("bearer");
    }
}
