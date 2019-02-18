package io.zensoft.kicker.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
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
class SwaggerConfig {

    @Bean
    fun api(): Docket {
        return Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("io.zensoft.kicker.controller.api"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo())
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

                Fields:
                * status - HTTP status
                * message - error's message
                * errors - `optional`
            """.trimIndent())
            .version("1.0.0")
            .build()

}