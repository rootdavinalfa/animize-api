/*
 * Copyright (c) 2020.
 * Davin Alfarizky Putra Basudewa <dbasudewa@gmail.com>
 * Animize Streaming API
 */

package xyz.dvnlabs.animize_api

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.Contact
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2

@Configuration
@EnableSwagger2
class SpringFoxConfig {
    @Bean
    fun api(): Docket {
        return Docket(DocumentationType.SWAGGER_2)
                .apiInfo(
                        ApiInfoBuilder()
                                .title("Animize API")
                                .description("API For Animize")
                                .contact(Contact(
                                        "Davin Alfarizky Putra Basudewa",
                                        "https://dvnlabs.xyz",
                                        "dbasudewa@gmail.com"
                                ))
                                .build()
                )
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build().pathMapping("/")
    }
}