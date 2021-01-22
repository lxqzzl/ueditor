package com.lxq.ueditor.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 跨域请求配置
 * @author l1
 *
 */

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override  
    public void addCorsMappings(CorsRegistry registry) {  
        registry.addMapping("/**")  
                .allowedOrigins("*")  
                .allowedMethods("GET", "HEAD", "POST", "PUT", "DELETE", "OPTIONS", "PATCH")  
                .allowCredentials(true)  
                .maxAge(3600)  
                .allowedHeaders("*");  
    }    
}
