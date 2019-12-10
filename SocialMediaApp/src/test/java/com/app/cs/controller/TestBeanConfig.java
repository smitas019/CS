package com.app.cs.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.app.cs.service.SocialMediaService;

//@ComponentScan(basePackages = "com.app.cs")
@Configuration
class TestBeanConfig {

	@Bean
    public HomeController myMvcController() {
        return new HomeController();
    }
	
	@Bean
    public SocialMediaService socialMediaService() {
        return new SocialMediaService();
    }
}
