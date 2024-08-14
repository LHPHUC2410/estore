package com.estore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.estore.interceptor.AuthorizeInterceptor;
import com.estore.interceptor.EShopInterceptor;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
	@Autowired
	EShopInterceptor share;
	@Autowired
	AuthorizeInterceptor authe;
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(share).addPathPatterns("/**");
		
		
		  registry.addInterceptor(authe) .addPathPatterns("/account/change",
		  "/account/logoff", "/account/edit", "/order/**");
		 
	}
}
