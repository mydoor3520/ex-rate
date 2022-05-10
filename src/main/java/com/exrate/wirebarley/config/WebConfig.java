package com.exrate.wirebarley.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.BeanNameViewResolver;

import java.util.concurrent.TimeUnit;

@Configuration
public class WebConfig implements WebMvcConfigurer {
	private static final String CLASSPATH_RESOURCE_LOCATIONS = "classpath:/static";

	@Override
	public void addResourceHandlers(final ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/assets/**").addResourceLocations(CLASSPATH_RESOURCE_LOCATIONS + "/assets/").setCacheControl(CacheControl.maxAge(365, TimeUnit.DAYS));
	}
    
    @Bean
    public ViewResolver viewResolver() {
    	return new BeanNameViewResolver();
    }

}