package com.heavenhr.recruiting.container.configuration;

import com.heavenhr.recruiting.module.common.domain.auditing.AuditorAwareImpl;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
@EntityScan(basePackages = {"com.heavenhr.recruiting.module.recruiting.domain.modal"} )
@EnableJpaRepositories(basePackages = {"com.heavenhr.recruiting.module.recruiting.domain.repository"})
public class JPAConfiguration {
    @Bean
    public AuditorAware<String> auditorAware() {
        return new AuditorAwareImpl();
    }
}