/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.teste.desafiobackvotos.config;

import springfox.documentation.service.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 *
 * @author deivid
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .apiInfo(info());
    }

    private ApiInfo info() {
        return new ApiInfoBuilder().title("Api Desafio Voto")
                .description("Desafio Técnico Java")
                .contact(new Contact("Deivid Nunes",
                        "https://github.com/deividnn/",
                        "deividnn6291@gmail.com") {
                })
                .license("Apache 2.0").licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
                .version("1.0.0").build();
    }
}
