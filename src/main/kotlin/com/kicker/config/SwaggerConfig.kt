package com.kicker.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.ApiInfo
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2


/**
 * @author Yauheni Efimenko
 */
@Configuration
@EnableSwagger2
class SwaggerConfig : WebMvcConfigurationSupport() {

    @Bean
    fun api(): Docket {
        return Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.kicker.controller.api"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo())
    }

    override fun addResourceHandlers(registry: ResourceHandlerRegistry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/")

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/")
    }

    private fun apiInfo(): ApiInfo = ApiInfoBuilder()
            .title("Kicker API")
            .description("""
                ## Marks

                * `pageable`

                Target - URL

                Indicate - URL marked by `pageble` supports request parameters described below

                Request params:
                    1. offset - describes the offset for the set of results (only positive integers, default value - `0`)
                    2. limit - describes the limit of entities in the result set (only positive integers, default value - `10`)

                * `sorting`

                Target - URL

                Indicate - allow sorting by acceptable fields

                Request params:
                    1. sortBy - describes the sorting field of entity (default value - `id`)
                    2. sortDirection - describes the sorting direction(`ASC` or `DESC`, default value - `ASC`)

                * `optional`

                Target - dto's fields

                Indicate - nullable fields

                ## Errors

                ```json
                {
                  "status": 400,
                  "message": "Method Argument Not Valid",
                  "errors": [
                    {
                      "message": "size must be between 5 and 50",
                      "field": "password",
                      "code": "Size"
                    },
                    {
                      "message": "size must be between 5 and 50",
                      "field": "confirmPassword",
                      "code": "Size"
                    },
                    {
                      "message": "Passwords must be equal",
                      "code": "PasswordsEqualsConstraint"
                    }
                  ]
                }
                ```

                Fields:
                * status - HTTP status
                * message - error's message
                * errors - `optional`
            """.trimIndent())
            .version("1.0.0")
            .build()

}