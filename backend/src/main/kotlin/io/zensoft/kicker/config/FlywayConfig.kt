package io.zensoft.kicker.config

import org.springframework.boot.autoconfigure.flyway.FlywayMigrationStrategy
import org.springframework.context.annotation.Configuration

/**
 * @author Yauheni Efimenko
 */
@Configuration
class FlywayConfig {

//    @Profile("local")
//    @Bean
    fun flywayMigrationStrategy(): FlywayMigrationStrategy {
        return FlywayMigrationStrategy { flyway ->
            flyway.clean()
            flyway.migrate()
        }
    }

}