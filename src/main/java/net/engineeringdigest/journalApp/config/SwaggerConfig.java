package net.engineeringdigest.journalApp.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import io.swagger.v3.oas.models.tags.Tag;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI myConfig(){
        return new OpenAPI()
                .info(
                new Info().title("Journal App APIs ")
                        .description("by Pradeep")
                )
                .servers(Arrays.asList(new Server().url("http://localhost:8081").description("local"),
                        new Server().url("http:localhost:8082").description("live")))

                .tags(Arrays.asList(
                        new Tag().name("Public APIs"),
                        new Tag().name("User APIs"),
                        new Tag().name("Journal APIs"),
                        new Tag().name("Admin APIs")
                ))
                //below code add header dialogue boc in Swagger-ui
//                .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
//                .components(new Components().addSecuritySchemes(
//                        "bearerAuth",new SecurityScheme()
//                                .type(SecurityScheme.Type.HTTP)
//                                .scheme("Bearer")
//                                .bearerFormat("JWT")
//                                .in(SecurityScheme.In.HEADER)
//                                .name("Authorization")
//                ))
                //end

                ;
    }
}
