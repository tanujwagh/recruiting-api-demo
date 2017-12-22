package com.heavenhr.recruiting.container.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Configuration root.
 */
@ComponentScan({"com.heavenhr.recruiting.module.recruiting"})
@Configuration
@Import(value = {
        JPAConfiguration.class,
        MapperConfiguration.class,
        SecurityConfiguration.class
})
public class ApplicationContextConfiguration {
}