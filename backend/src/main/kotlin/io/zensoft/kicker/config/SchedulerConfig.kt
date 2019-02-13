package io.zensoft.kicker.config

import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.annotation.SchedulingConfigurer
import org.springframework.scheduling.config.ScheduledTaskRegistrar
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler

/**
 * @author Yauheni Efimenko
 */
@Configuration
@EnableScheduling
class SchedulerConfig : SchedulingConfigurer {

    override fun configureTasks(taskRegistrar: ScheduledTaskRegistrar) {
        val taskScheduler = ThreadPoolTaskScheduler()
        taskScheduler.poolSize = 5
        taskScheduler.threadNamePrefix = "ScheduledExecutor-"
        taskScheduler.initialize()

        taskRegistrar.setTaskScheduler(taskScheduler)
    }

}