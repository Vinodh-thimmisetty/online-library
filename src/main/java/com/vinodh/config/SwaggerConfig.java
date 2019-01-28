package com.vinodh.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.AuthorizationCodeGrantBuilder;
import springfox.documentation.builders.OAuthBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.TokenEndpointBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.GrantType;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.service.TokenRequestEndpoint;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger.web.SecurityConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static springfox.documentation.builders.RequestHandlerSelectors.basePackage;
import static springfox.documentation.spi.DocumentationType.SWAGGER_2;

/**
 *
 * Swagger home page:
 *          http://localhost:1111/v2/api-docs
 *          http://localhost:1111/swagger-ui.html#/
 *
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    private static final Supplier<Set<String>> CONTENT_TYPES = () -> Stream.of("application/json", "application/xml").collect(Collectors.toSet());

    @Bean
    public Docket libraryAPI() {
        return new Docket(SWAGGER_2)
                .select()
                .apis(basePackage("com.vinodh.web"))
                .paths(PathSelectors.regex("/library.*"))
//                .paths(PathSelectors.ant("/library/*"))
                .build()
                .apiInfo(libraryMetaData());
//                .produces(CONTENT_TYPES.get())
//                .consumes(CONTENT_TYPES.get());
//                .securitySchemes(securitySchemes())
//                .securityContexts(securityContexts());
    }

    /**
     *
     * Swagger Definition i.e., Meta Data of SWAGGER API
     *
     * @return ApiInfo
     */
    private ApiInfo libraryMetaData() {
        return new ApiInfoBuilder()
                .title("OLP (Online Library Platform)")
                .description("A desktop application which manages the offline and online book keeping")
                .version("V1.0.0")
                .license("Apache 2.0")
                .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0")
                .termsOfServiceUrl("For god sake, we are not going to collect any of your personal data")
                .contact(new Contact("Vinodh Kumar Thimmisetty", "vinodh-thimmisetty", "vinodh5052@gmail.com"))
                .extensions(null)
                .build();
    }

    /**
     *
     * @return which API's Endpoints are Secured ?
     */
    private List<SecurityContext> securityContexts() {
        return null;
    }

    /**
     *
     * @return Basic Auth | oAuth etc..
     */
    private List<SecurityScheme> securitySchemes() {
        return Stream.of(new OAuthBuilder()
                .name("")
                .grantTypes(grants())
                .scopes(scopes())
                .build())
                .collect(Collectors.toList());
    }

    private List<AuthorizationScope> scopes() {
        return Stream
                .of(new AuthorizationScope("SUPER ADMIN", "Head of All Libraries"),
                        new AuthorizationScope("ADMIN", "Head of individual Library"),
                        new AuthorizationScope("CUSTOMER", "Customer to a Library"))
                .collect(Collectors.toList());
    }

    private List<GrantType> grants() {
        return Stream.of(new AuthorizationCodeGrantBuilder()
                .tokenEndpoint(new TokenEndpointBuilder()
                        .tokenName("")
                        .url("")
                        .build())
                .tokenRequestEndpoint(new TokenRequestEndpoint("", "", ""))
                .build()).collect(Collectors.toList());

    }

    @Bean
    public SecurityConfiguration securityConfiguration() {
        return SecurityConfigurationBuilder
                .builder()
                .clientId("vinodh")
                .clientSecret("vinodh")
                .scopeSeparator(" ")
                .useBasicAuthenticationWithAccessCodeGrant(true)
                .build();
    }
}
