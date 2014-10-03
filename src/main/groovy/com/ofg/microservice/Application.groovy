package com.ofg.microservice

import com.ofg.infrastructure.environment.EnvironmentSetupVerifier
import groovy.transform.TypeChecked
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.orm.jpa.EntityScan
import org.springframework.cache.annotation.EnableCaching
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.EnableAspectJAutoProxy
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.scheduling.annotation.EnableAsync
import org.springframework.transaction.annotation.EnableTransactionManagement

import static com.ofg.config.BasicProfiles.*

@TypeChecked
@Configuration
@EnableAutoConfiguration
@EnableAspectJAutoProxy(proxyTargetClass = true)
@EnableTransactionManagement
@ComponentScan(basePackages = ["com.ofg.microservice", "com.ofg.decisionmaker"])
@EnableCaching
@EnableAsync
@EnableJpaRepositories("com.ofg.decisionmaker")
@EntityScan("com.ofg.decisionmaker.domain")

class Application {

    static void main(String[] args) {
        SpringApplication application = new SpringApplication(Application)
        application.addListeners(new EnvironmentSetupVerifier([DEVELOPMENT, PRODUCTION, TEST]))
        application.run(args)
    }
}