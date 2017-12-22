package com.heavenhr.recruiting.container;

import com.heavenhr.recruiting.container.configuration.ApplicationContextConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jmx.JmxAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Import;

/**
 * Spring boot application Entry Point.
 */
@EnableAutoConfiguration(exclude = {JmxAutoConfiguration.class})
@Import({ApplicationContextConfiguration.class})
@SpringBootApplication
public class RecruitingApiWebApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(RecruitingApiWebApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(RecruitingApiWebApplication.class);
	}
}
